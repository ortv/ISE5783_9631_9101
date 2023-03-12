package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube{
	private double height;
	
	
	
	
	
	
	public double getHeight() {
		return height;
	}

	public Cylinder(double rad, Ray r) {
		super(rad, r);
		// TODO Auto-generated constructor stub
	}

	@Override
	   public Vector getNormal(Point point) {return null;}
}
