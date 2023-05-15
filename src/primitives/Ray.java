package primitives;
import static primitives.Util.*;

import java.util.List;
public class Ray {

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

	
	private Point p0;
	private Vector dir;
	public Ray(Vector v,Point p)
	{
		if(p.equals(Double3.ZERO))
			throw new IllegalArgumentException("cant use a zero vector!");
		p0=new Point(p.xyz);//p0=p???
		//normalizing the vector
		dir=new Vector(v.xyz).normalize();	
	}
	
	public Point getP0() {
		return p0;
	}

	public Vector getDir() {
		return dir;
	}
	/** 
	 * return the point that on the ray
	 * @param t is the scalar
	 * @return the result-point
	 */
	public Point getPoint(double t) 
	{
		if(isZero(t))//if t is almost zero-return the initial point of ray
			return p0;
		return p0.add(dir.scale(t));
	}
	
	public Point findClosestPoint(List<Point>lst)
	{
		if(lst.size()==0)//empty list
			return null;
		double minDis=p0.distance(lst.get(0));//
		Point minPoint=lst.get(0);
		for (Point point : lst)//runs on all the points in lst
			//and check who has the smallest distance between itself the p0-head of ray
		{
			if(p0.distance(point)<minDis)//if we found a smaller distance
			{
				//change to the closer point
				minDis=p0.distance(point);
				minPoint=point;
			}
		}
		return minPoint;//return the closest point
	}
	
	
}