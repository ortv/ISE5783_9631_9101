package primitives;
import java.lang.Math;
import java.util.Objects;
public class Point {
 public Point(double x, double y, double z)
 {
	 xyz= new Double3(x,y,z);
}
 public Point(Double3 x)
 {
	 xyz= new Double3(x.d1,x.d2,x.d3);
 }
 /*
  * gets a point and  subtract   from the current point by sending to  an existing Double3 function-return a vector
 */
 public Vector subtract(Point p)
 {
	return new Vector(xyz.subtract(p.xyz));	
 }
 public Point add(Vector v)
 {
	 return new Point(xyz.add(v.xyz));
 }
 /*
  * return the distance between two points in a pow
 */
 public double distanceSquared(Point p)
 {
	 return ((p.xyz.d1-xyz.d1)*(p.xyz.d1-xyz.d1)+(p.xyz.d2-xyz.d2)*(p.xyz.d2-xyz.d2)+(p.xyz.d3-xyz.d3)*(p.xyz.d3-xyz.d3));
 }
 /*
  * return the actual distance between two points
  * */
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
	return xyz.equals(obj);
}
Double3 xyz;
 
}