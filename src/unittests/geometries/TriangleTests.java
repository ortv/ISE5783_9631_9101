/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Plane;
import primitives.Point;

/**
 * @author AAA
 *
 */
class TriangleTests {

	/**
	 * Test method for {@link geometries.Triangle#Triangle(primitives.Point[])}.
	 */
	@Test
	void testTriangle() {
		fail("Not yet implemented");
	}
	/////@#$%^&*()_56

	/**
	 * Test method for {@link geometries.Polygon#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
		 // ============ Equivalence Partitions Tests ==============
		//TC01:test if get the correct result-the length of the normal vector should be 1
		Plane p=new Plane(new Point(1,2,3),new Point (1,1,1),new Point(2,4,6));
		assertEquals(1, p.getNormal().length(),"ERROR: getNormal()  does not work correctly-should be 1!");
			}

}
