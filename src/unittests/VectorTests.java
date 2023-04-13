/**
 * 
 */
package unittests;
import primitives.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.lang.System.out;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static primitives.Util.isZero;

class VectorTests {

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	void testAddVector() {
	      Vector v1 = new Vector(1, 2, 3);
	      Vector v2=new Vector(-1, -2, -3);
		  // ============ Equivalence Partitions Tests ==============
	      //TC01:Test add vector-if throw an exception in case its a Zero vector(on the same line in opposite directions
	      assertThrows(IllegalArgumentException.class, ()->v1.add(v2),"\"ERROR: Vector + -itself does not throw an exception\"");
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	void testScale() {
		 // ============ Equivalence Partitions Tests ==============
		//TC01:Test of scaling a vector in Zero
		Vector v1 = new Vector(1, 2, 3);
	    assertThrows(IllegalArgumentException.class, ()->v1.scale(0),"\"ERROR: Vector scale -itself does not throw an exception\"");
		// =============== Boundary Values Tests ==================
	    //TC02:scaling a very small number
	    Vector vr=new Vector(0.0002,0.0004,0.0006);
	    assertEquals(vr.length(), v1.scale(0.0002).length() ,0.0001,"scale() wrong results");
	    //TC03:scaling a very large number
	    Vector vr2=new Vector(10000,20000,30000);
	    assertEquals(vr2.length(), v1.scale(10000).length() ,0.0001,"scale() wrong results");
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	void testCrossProduct() {
			 Vector v1 = new Vector(1, 2, 3);
			 // ============ Equivalence Partitions Tests ==============
			 Vector v2 = new Vector(0, 3, -2);
			 Vector vr = v1.crossProduct(v2);
			 // TC01: Test that length of cross-product is proper (orthogonal vectors taken
			 // for simplicity)
			 assertEquals(v1.length() * v2.length(), vr.length(), 0.00001, "crossProduct() wrong result length");
			 // TC02: Test cross-product result orthogonality to its operands
			 assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
			 assertTrue(isZero(vr.dotProduct(v2)), "crossProduct() result is not orthogonal to 2nd operand");
			// =============== Boundary Values Tests ==================
			 // TC11: test zero vector from cross-productof co-lined vectors
			 Vector v3 = new Vector(-2, -4, -6);
			 assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),"crossProduct() for parallel vectors does not throw an exception");
	}

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	void testLengthSquared() {
		Vector v1 = new Vector(1, 2, 3);
		 // ============ Equivalence Partitions Tests ==============
	    //TC01:test if len is zero
	    assertTrue(isZero(v1.lengthSquared() - 14), "ERROR: lengthSquared() wrong value(should be zero)");
		// =============== Boundary Values Tests ==================
	    //TC02:test if len of almost(a bit less) one is still less then 1
	    Vector v2=new Vector(0.499999,0.3999,0.1);
	    assertTrue(1>v2.lengthSquared(), "ERROR: lengthSquared() wrong value");
	    //TC03:test if len in between
	    Vector v3=new Vector(1,10,0);
	    assertTrue(99.9<v3.lengthSquared()&&v3.lengthSquared()<110.5, "ERROR: lengthSquared() wrong value");
	}
	      

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	void testLength() {
		Vector v1 = new Vector(1, 2, 3);
		 // ============ Equivalence Partitions Tests ==============
	    //TC01:test if len is zero
	    assertTrue(isZero(v1.length() - Math.sqrt(14)), "ERROR: length wrong value(should be zero)");
		// =============== Boundary Values Tests ==================
	    //TC02:test if len of almost(a bit less) one is still less then 1
	    Vector v2=new Vector(0.499999,0.3999,0.1);
	    assertTrue(1>v2.length(), "ERROR: length wrong value");
	    //TC03:test if len in between
	    Vector v3=new Vector(1,10,0);
	    assertTrue(9<v3.length()&&v3.length()<11, "ERROR: length wrong value");	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	void testNormalize() {
		 Vector v = new Vector(1, 2, 3);
	     Vector u = v.normalize();
		 // ============ Equivalence Partitions Tests ==============
	     //TC01:test that the result is correct
	     assertTrue(isZero(u.length() - 1), "ERROR: the normalized vector is not a unit vector");
		 // =============== Boundary Values Tests ==================
	     //TC02:test if tne normal vector is parallel
		 assertThrows(IllegalArgumentException.class, () -> v.crossProduct(u),"the normalized vector is not parallel to the original and does not throw an exception");
		 //TC03:test if the normalize vector and the original vector in the same directions
		 assertFalse(v.dotProduct(u)<0, "ERROR: the normalized vector is opposite to the original one");
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	void testDotProduct() {
		Vector v1 = new Vector(1, 2, 3);
	    Vector v2 = new Vector(-2, -4, -6);
	    Vector v3 = new Vector(0, 3, -2);
	    Vector norm=new Vector(1,1,1); 
		 // ============ Equivalence Partitions Tests ==============
	     //TC01:test if result is correct
	    assertEquals(-28, v1.dotProduct(v2),"ERROR: dotProduct() wrong value");
		// =============== Boundary Values Tests ==================
	    //TC02:test orthiginal vectors that should be equal zero
	    assertTrue(isZero(v1.dotProduct(v3)), "ERROR: dotProduct() for orthogonal vectors is not zero");
		//TC03:test when one of the vector is the unit vector
	    assertEquals(6,v1.dotProduct(norm), "ERROR: dotProduct() for  unit vector vector-not stays the same");
	}

}
