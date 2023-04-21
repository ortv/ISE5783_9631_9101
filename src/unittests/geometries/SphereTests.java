/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import geometries.Sphere;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * @author AAA
 *
 */
class SphereTests {

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
		 // ============ Equivalence Partitions Tests ==============
		//TC01:test if a proper result
		Point p1=new Point(1, 2, 3);
		double rad=5.5;
		Sphere sp=new Sphere(rad, p1);
		Point pC=new Point(4,2,7);
		assertTrue(p1.distance(pC)<=rad, "ERROOR: point not in Sphere");//check if the point in sphere
		assertEquals(1, sp.getNormal(pC).length(),"ERROR: getNormal()  does not work correctly-should be 1!");
	}
	   
	/**
	 * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
	 */
	 @Test
	 public void testFindIntersections() {
	 Sphere sphere = new Sphere(1d, new Point (1, 0, 0));
	 // ============ Equivalence Partitions Tests ==============
	 // TC01: Ray's line is outside the sphere (0 points)
	 assertNull(sphere.findIntsersections(new Ray( new Vector(1, 1, 0),new Point(-1, 0, 0))),"Ray's line out of sphere");
	 // TC02: Ray starts before and crosses the sphere (2 points)
	 Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
	 Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
	 List<Point> result = sphere.findIntsersections(new Ray(new Vector(3, 1, 0),new Point(-1, 0, 0)));
	 assertEquals(2, result.size(), "Wrong number of points");
	 if (result.get(0).getX() > result.get(1).getX())
		 result = List.of(result.get(1), result.get(0));
	 assertEquals(List.of(p1, p2), result, "Ray crosses sphere");
	 // TC03: Ray starts inside the sphere (1 point)
	 List<Point> result1 = sphere.findIntsersections(new Ray(new Vector(3, 1, 0),new Point(0.333, 0, 0)));
	 assertEquals(1, result1.size(),"Wrong number of points");//the beginning point of ray from inside the sphere
	 // TC04: Ray starts after the sphere (0 points)
	 Sphere s1=new Sphere(1d,new Point(-20,5,7));
	 assertNull(s1.findIntsersections(new Ray( new Vector(1, 1, 1),new Point(-2, 0, 1))),"Ray's line starts after sphere");
	 // =============== Boundary Values Tests ==================
	 // **** Group: Ray's line crosses the sphere (but not the center)
	 // TC11: Ray starts at sphere and goes inside (1 points)
	 Sphere s2=new Sphere(3, new Point(-1,2,3));
	 Ray ray=new Ray(new Vector(-1,0,0), new Point(2,2,3));
	 List<Point> r2=s2.findIntsersections(ray);
	 assertEquals(1, r2.size(),"Wrong number of points");
	 assertEquals(new Point(-4,2,3),r2.get(0),"Wrong intersection point");//check if the calculated point id as should be
	 // TC12: Ray starts at sphere and goes outside (0 points)
	 Sphere s3=new Sphere(1, new Point(1,1,1));
	 Ray ray3=new Ray(new Vector(-1,0,0), new Point(2,0,0));
	 assertNull(s3.findIntsersections(ray3),"Ray's line starts at sphere and goes outside");
	 // **** Group: Ray's line goes through the center
	 // TC13: Ray starts before the sphere (2 points)
	 Sphere s4=new Sphere(3, new Point(-1,2,3));
	 Ray ray4=new Ray(new Vector(-1, 0, 0), new Point(4,2,3));
	 List<Point> result4=s4.findIntsersections(ray4);
	 assertEquals(2, result4.size(),"Wrong number of points");	 
	 // TC14: Ray starts at sphere and goes inside (1 points)
	 //the same sphere as the last test
	 Ray r5=new Ray(new Vector(-1, 0, 0), new Point(2,2,3));
	 List<Point> result5=s4.findIntsersections(r5);
	 assertEquals(1, result5.size(),"Wrong number of points");	
	 assertEquals(new Point(-4,2,3), result5.get(0),"Wrong intersection point");
	 // TC15: Ray starts inside (1 points)
	 //the same sphere as the last test
	 Ray r6=new Ray(new Vector(-1,0,0), new Point(2,2,3));
	 List<Point> result6=s4.findIntsersections(r6);
	 assertEquals(1, result6.size(),"Wrong number of points");	
	 assertEquals(new Point(-4,2,3), result6.get(0),"Wrong intersection point");
	 // TC16: Ray starts at the center (1 points)
	 //the same sphere as the last test
	 Ray r7=new Ray(new Vector(-1,0,0), new Point(-1,2,3));
	 List<Point> result7=s4.findIntsersections(r7);
	 assertEquals(1, result7.size(),"Wrong number of points");	
	 assertEquals(new Point(-4,2,3), result7.get(0),"Wrong intersection point");
	 // TC17: Ray starts at sphere and goes outside (0 points)
	 //the same sphere as the last test
	 Ray r8=new Ray(new Vector(-1, 0, 0), new Point(-4,2,3));
	 assertNull(s4.findIntsersections(r8), "Ray's line starts at sphere and goes outside");
	 // TC18: Ray starts after sphere (0 points)
	 //the same sphere as the last test
	 assertNull(s4.findIntsersections(new Ray(new Vector(-1,0,0), new Point(-4,3,3))), "Ray's line starts after the sphere and goes outside");
	 // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
	 // TC19: Ray starts before the tangent point
	 //the same sphere as the last test
	 Ray r9=new Ray(new Vector(0,1,0), new Point(-4,1.5,3));
	 assertNull(s4.findIntsersections(r9), "Ray's line starts before the sphere and tangent");
	 // TC20: Ray starts at the tangent point
	//the same sphere as the last test
	 Ray r10=new Ray(new Vector(0, 1, 0), new Point(-4,2,3));
	 assertNull(s4.findIntsersections(r10), "Ray's line starts at the sphere and tangent");
	 // TC21: Ray starts after the tangent point
	//the same sphere as the last test
	 Ray r11=new Ray(new Vector(0, 1, 0), new Point(-4,3,3));
	 assertNull(s4.findIntsersections(r11), "Ray's line starts after the sphere and tangent");
	 // **** Group: Special cases
	 // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
	 //the same sphere as the last test
	 Ray r12=new Ray(new Vector(0, 1, 0), new Point(2,2,1));
	 assertNull(s4.findIntsersections(r12), "line is outside, ray is orthogonal to ray start to sphere's center line");
	 }
}
