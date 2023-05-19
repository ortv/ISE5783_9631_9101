package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry {

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	final Ray axisRay;
	public Tube(double rad,Ray r) {
		super(rad);
		// TODO Auto-generated constructor stub
		axisRay=r;///////////652#$%^
	}
	@Override
	   public Vector getNormal(Point p) {
		//need to find the center point
		//assuming p is inside the tube
		Vector v=p.subtract(this.axisRay.getP0());//p-p0(this-p)
		double t=this.axisRay.getDir().dotProduct(v);//dot product normal vector of ray*v
		v=v.scale(t);//t*v
		Point centerPoint=this.axisRay.getP0().add(v);//p0+t*v
		return (p.subtract(centerPoint).normalize());//return normal vector for tube
		}
	                                                                                              
	public Ray getAxisRay() {
		return axisRay;
	}
	public List<Point> findIntsersections(Ray ray)
	{
		return null;
	}
	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}
}
