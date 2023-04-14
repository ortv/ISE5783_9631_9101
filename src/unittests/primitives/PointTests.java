/**
 * 
 */
package unittests.primitives;
import static primitives.Util.isZero;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.Point;
import primitives.Vector;

/**
 * @author AAA
 *
 */
class PointTests {

	/**
	 * Test method for {@link primitives.Point#subtract(primitives.Point)}.
	 */
	@Test
	void testSubtract() {
		Point p1 = new Point(1, 2, 3);
		Point p=new Point(1, 4, 0);
		// ============ Equivalence Partitions Tests ==============
		//TC01:test if gets a proper result
		assertEquals(Math.sqrt(13), p1.subtract(p).length(),"ERROR: Point subtruct() Vector does not work correctly");
		// =============== Boundary Values Tests ==================
		//TC02: is throw an exception when Zero
	      assertThrows(IllegalArgumentException.class, ()->p1.subtract(p1),"\"ERROR: Point subruct() -itself does not throw an exception\"");
	}


	/**
	 * Test method for {@link primitives.Point#add(primitives.Vector)}.
	 */
	@Test
	void testAdd() {
		Point p1 = new Point(1, 2, 3);
		Vector p=new Vector(1, 4, 0);
		// ============ Equivalence Partitions Tests ==============
		//TC01:test if gets a proper result
		assertEquals(Math.sqrt(17),p1.distance(p1.add(p)),"ERROR: Point add()  does not work correctly");
		// =============== Boundary Values Tests ==================
		//TC02:test add with opssite values-should be Zero
		Vector v =new Vector(-1,-2,-3);
		assertTrue(new Point(0,0,0).equals(p1.add(v)),"ERROR:Point add()  does not work correctly-should be zero");
	}

	/**
	 * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
	 */
	@Test
	void testDistanceSquared() {
		// ============ Equivalence Partitions Tests ==============
		//TC01:test if gets a proper result
		Point p1=new Point(4,5,6);
		Point p2=new Point(1,2,3);
		assertEquals(27, p1.distanceSquared(p2),"ERROR: Point DistanceSquared()  does not work correctly");
		// =============== Boundary Values Tests ==================
		//TC02:distance betwenn the same point-should be zero
		assertTrue(isZero(p1.distanceSquared(p1)), "ERROR: Point DistanceSquared()  does not work correctly");	
		
	}
	
	

	/**
	 * Test method for {@link primitives.Point#distance(primitives.Point)}.
	 */
	@Test
	void testDistance() {
		// ============ Equivalence Partitions Tests ==============
				//TC01:test if gets a proper result
				Point p1=new Point(4,5,6);
				Point p2=new Point(1,2,3);
				assertEquals(3*Math.sqrt(3), p1.distance(p2),"ERROR: Point Distance()  does not work correctly");
				// =============== Boundary Values Tests ==================
				//TC02:distance betwenn the same point-should be zero
				assertTrue(isZero(p1.distance(p1)), "ERROR: Point Distance()  does not work correctly");
				
	}

}
