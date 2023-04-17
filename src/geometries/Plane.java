package geometries;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry{
	private Point p0;
	private Vector normal;
	
	public Point getP0() {
		return p0;
	}
	
	public Vector getNormal() {
		return normal;
	}
	
	public Plane(Point x,Point y,Point z)
	{
		p0=x;//saving one of the points as the current point... from the instructions...
		if(x.equals(y)) {//if the first in the second points are connected-the same
			throw new IllegalArgumentException("cant build a plane-2 points are the same");
		}
		Vector v1=y.subtract(x);//y-x
		Vector v2=z.subtract(x);//z-y
		
		try {
			normal=v1.crossProduct(v2);//return a new vector
		}
		catch (IllegalArgumentException e) {//if its a zero vector-throws an exception
			throw e;
		}
		//otherwise...
		normal=normal.normalize();
		}
	
	public Plane(Point p,Vector v)
	{
		p0=p;
		normal=this.getNormal(p);//not implemented yet
	}
	@Override
	public Vector getNormal(Point p) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
