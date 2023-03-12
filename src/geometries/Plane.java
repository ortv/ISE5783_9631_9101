package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry{
	public Point getP0() {
		return p0;
	}
	public Vector getNormal() {
		return normal;
	}
	private Point p0;
	private Vector normal;
	
	public Plane(Point x,Point y,Point z)
	{
		p0=x;//saving one of the points as the current point... from the instructions...
		normal=null;//for now... as the instructions...  will be completed in the next stage
	}
	public Plane(Point p,Vector v)
	{
		p0=p;
		normal=v;
		//////not sure.............
	}
	@Override
	public Vector getNormal(Point p) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
