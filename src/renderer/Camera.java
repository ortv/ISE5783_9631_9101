package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.isZero;

import java.awt.image.renderable.ContextualRenderedImageFactory;
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
    /**
     * Renders the image using the camera configuration.
     *
     * @return The camera instance for method chaining.
     * @throws MissingResourceException If any required resources are missing.
     */
	public Camera renderImage()
	{
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
        
        return this;//for builder

	}
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
	
	private Color castRay(int nX, int nY, int j, int i) 
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
		if(xJ!=0)
			pIJ=pIJ.add(vRight.scale(xJ));
		if(yI!=0)
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


	
}
