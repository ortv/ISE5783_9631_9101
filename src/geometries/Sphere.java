package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere extends RadialGeometry{
	private Point center;
	private double radius;///////////////
	
	
	public Point getCenter() {
		return center;
	}

	public double getRadius() {
		return radius;
	}

	public Sphere(double rad) {
		super(rad);
		// TODO Auto-generated constructor stub
	}

	@Override
	   public Vector getNormal(Point point) {return null;}
}
