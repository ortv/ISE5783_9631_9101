package primitives;
import static primitives.Util.*;

import java.util.List;
/**
 * The Ray class represents a ray in 3D space.
 * It consists of a starting point (p0) and a direction vector (dir).
 */

import geometries.Intersectable.GeoPoint;
public class Ray {
	private Point p0;
	private Vector dir;
	@Override
	public String toString() {
		return "Ray [p0=" + p0 + ", dir=" + dir + "]";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
	    if (this == obj) return true;
		if (obj instanceof Ray other)
			return (this.p0.equals(other.p0)&&this.dir.equals(other.dir));
		return false;
	}

	
	/**
     * Constructs a Ray object with the given direction vector and starting point.
     *
     * @param v The direction vector of the ray.
     * @param p The starting point of the ray.
     * @throws IllegalArgumentException if the starting point is the zero vector.
     */
	public Ray(Vector v,Point p)
	{
		if(p.equals(Double3.ZERO))
			throw new IllegalArgumentException("cant use a zero vector!");
		p0=new Point(p.xyz);//p0=p???
		//normalizing the vector
		dir=new Vector(v.xyz).normalize();	
	}
	/**
     * Returns the starting point of the ray.
     *
     * @return The starting point of the ray.
     */
	public Point getP0() {
		return p0;
	}
	/**
     * Returns the direction vector of the ray.
     *
     * @return The direction vector of the ray.
     */
	public Vector getDir() {
		return dir;
	}
	/**
     * Returns the point on the ray at the given scalar value.
     *
     * @param t The scalar value.
     * @return The point on the ray at the given scalar value.
     */
	public Point getPoint(double t) 
	{
		if(isZero(t))//if t is almost zero-return the initial point of ray
			return p0;
		return p0.add(dir.scale(t));
	}
	/**
     * Finds the closest point from a list of points to the starting point of the ray.
     *
     * @param lst The list of points to check.
     * @return The closest point from the list to the starting point of the ray.
     */
	public Point findClosestPoint(List<Point>lst)
	{
			 return lst == null || lst.isEmpty() ? null
			 : finfClosestGeoPoint(lst.stream().map(p -> new GeoPoint(null, p)).toList()).point;			
	}
	public GeoPoint finfClosestGeoPoint(List<GeoPoint>lst)
	{
		if(lst.size()==0)//empty list
			return null;
		double minDis=p0.distance(lst.get(0).point);//
		GeoPoint minPoint=lst.get(0);
		for (GeoPoint g_point : lst)//runs on all the points in lst
			//and check who has the smallest distance between itself the p0-head of ray
		{
			if(p0.distance(g_point.point)<minDis)//if we found a smaller distance
			{
				//change to the closer point
				minDis=p0.distance(g_point.point);
				minPoint=g_point;
			}
		}
		return minPoint;//return the closest point
	}
	
}