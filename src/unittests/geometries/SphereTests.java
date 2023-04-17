/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import geometries.Sphere;
import primitives.Point;

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

}
