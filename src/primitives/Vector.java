package primitives;
import java.lang.Math;

public class Vector extends Point
{

	public Vector(double x, double y, double z) 
	{
		super(x, y, z);
		// TODO Auto-generated constructor stub
		/*k855#$%^&*()_)O(*&^%????????????*/
		if(new Double3(x,y,z).equals(Double3.ZERO))
			throw new IllegalArgumentException("cant use a zero vector!");
	}
	public Vector(Double3 p) 
	{
		super(p);// TODO Auto-generated constructor stub
		if(p.equals(Double3.ZERO))
			throw new IllegalArgumentException("cant use a zero vector!");
	}
	public Vector add(Vector v)
	{
		return new Vector(this.xyz.add(v.xyz));
	}
	public Vector scale(double num)
	{
		return new Vector(this.xyz.scale(num));
	}
	public Vector crossProduct(Vector v)
	{
		return new Vector(this.xyz.d2*v.xyz.d3-this.xyz.d3*v.xyz.d2,this.xyz.d3*v.xyz.d1-this.xyz.d1*v.xyz.d3,this.xyz.d1*v.xyz.d2-this.xyz.d2*this.xyz.d2*v.xyz.d1);
	}
	public double lengthSquared()
	{
		return (this.xyz.d1*this.xyz.d1)+(this.xyz.d2*this.xyz.d2)+(this.xyz.d3*this.xyz.d3);
	}
	public double length()
	{
		return Math.sqrt(this.lengthSquared());
	}
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
		return super.equals(obj);
	}
	public double dotProduct(Vector v)
	{
		return(this.xyz.d1*v.xyz.d1+this.xyz.d2*v.xyz.d2+this.xyz.d3*v.xyz.d3);
	}
}
