package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.isZero;

public class Camera {

	private Point p0;
	private Vector vUp;
	private Vector vTo;
	private Vector vRight;
	private double width;
	private double height;
	private double distance;
	
	
	public Camera(Point p,Vector up,Vector to)
	{
		//need to check if the given vectors are orthogonal-if their dotProduct is zero
		if(isZero(up.dotProduct(to))==false)
			throw new IllegalArgumentException("vectors are not orthogonal!");
		//else-orthogonal
		vRight=up.crossProduct(to).normalize();
		vUp=up.normalize();
		vTo=to.normalize();
		p0=new Point(p.getX(),p.getY(),p.getZ());
	}
	
	
	public Camera setVPSize(double w,double h)
	{
		this.width=w;
		this.height=h;
		return this;
	}
	
	
	public Camera setVUPDistance(double d)
	{
		this.distance=d;
		return this;
	}
	
	
	
	
	public Ray constructRay(int nX,int nY,int j,int i)
	{
		return null;//for now!!
	}
	
	
	
}
