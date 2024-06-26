package geometries;

import java.util.List;


import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import primitives.Util;
import geometries.Intersectable.GeoPoint;


public class Triangle extends Polygon {

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public Triangle(Point p1, Point p2, Point p3) {
		super(p1, p2, p3);
		// TODO Auto-generated constructor stub
	}

	public List<Point> findIntsersections(Ray ray) {

		Vector rayDirection = ray.getDir();

		// point of ray p0
		Point p0 = ray.getP0();

		// 3 points of 3 triangle vertex
		Point p1 = vertices.get(0);
		Point p2 = vertices.get(1);
		Point p3 = vertices.get(2);

		// calculate the direction from any vertex to ray p0
		Vector vector1 = p1.subtract(p0);
		Vector vector2 = p2.subtract(p0);
		Vector vector3 = p3.subtract(p0);

		
		// calculate the cross product between 3 vectors
		Vector crossProduct1 = vector1.crossProduct(vector2);
		Vector crossProduct2 = vector2.crossProduct(vector3);
		Vector crossProduct3 = vector3.crossProduct(vector1);

		// calculate if the dot product between ray direction and vectors are positive
		// or negative
		double dotProduct1 = rayDirection.dotProduct(crossProduct1);
		double dotProduct2 = rayDirection.dotProduct(crossProduct2);
		double dotProduct3 = rayDirection.dotProduct(crossProduct3);

		// check if all dot product result is with same sign
		if ((dotProduct1 > 0 && dotProduct2 > 0 && dotProduct3 > 0)
				|| (dotProduct1 < 0 && dotProduct2 < 0 && dotProduct3 < 0)) {
			return plane.findIntsersections(ray);
		}

		return null;

	}
	public List<GeoPoint>findGeoIntersectionsHelper(Ray ray)
	{
		var intersections=plane.findGeoIntersectionsHelper(ray);
		if(intersections==null)
			return null;//no intersections
		Vector rayDirection = ray.getDir();

		// point of ray p0
		Point p0 = ray.getP0();

		// 3 points of 3 triangle vertex
		Point p1 = vertices.get(0);
		Point p2 = vertices.get(1);
		Point p3 = vertices.get(2);

		// calculate the direction from any vertex to ray p0
		Vector vector1 = p1.subtract(p0);
		Vector vector2 = p2.subtract(p0);
		Vector vector3 = p3.subtract(p0);

		
		// calculate the cross product between 3 vectors
		Vector crossProduct1 = vector1.crossProduct(vector2);
		Vector crossProduct2 = vector2.crossProduct(vector3);
		Vector crossProduct3 = vector3.crossProduct(vector1);

		// calculate if the dot product between ray direction and vectors are positive
		// or negative
		double dotProduct1 = rayDirection.dotProduct(crossProduct1);
		double dotProduct2 = rayDirection.dotProduct(crossProduct2);
		double dotProduct3 = rayDirection.dotProduct(crossProduct3);

		// check if all dot product result is with same sign
		if ((dotProduct1 > 0 && dotProduct2 > 0 && dotProduct3 > 0)
				|| (dotProduct1 < 0 && dotProduct2 < 0 && dotProduct3 < 0)) {
			intersections.get(0).geometry=this;
			return intersections;
		}

		return null;
	}
}
