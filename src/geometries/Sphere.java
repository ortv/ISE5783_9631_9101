package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
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
	/**
	 * return a list of intersection points between ray and sphere
	 * @param ray is the given ray to intersect with
	 * @return list of intersection points-if there are not, return null */
	public List<Point> findIntsersections(Ray ray)
	{
		//if the center equals to the initial point-cant build a vector(zero vector) but still, there is intersection!!!
		if(center.equals(ray.getP0()))
		{
			Point p=ray.getP0().add(ray.getDir().scale(radius));
			return List.of(p);
		}
		
		Vector u=(center.subtract(ray.getP0()));//center-initial point of ray,normalize
		double tm=ray.getDir().dotProduct(u);//dot product between the direction vector of ray and the vector above,return double number
		if(Util.isZero(tm))//orthogonal
			return null;
		
		double d=Math.sqrt(u.dotProduct(u)-tm*tm);//using pitagoras, finds the distance
		if(Util.alignZero(d)>=radius)//tangent or out of the sphere-no intersections,return null
			return null;
		//otherwise
		
		double th=Math.sqrt(radius*radius-d*d);//using pitagoras
		double t1=Util.alignZero(tm+th);
		double t2=Util.alignZero(tm-th);
		//use t1,2 only if>0
		if(t1>0&&t2>0)//if both are positive
		{
			Point p1=ray.getP0().add(ray.getDir().scale(t1));
			Point p2=ray.getP0().add(ray.getDir().scale(t2));
			if(Util.alignZero(p1.distance(center))<radius&&(Util.alignZero(p2.distance(center))<radius))//2 intersections
				List.of(p1,p2);

			if(Util.alignZero(p1.distance(center))<radius&&(Util.alignZero(p2.distance(center))>=radius))//1 intersections
			{
				if(ray.getP0().distance(p2)>0)//if initial point before the ray or inside the ray and its vector
					//goes outside-need to caluculate the point that on the sphere-dis=readius
					return List.of(p1,p2);
				return List.of(p1);
			}
			
			if(Util.alignZero(p1.distance(center))>=radius&&(Util.alignZero(p2.distance(center))<radius))//1 intersections
			{
				if(ray.getP0().distance(p1)>0)//if initial point before the ray or inside the ray and its vector
					//goes outside-need to caluculate the point that on the sphere-dis=readius
					return List.of(p1,p2);
				return List.of(p1);
			}
			if(Util.alignZero(p1.distance(center))>=radius&&(Util.alignZero(p2.distance(center))>=radius))//2 intersections
			{
				if(ray.getP0().distance(p1)>0&&(ray.getP0().distance(p2)>0))//if initial point before the ray or inside the ray and its vector
					//goes outside-need to caluculate the point that on the sphere-dis=readius
					return List.of(p1,p2);
				return null;
			}
			//otherwise-no intersections
			return null;

		}
		if(t1>0&&t2<=0)//only one is positive
		{
			Point p1=ray.getP0().add(ray.getDir().scale(t1));
			if(Util.alignZero(p1.distance(center))<radius)//1 intersections
				return List.of(p1);

			if((Util.alignZero(p1.distance(center))==radius)&&(ray.getP0().distance(p1)>0))//if initial point before the ray or inside the ray and its vector
					//goes outside-need to caluculate the point that on the sphere-dis=readius
				return List.of(p1);

			return null;
		}
		if(t2>0&&t1<=0)//only one is positive
		{
			Point p2=ray.getP0().add(ray.getDir().scale(t2));
			if(Util.alignZero(p2.distance(center))<radius)//1 intersections
				return List.of(p2);

			if((Util.alignZero(p2.distance(center))==radius)&&(ray.getP0().distance(p2)>0))//if initial point before the ray or inside the ray and its vector
				//goes outside-need to caluculate the point that on the sphere-dis=readius
				return List.of(p2);
			
			return null;
		}	
		//else-no positive
		return null;
	}
}























