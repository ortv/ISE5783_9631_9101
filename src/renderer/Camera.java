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
