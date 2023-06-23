package renderer;


import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
import static primitives.Util.isZero;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;

public class Camera {
	/*
	The center point of the camera.
	*/
	private Point p0;
	/*
	The up vector of the camera.
	*/
	private Vector vUp;
	/*
	The "to" vector of the camera.
	*/
	private Vector vTo;
	/*
	The right vector of the camera.
	*/
	private Vector vRight;
	/*
	The width of the viewport.
	*/
	private double width;
	/*
	The height of the viewport.
	*/
	private double height;
	/*
	The distance between the camera and the view plane.
	*/
	private double distance;
	/* 
	 for writing the image
	 */
	private ImageWriter imageWriter;
	/*
	 * for throwing rays from pixels for coloring*/
	private RayTracerBase rayTracer;
	
	 /**
     * turn on - off antialising super sampling
     */
    private boolean isAntialising= false;//indicates of to improve the picture with antialising or not. the default is not. 
    
   
    /**
     * the number of rays
     */
    private int numOfRaysSuperSampeling = 100;//for sumersampleing

    private boolean AdaptiveSuperSamplingFlag = false;
	private int numOfRays=1;
    public Camera setNumOfRays(int numOfRays){
		if(numOfRays == 0)
			this.numOfRays=1;
		else
		 this.numOfRays = numOfRays;
		return this;
	}

	private int _threads = 1;
	public Camera setNumOfRaysSuperSampeling(int numOfRaysSuperSampeling) {
		this.numOfRaysSuperSampeling = numOfRaysSuperSampeling;
		return this;
	}

	public Camera setAdaptiveSuperSamplingFlag(boolean adaptiveSuperSamplingFlag) {
		AdaptiveSuperSamplingFlag = adaptiveSuperSamplingFlag;
		return this;
	}

	public Camera setThreads(int threads) {
		this.threads = threads;
		return this;
	}

	public Camera setPrint(boolean print) {
		this.print = print;
		return this;
	}
	private int threads = 1;
	private static final int SPARE_THREADS = 2; // Spare threads if trying to use all the cores
	private boolean print = false; // printing progress percentage

	public Camera setnumOfRaysSuperSampeling(int numOfRays) {
		this.numOfRaysSuperSampeling = numOfRays;
		return this;
		
	}
	
	public Camera setAntialising(boolean isAntialising) {
		this.isAntialising = isAntialising;
		return this;
	}
	
	private class Pixel
	{
		private long maxMunRows=0;// maximum row values of image
		private long maxMunCols=0;//maximum col values of image
		private long pixels=0;//total number of pixels in the image
		public volatile int row = 0;//epresent the current row and 
	    public volatile int col = -1;//column of the pixel being processed.
	    private long counter = 0;//number of pixels processed so far.
	    private int percent = 0;// percentage progress of pixel processing.
	    private long nextCounter = 0;// counter value at which the next percentage progress update should occur.

		public Pixel(int row,int col)
		{
			maxMunRows=row;
			maxMunCols=col;
			pixels=maxMunCols*maxMunRows;
			nextCounter=pixels/100;
			 if(print)//if the flag for printing the percentage is on
	             System.out.printf("\r %02d%%", percent);
				
		}
		public Pixel() {}
		
		
		public boolean nextPixel(Pixel p)
		{//check if there is a next pixel
			int percent=nextp(p);
			if(percent>0&&print)
			{
                synchronized(System.out){System.out.printf("\r %02d%%", percent);}
			}
			if(percent>=0)
				return true;//there is a next pixel
			if(print)
			{
				synchronized(System.out){System.out.printf("\r %02d%%", 100);}
			}
			return false;//no next pixel
			}
		
		
		
		/**
         * Pixel - nextP
         * changing target pixel to the next pixel
         * return 0 if there is a next pixel, and -1 else
         */
        private synchronized int nextp(Pixel target) {
            col++;
            counter++;
            if(col < maxMunCols) {
                target.row = this.row;
                target.col = this.col;
                if(counter == nextCounter)
                {
                    percent++;
                    nextCounter = pixels * (percent + 1) / 100;
                    return percent;
                }
                return 0;
            }

            row++;
            if(row < maxMunRows) {
                col = 0;
                if(counter == nextCounter)
                {
                    percent++;
                    nextCounter = pixels * (percent + 1) / 100;
                    return percent;
                }
                return 0;//could create a next pixel
            }
            return -1;
        }
        
	}
    ////////////////////////////////////////////////////////////////////////////////////////end of inner class

