package geometries;
import java.util.List;

import primitives.Double3;
import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

public class Plane implements Geometry{
	final private Point p0;
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
		normal=v.normalize();
	}
	@Override
	public Vector getNormal(Point p) {
		// TODO Auto-generated method stub
		return normal;
	}
	/*
	 * find all the intersection points between a ray and a plane
	* @param ray is the given ray to intersect with
	 * @return list of intersection points-if there are not, return null
	 * */
	public List<Point> findIntsersections(Ray ray)
	{
		double tmp=Util.alignZero(normal.dotProduct(ray.getDir()));//if it zero-either orthogonal or parallel
		if(tmp==0)
		{//orthogonal
			
		}
		//otherwise
		double tmp1=normal.dotProduct(p0.subtract(ray.getP0()));
		double t=tmp1/tmp;
		if(t<0)
		{
			return null;
		}
		if(t>0)
		{
			Point p1=ray.getP0().add(ray.getDir().scale(t));
			return List.of(p1);
		}
		if(t==0)
		{//if(p0-ray.p0)*normal=0 parallel and included in the plane
			//otherwise,only parallel.
			//in both options return null-no intersections
			return null;
		}
		return null;
	}
	
	
	
}
