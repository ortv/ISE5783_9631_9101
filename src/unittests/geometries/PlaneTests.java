/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.Plane;
import geometries.Polygon;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * @author AAA
 *
 */
class PlaneTests {
	
	/** Test method for {@link geometries.Plane#Plane(primitives.Point...)}. */
	   @Test
	   public void testConstructor() {
		  // ============ Equivalence Partitions Tests ==============
		  //TC01:test to create a plane from 3 points
		   try
		   {
			   new Plane(new Point(1,2,3),new Point (1,1,1),new Point(2,4,6));
		   }
		   catch (IllegalArgumentException e) {
		         fail("Failed constructing a correct plane");
		      }
		   //the ctor takes 3 points and create a plane that includes a normal vector that was created from the 3 points-we just need to check that the normal is correct in testgetNormal
		   
			// =============== Boundary Values Tests ==================
		   //TC02: the first and the second points converge(connected)
		   assertThrows(IllegalArgumentException.class, 
                   () -> new Plane(new Point(1, 1, 1), new Point(1, 1, 1), new Point(3, 4, 5)), 
                   "Constructed a Plane with two converge(connected) points");
		   //TC03: all the points on the same line
		   assertThrows(IllegalArgumentException.class, 
                   () -> new Plane(new Point(1, 1, 1), new Point(2, 2, 2), new Point(3, 3, 3)), 
                   "Constructed a Plane with 3 ponts that on the same line");
	   }   

	   	   
	/**
	 * Test method for {@link geometries.Plane#getNormal()}.
	 */
	@Test
	void testGetNormal() {
	      // ============ Equivalence Partitions Tests ==============
		//TC01:test if get the correct result-the length of the normal vector should be 1
		Plane p=new Plane(new Point(1,2,3),new Point (1,1,1),new Point(2,4,6));
		assertEquals(1, p.getNormal().length(),"ERROR: getNormal()  does not work correctly-should be 1!");
		
	}
	   
	/**
	 * Test method for {@link geometries.Plane#findIntsersections(Ray ray)}.
	 */
	@Test
	public void  testFindIntersectionPoints()
	{
	      // ============ Equivalence Partitions Tests ==============
		//============ray neither orthogonal nor parallel to plane============
		//TC01:ray intersects plane(1 point)
		Plane p1=new Plane(new Point(0,6,0), new Vector(2, -1, 3));
		Ray r1=new Ray(new Vector(1,0,-1), new Point(2,1,3));
		List<Point>result1=p1.findIntsersections(r1);
		assertEquals(1,result1.size(),"wrong number of intersections");
		assertEquals(new Point(20,1,-15),result1.get(0),"wrong value of point intersection");
		//TC02:ray doesn't intersect plane(0 points)
		Plane p2=new Plane(new Point(0,0,7), new Vector(2, 3, 1));
		assertNull(p2.findIntsersections(new Ray(new Vector(3,1,-1),new Point(1,2,3))),"wrong number of intersections-should be zero!" );
		// =============== Boundary Values Tests ==================
		//===============ray parallel to the plane
		//TC03:ray is included in plane(0-infinity points)
		Plane p3=new Plane(new Point(0,0,7),new Vector(2,3,1));
		assertNull(p3.findIntsersections(new Ray(new Vector(1,1,1),new Point(0,-1,10))),"wrong number of intersections-should be zero!" );
		//TC04:ray doesn't included in plane(0 points)
		Plane p4=new Plane(new Point(0,0,7), new Vector(-1,0,1));
		assertNull(p4.findIntsersections(new Ray(new Vector(1,1,1),new Point(0,-1,10))),"wrong number of intersections-should be zero!" );
		//=============== ray orthogonal to the plane
		//TC05:ray starts before the plane 
		Plane p5=new Plane(new Point(-0.5, -0.5, 0), new Point(1, 0, 0), new Point(0, 1, 0));
		Ray r5=new Ray(new Vector(0, 0, -1),new Point(0, 1, 1));
		List<Point>result5=p5.findIntsersections(r5);
		assertEquals(1, result5.size(),"wrong number of points");
		//TC06:ray starts in the plane
		Plane p6=new Plane(new Point(0,0,5),new Vector(1,2,1));
		assertNull(p6.findIntsersections(new Ray(new Vector(1,-1,1),new Point(3,-1,4))),"wrong number of intersections-should be zero!" );
		//TC07:ray starts after the plane
		Plane p7=new Plane(new Point(0,0,5),new Vector(3,-2,1));
		assertNull(p7.findIntsersections(new Ray(new Vector(1,2,1),new Point(2,0,8))),"wrong number of intersections-should be zero!" );
		//============================================= 
		//TC08:ray neither orthogonal nor parallel to plane and begins at the plane(0 points)
		Plane p8=new Plane(new Point(1,-1,1),new Vector(1,-2,1));
		assertNull(p8.findIntsersections(new Ray(new Vector(1,0,0),new Point(0,-2,0))),"wrong number of intersections-should be zero!" );
		//TC09:ray neither orthogonal nor parallel to plane and begins at the same point the the plane was defined(1 point)
		Plane p9=new Plane(new Point(1,2,1),new Vector(2,-1,1));
		Ray r9=new Ray(new Vector(2,1,-1), new Point(1,2,1));
		assertNull(p9.findIntsersections(r9),"wrong number of intersections-should be zero!" );

	}


}
