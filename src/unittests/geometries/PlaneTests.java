/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Plane;
import geometries.Polygon;
import primitives.Point;

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
		   //TC02: the first and the second points converge
		   assertThrows(IllegalArgumentException.class, 
                   () -> new Plane(new Point(1, 2, 3), new Point(2, 4, 6), new Point(0, 2, 2)), 
                   "Constructed a Plane with two converge points");
		   //TC03: all the points on the same line
		   assertThrows(IllegalArgumentException.class, 
                   () -> new Plane(new Point(1, 2, 3), new Point(2, 4, 6), new Point(4, 8, 12)), 
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


}