        /**
         * Render - Image
         * Converts the image from D3 to D2
         * The result is written in imageWriter
         */
        public Camera renderImage()
        {
        	try {
        	if (AdaptiveSuperSamplingFlag) {//indicates whether to apply adaptive super sampling.
            int nX = imageWriter.getNx();
            int nY = imageWriter.getNy();

            //Multi threads for calculating pixel color
            final Pixel thePixel = new Pixel(nY,nX);//creating an instance of pixel with the maximum row and column values.
            Thread[] threads = new Thread[_threads];//arrey of threads to perform parallel processing of pixels
            for(int i = 0 ; i<_threads; i++)
            {//creates a new thread for each 
                //index and assigns a task to each thread
            	//while there is a pixel  that is not processed yet,
            	//calculating the color,and writing it in the pixel
            	threads[i] = new Thread(()->{
                    Pixel pixel = new Pixel();
                    try {
                        //while there is a pixel that is not processed by a thread
                        while (thePixel.nextPixel(pixel)) {
                                    primitives.Color color = AdaptiveSuperSampling(nX, nY, pixel.col, pixel.row,numOfRaysSuperSampeling);
                                    imageWriter.writePixel(pixel.col, pixel.row, new Color(color.getColor()));
                        }
                    }
                    catch (Exception e){}
                });//end of creating thread
            }
            //starts all threads
            for (Thread thread : threads)
                thread.start();

            //Wait for all threads to finish
            for (Thread thread : threads)
                thread.join();

            //finish to create the image
            if(print)
                System.out.print("\r100%\n");
        	}
        	
            else
            { this.renderImageBeam();}}
        	
            catch (Exception e){}
        	return this;
        	
        	
        }
            
        
        /**
         * Adaptive Super Sampling
         *@param nX number of pixels in x axis
         *@param nY number of pixels in y axis
         *@param j number of pixels in x axis
         *@param i number of pixels in x axis
         *@param numOfRays max num of ray for pixel
         *@return color for pixel
         */
        private primitives.Color AdaptiveSuperSampling(int nX, int nY, int j, int i, int numOfRays) throws Exception {
            int numOfRaysInRowCol = (int)Math.floor(Math.sqrt(numOfRays));
            if(numOfRaysInRowCol == 1)
                return rayTracer.traceRay(constructRay(nX, nY, j, i));
            Point Pc;
            if (distance != 0)
                Pc = p0.add(vTo.scale(distance));
            else
                Pc = p0;
            Point Pij = Pc;
            double Ry = height/nY;
            double Rx = width/nX;
            double Yi= (i - (nY/2d))*Ry + Ry/2d;
            double Xj= (j - (nX/2d))*Rx + Rx/2d;
            if(Xj != 0) Pij = Pij.add(vRight.scale(-Xj)) ;
            if(Yi != 0) Pij = Pij.add(vUp.scale(-Yi)) ;
            double PRy = Ry/numOfRaysInRowCol;
            double PRx = Rx/numOfRaysInRowCol;
            return AdaptiveSuperSamplingRec(Pij, Rx, Ry, PRx, PRy,null);
        }

