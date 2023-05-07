package primitives;
import static primitives.Util.*;
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
}