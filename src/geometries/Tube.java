package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry {

	Ray axisRay;
	public Tube(double rad,Ray r) {
		super(rad);
		// TODO Auto-generated constructor stub
		axisRay=r;///////////652#$%^
	}
	@Override
	   public Vector getNormal(Point point) {return null;}
	public Ray getAxisRay() {
		return axisRay;
	}

}