        /**
         * Adaptive Super Sampling recursive
         *@param centerP the screen location
         *@param Width the screen width
         *@param Height the screen height
         *@param minWidth the min cube width
         *@param minHeight the min cube height
         *@param prePoints list of pre points to
         *@return color for pixel
         */
        private primitives.Color AdaptiveSuperSamplingRec(Point centerP, double Width, double Height, double minWidth, double minHeight, List<Point> prePoints) throws Exception {
//The method recursively subdivides the current pixel into smaller sub-pixels and calculates the color for each sub-pixel.
            if (Width < minWidth * 2 || Height < minHeight * 2) {//calculates the color
                return rayTracer.traceRay(new Ray(centerP.subtract(p0),p0));
            }
            //otherwise
            List<Point> nextCenterPList = new LinkedList<>();
            List<Point> cornersList = new LinkedList<>();
            List<primitives.Color> colorList = new LinkedList<>();
            Point tempCorner;
            Ray tempRay;
            //subdivides the current pixel into four sub-pixels and recursively calls itself for each sub-pixel.
            for (int i = -1; i <= 1; i += 2){
                for (int j = -1; j <= 1; j += 2) {
                	tempCorner = centerP.add(vRight.scale(i * Width / 2)).add(vUp.scale(j * Height / 2));
                    cornersList.add(tempCorner);//add the corner point to list
                    if (prePoints == null || !isInList(prePoints, tempCorner)) {
                        tempRay = new Ray(tempCorner.subtract(p0),p0);//creates a ray, calc the color and add to list of colors
                        nextCenterPList.add(centerP.add(vRight.scale(i * Width / 4)).add(vUp.scale(j * Height / 4)));
                        colorList.add(rayTracer.traceRay(tempRay));//all the calculated colors  are saved in the list
                        }
                    }
                }
            //if there are no points(calculated colors)
            if (nextCenterPList == null || nextCenterPList.size() == 0) {
                return primitives.Color.BLACK;
            }


            boolean isAllEquals = true;
            primitives.Color tempColor = colorList.get(0);
            for (primitives.Color color : colorList) {//check if the colors in the list are not equal
                if (!tempColor.isAlmostEquals(color))
                    isAllEquals = false;
            }
            if (isAllEquals && colorList.size() > 1)//if the colors are equal
                return tempColor;//return the color of all
            //else, not all colors are equal

            tempColor = primitives.Color.BLACK;
            for (Point center : nextCenterPList) {
                tempColor = tempColor.add(AdaptiveSuperSamplingRec(center, Width/2,  Height/2,  minWidth,  minHeight ,  cornersList));//calls again for each sub pixel
            }
            return tempColor.reduce(nextCenterPList.size());//return the  color of all colors


        }
        
        /**
         * is In List - Checks whether a point is in a points list
         * @param point the point we want to check
         * @param pointsList where we search the point
         * @return true if the point is in the list, false otherwise
         */
        private boolean isInList(List<Point> pointsList, Point point) {
            for (Point tempPoint : pointsList) {
                if(point.isAlmostEquals(tempPoint))
                    return true;
            }
            return false;
        }
        

	 /**
     * Set multi-threading <br>
     * - if the parameter is 0 - number of cores less 2 is taken
     *
     * @param threads number of threads
     * @return the Render object itself
     */
     public Camera setMultithreading(int threads) {
     if (threads < 0)
 	    throw new IllegalArgumentException("Multithreading parameter must be 0 or higher");
     if (threads != 0)
 	    _threads = threads;
     else {
 	    int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
 	    _threads = cores <= 2 ? 1 : cores;
     }
     return this;
     }
			
	
	/**
	Constructs a new camera with the given parameters.
	@param p The center point of the camera.
	@param to The "to" vector of the camera.
	@param up The up vector of the camera.
	@throws IllegalArgumentException If the given up and to vectors are not orthogonal.
	*/
	public Camera(Point p,Vector to,Vector up)
	{
		//need to check if the given vectors are orthogonal-if their dotProduct is zero
		if(isZero(up.dotProduct(to))==false)
			throw new IllegalArgumentException("vectors are not orthogonal!");
		//else-orthogonal
		vRight=to.crossProduct(up).normalize();
		vUp=up.normalize();
		vTo=to.normalize();
		p0=new Point(p.getX(),p.getY(),p.getZ());
	}
    private final String RESOURCE = "Renderer resource not set";
	private final String CAMERA_CLASS = "Camera";
    private final String IMAGE_WRITER = "Image writer";
    private final String CAMERA = "Camera";
    private final String RAY_TRACER = "Ray tracer";
    private final String DISTANCE = "camera cant be in distance 0";

