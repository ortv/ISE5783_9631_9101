package primitives;
import static primitives.Util.isZero;

import java.lang.Math;

import javax.print.attribute.standard.MediaSize.Other;
/**
 * Vector class that inherit from Point
 * @author AAA
 *
 */
public class Vector extends Point
{
	/**
	 * ctor for Vector class that gets 3 double numbers
	 * @param x num1
	 * @param y num2
	 * @param z num3
	 */
	public Vector(double x, double y, double z) 
	{
		super(x, y, z);
		// TODO Auto-generated constructor stub
		if(xyz.equals(Double3.ZERO))
			throw new IllegalArgumentException("cant use a zero vector!");
	}
	/**
	 * ctor for Vector class that gets number of Double3 that consists of 3 double numbers
	 * @param p
	 */
	public Vector(Double3 p) 
	{
		super(p);// TODO Auto-generated constructor stub
		if(p.equals(Double3.ZERO))
			throw new IllegalArgumentException("cant use a zero vector!");
	}
	/**
	 * add between 2 vectors and return the new vector of the result
	 */
	public Vector add(Vector v)
	{
		return new Vector(this.xyz.add(v.xyz));
	}
	/**
	 * scale vector and number
	 * @param num
	 * @return new vector of the result
	 */
	public Vector scale(double num)
	{
		return new Vector(this.xyz.scale(num));
	}
	/**
	 * cross product between  two vector
	 * @param v the given vector
	 * @return new vector of the result
	 */
	public Vector crossProduct(Vector v)
	{
		return new Vector(this.xyz.d2*v.xyz.d3-this.xyz.d3*v.xyz.d2,
				this.xyz.d3*v.xyz.d1-this.xyz.d1*v.xyz.d3,
				this.xyz.d1*v.xyz.d2-this.xyz.d2*v.xyz.d1);
	}
	/**
	 * calculates length of vector without squrt
	 * @return the length
	 */
	public double lengthSquared()
	{
		return (this.xyz.d1*this.xyz.d1)+(this.xyz.d2*this.xyz.d2)+(this.xyz.d3*this.xyz.d3);
	}
	/**
	 * calculate the actual length of vector
	 * @return length
	 */
	public double length()
	{
		return Math.sqrt(this.lengthSquared());
	}
	/**
	 * calculate normal vector for the current vector
	 * @return normal vector
	 */
	public Vector normalize()
	{
		double len=this.length();
		return new Vector(this.xyz.d1/len,this.xyz.d2/len,this.xyz.d3/len);
	}
	@Override
	public String toString() {
		return "Vector [xyz=" + xyz + "]";
	}
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		      if (this == obj) return true;
		      if (obj instanceof Vector other)
		        return this.xyz.equals(other.xyz);
		      return false;
	}
	/**
	 * calculates dot product for vector
	 * @param v the given vector
	 * @return the result-double number
	 */
	public double dotProduct(Vector v)
	{
		return(this.xyz.d1*v.xyz.d1+this.xyz.d2*v.xyz.d2+this.xyz.d3*v.xyz.d3);
	}
}
