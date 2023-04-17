package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere extends RadialGeometry{
	private Point center;
	
	
	public Point getCenter() {
		return center;
	}

	public double getRadius() {
		return radius;
	}

	public Sphere(double rad,Point p) {
		super(rad);
		// TODO Auto-generated constructor stub
		this.center=p;
	}

	@Override
	   public Vector getNormal(Point p) {
			//p is a point on the sphere
			return  (p.subtract(center).normalize());
		}
}