    /**
     * Renders the image using the camera configuration.
     *
     * @throws MissingResourceException If any required resources are missing.
     */
	/*public Camera renderImage()
	{
		if(isAntialising)//if the flag to improvement is on
			renderImageBeam();
		if (imageWriter == null)
            throw new MissingResourceException(RESOURCE, CAMERA_CLASS, IMAGE_WRITER);
        if (p0 == null || vTo == null || vUp == null || vRight == null || width == 0 || height == 0 || distance == 0)
            throw new MissingResourceException(RESOURCE, CAMERA_CLASS, CAMERA);
        if (rayTracer == null)
            throw new MissingResourceException(RESOURCE, CAMERA_CLASS, RAY_TRACER);
        final int nX = imageWriter.getNx();
        final int nY = imageWriter.getNy();
        for(int i=0;i<nX;i++)
        {
        	for(int j=0;j<nY;j++)
        	{//for each pixel in the view plane, shoot a ray from the camera to the pixel and color the pixel
                this.imageWriter.writePixel(j, i, castRay(nX, nY, j, i));
        	}
        }
        return this;
	}*/
	/**
     * Prints a grid pattern on the image.
     *adds grid lines to the rendered image by setting specific pixels to a specified color
     * @param interval The interval between grid lines.
     *
	@param color The color of the grid lines.
	* @throws MissingResourceException If the image writer is not set.
	*/
	public void printGrid(int interval, Color color)
	{
		if (imageWriter == null)
            throw new MissingResourceException(RESOURCE, CAMERA_CLASS, IMAGE_WRITER);
		
		final int nX = imageWriter.getNx();
        final int nY = imageWriter.getNy();

        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (i % interval == 0 || j % interval == 0) {
                    imageWriter.writePixel(j, i, color);
                }
            }
        }
	        
	}
	/**
	 * Sets the ray tracer for the camera.
	 *
	 * @param rayTracer The ray tracer to set.
	 * @return The camera instance for method chaining.
	 */
	public Camera setRayTracer(RayTracerBase rayTracer) {
		this.rayTracer = rayTracer;
		return this;
	}
	/**
	 * Sets the image writer for the camera.
	 *
	 * @param imageWriter The image writer to set.
	 * @return The camera instance for method chaining.
	 */
	public Camera setImageWriter(ImageWriter imageWriter) {
		this.imageWriter = imageWriter;
		return this;
	}
	/**
	 * Writes the image to the file specified in the image writer.
	 *
	 * @throws MissingResourceException If the image writer is not set.
	 */
	public void writeToImage()
	{
		if (imageWriter == null)
			throw new MissingResourceException(RESOURCE, CAMERA, IMAGE_WRITER);
        imageWriter.writeToImage();//delegation!
	}
	
	private Color castRay(int nX, int nY, int j, int i) //shoot the ray through pixel
	{
        return this.rayTracer.traceRay(this.constructRay(nX, nY, j, i));
	}
	/**
	Sets the size of the viewport.
	@param w The width of the viewport.
	@param h The height of the viewport.
	@return The camera instance with the updated viewport size.
	*/
	public Camera setVPSize(double w,double h)
	{
		this.width=w;
		this.height=h;
		return this;
	}
	
	/**
	Sets the distance between the camera and the view plane.
	@param d The distance between the camera and the view plane.
	@return The camera instance with the updated distance.
	*/
	public Camera setVUPDistance(double d)
	{
		this.distance=d;
		return this;
	}
	

	
	public void renderImageBeam()
	{//track rays, if its a beam and more
		
		if (imageWriter == null)
            throw new MissingResourceException(RESOURCE, CAMERA_CLASS, IMAGE_WRITER);
		if (rayTracer == null)
            throw new MissingResourceException(RESOURCE, CAMERA_CLASS, RAY_TRACER);
		
		//if(numOfRaysSuperSampeling==0||numOfRaysSuperSampeling==1)//no supersampeling
			//renderImage();//calls for creating the picture
			
		//else,supersampleing
		//runs on all the image
		for(int i=0;i<imageWriter.getNx();i++)
		{
			for(int j=0;j<imageWriter.getNy();j++)
			{
				if(numOfRays == 1 || numOfRays == 0)
				{
					Ray ray = constructRay(imageWriter.getNx(), imageWriter.getNy(), j, i);
					Color rayColor = rayTracer.traceRay(ray);
					imageWriter.writePixel(j, i, rayColor); 
				}
				//creating a list of all the rays in the beam, calculating their color and eriting the image
				else {
				List<Ray> rays = constructBeamThroughPixel(imageWriter.getNx(), imageWriter.getNy(), j, i,numOfRaysSuperSampeling);
				Color rayColor = rayTracer.traceRay(rays);//new func,gets a list of rays
				imageWriter.writePixel(j, i, rayColor); 
				}
			}
		}
	}
	
	
	
	
	
	
	
	public List<Ray> constructBeamThroughPixel(int nX, int nY, int j, int i, int raysAmount)
	{
		if(isZero(distance))
			throw new IllegalArgumentException(DISTANCE);
		int numOfRays = (int)Math.floor(Math.sqrt(raysAmount)); //num of rays in each row or column
		
		if (numOfRays==1) //if the beam is only one ray
			return List.of(constructRay(nX, nY, j, i));//return a list of only one ray
		
		double Ry= height/nY;
		double Rx=width/nX;
		double Yi=(i-(nY-1)/2d)*Ry;
		double Xj=(j-(nX-1)/2d)*Rx;
    
        double PRy = Ry / numOfRays; //height distance between each ray
        double PRx = Rx / numOfRays; //width distance between each ray

        List<Ray> beamRays = new ArrayList<>();
        
        for (int row = 0; row < numOfRays; ++row) {//runs on the pixel grid
            for (int column = 0; column < numOfRays; ++column) {
            	beamRays.add(constructRaysThroughPixel(PRy,PRx,Yi, Xj, row, column));//add the ray that was shot from target area
            }
        }
        beamRays.add(constructRay(nX, nY, j, i));//add the center screen ray
        return beamRays;
	}
	
	
	 private Ray constructRaysThroughPixel(double Ry,double Rx, double yi, double xj, int j, int i)
	 {//creating a ray of beam rays
	        Point Pc = p0.add(vTo.scale(distance)); //the center of the screen point

	        double y_sample_i =  (i *Ry + Ry/2d); //The pixel starting point on the y axis
	        double x_sample_j=   (j *Rx + Rx/2d); //The pixel starting point on the x axis

	        Point Pij = Pc; //The center point at the pixel through which a beam is fired
	        //Moving the point through which a beam is fired on the x axis
	        if (!isZero(x_sample_j + xj))
	        {
	            Pij = Pij.add(vRight.scale(x_sample_j + xj));
	        }
	        //Moving the point through which a beam is fired on the y axis
	        if (!isZero(y_sample_i + yi))
	        {
	            Pij = Pij.add(vUp.scale(-y_sample_i -yi ));
	        }
	        Vector Vij = Pij.subtract(p0);
	        return new Ray(Vij,p0);//create the ray throw the point we calculate here
	    }
	
	
	/**
	 * building ray from each pixel's center
	 * @param nX size of width of pixel graph
	 * @param nY size of  of pixel graph 
	 * @param j given value for index of wanted pixel
	 * @param i given value for index of wanted pixel
	 * @return a ray from the center of the pixel to the picture
	 */
	
	public Ray constructRay(int nX,int nY,int j,int i)
	{	
		Point pCenterPoint=p0.add(vTo.scale(distance));//p0+d*Vto
		double rY=(height/nY);
		double rX=width/nX;//Ry=h/Ny     Rx=w/Nx
		double yI=-(i-(nY-1d)/2)*rY;
		double xJ=(j-(nX-1d)/2)*rX;
		Point pIJ=pCenterPoint;
		if(!Util.isZero(xJ))
			pIJ=pIJ.add(vRight.scale(xJ));
		if(!Util.isZero(yI))
			pIJ=pIJ.add(vUp.scale(yI));
		//Didn't throw an exception
		Vector vIJ=pIJ.subtract(p0);//a vector for the ray
		//we have a point and a vector for the ray
		return new Ray(vIJ, p0);//building a ray and return it

		
	}
	@Override
	public String toString() {
		return "Camera [p0=" + p0 + ", vUp=" + vUp + ", vTo=" + vTo + ", vRight=" + vRight + ", width=" + width
				+ ", height=" + height + ", distance=" + distance + "]";
	}


	/*Adaptive supersampling increases the efficiency of supersampling by applying it only
where necessary. Instead of taking a sample of the center of the pixel, the algorithm
uses the corners of the pixel to aim the rays. If the colors of the casted rays are similar
to each other the color of the pixel is computed by taking the average. However, if
the colors differ more than a specified amount the pixel is subdivided into 4 equal sized
subareas. The color of each subarea is computed the same way as the color of a pixel.
So each subarea might be divided into smaller and smaller subareas until the colors of
the corners of the smallest subarea are similar enough or a specified maximum recursion
depth is reached*/
	
}


