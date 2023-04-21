/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import geometries.Tube;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * @author AAA
 *
 */
class TubeTests {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
		 // ============ Equivalence Partitions Tests ==============
		//TC01:test if a proper result
		Vector v=new Vector(1, 2, 3);
		Point p=new Point(7,4,3);
		Point pC=new Point(9.2, 4, 0);//random point
		Ray r=new Ray(v, p);
		double rad=6.7;
		Tube tu=new Tube(rad, r);
		assertTrue(p.distance(pC)<=rad, "ERROOR: point not in Tube");//check if the point in tube
		assertEquals(1, tu.getNormal(pC).length(),0.0001,"ERROR: getNormal()  does not work correctly-should be 1!");
	    // =============== Boundary Values Tests ==================
		//TC02:test  connection of a point to the axis of the sphere creates a right angle with the axis
		Tube t1=new Tube(5,new Ray(new Vector(0,0,1), new Point(1,1,1)));
		Point p2=new Point(2, 0, 0);
		assertEquals(1, t1.getNormal(p2).length(),"ERROR: getNormal()  does not work correctly-should be 1!");	
	}
	   
	   /**
		 * Test method for {@link geometries.Tube#findIntsersections(Ray ray)}.
		 */
		@Test
		public void  testFindIntersectionPoints()
		{
			
		}

}
