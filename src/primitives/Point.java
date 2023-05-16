package primitives;
import java.lang.Math;
import java.util.Objects;

import org.junit.validator.PublicClassValidator;
/**
 * a Point class
 * @author AAA
 *
 */
public class Point {
	/**
	 * ctor for Point class how gets 3 double numbers
	 * @param x num1	
	 * @param y num2
	 * @param z num3
	 */
 public Point(double x, double y, double z)
 {
	 xyz= new Double3(x,y,z);
 }
 public double getX()
 {
	 return xyz.d1;
 }
 public double getY()
 {
	 return xyz.d2;
 }
 public double getZ()
 {
	 return xyz.d3;
 }
 /**
  * ctor for Point class that gets a number of Double3-that consists of 3 double numbers
  * @param x
  */
 public Point(Double3 x)
 {
	 xyz= new Double3(x.d1,x.d2,x.d3);
 }
/**
 * subtract between two points
 * @param p is the given point
 * @return a new vector-point which is the result of the subtraction
 */
 public Vector subtract(Point p)
 {
	return new Vector(xyz.subtract(p.xyz));	
 }
 /**
  * add between two points(vector is kind of point)
  * @param v is the given vector
  * @return a new point which is the result of the subtraction
  */
 public Point add(Vector v)
 {
	 return new Point(xyz.add(v.xyz));
 }
 /**
  * calculate the distance between two points-without squrt
  * @param p the given point to calculate the distance from
  * @return distance in a pow
  */
 public double distanceSquared(Point p)
 {
	 return ((p.xyz.d1-xyz.d1)*(p.xyz.d1-xyz.d1)+(p.xyz.d2-xyz.d2)*(p.xyz.d2-xyz.d2)+(p.xyz.d3-xyz.d3)*(p.xyz.d3-xyz.d3));
 }
 /**
  * calculate the distance between two points
  * @param p given point to calculate the distance from
  * @return the actual distance between two points
  */
 
 public double distance(Point p)
 {
	 return (Math.sqrt(distanceSquared(p)));
 }
 @Override
 public String toString()
 {
	 return xyz.toString();
 }
 @Override
public int hashCode() {
	return Objects.hash(xyz);
}
@Override
public boolean equals(Object obj) {	
	 if (this == obj) return true;
     if (obj instanceof Point other)
       return this.xyz.equals(other.xyz);
     return false;
}
final Double3 xyz;
 
}