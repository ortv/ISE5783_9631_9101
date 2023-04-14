package geometries;

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
		Vector v1=y.subtract(x);//y-x
		Vector v2=z.subtract(x);//z-y
		normal=v1.crossProduct(v2).normalize();
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
