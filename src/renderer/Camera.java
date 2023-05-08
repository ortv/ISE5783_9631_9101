package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.isZero;

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
