//

//
 
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.Plane;
import geometries.Triangle;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * @author AAA
 *
 */
class TriangleTests {

	
	//private static final String Triangle = null;

	/**
	 * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
		 // ============ Equivalence Partitions Tests ==============
		//TC01:test if get the correct result-the length of the normal vector should be 1
		Plane p=new Plane(new Point(1,2,3),new Point (1,1,1),new Point(2,4,6));
		assertEquals(1, p.getNormal().length(),"ERROR: getNormal()  does not work correctly-should be 1!");
			}
	   
	   /**
		 * Test method for {@link geometries.Triangle#findIntsersections(Ray ray)}.
		 */
		@Test
		public void  testFindIntersectionPoints()
		{
			 // ============ Equivalence Partitions Tests ==============
			//TC01:the point inside the triangle
			
			Triangle tri = new Triangle(new Point(0, 3, -3), new Point(3, 0, -3), new Point(-3, 0, -3));
			Ray ray=new Ray(new Vector(-2,0.5,-1), new Point(1,1,-2));
			assertEquals(List.of( new Point(-1,1.5,-3)), tri.findIntsersections(ray),"wrong intersection point");
			 // ============ Equivalence Partitions Tests ==============
			
			 
			//TC02:the point outside against edge
			Triangle tri2 = new Triangle(new Point(0, 3, -3), new Point(3, 0, -3), new Point(-3, 0, -3));
			Ray ray2=new Ray(new Vector(1,1,-4), new Point(4,4,-2));
			List<Point> result2=tri2.findIntsersections(ray2);
			assertEquals(null, result2,"wrong number of points");
			
			 // ============ Equivalence Partitions Tests ==============
			//TC03:the point on edge's continuation 
			Triangle tri3 = new Triangle(new Point(0, 3, -3), new Point(3, 0, -3), new Point(-3, 0, -3));
			Ray ray3=new Ray(new Vector(0,0,-1), new Point(-1,4,-2));
			List<Point> result3=tri3.findIntsersections(ray3);
			assertEquals(null, result3,"wrong number of points");
			
			 // ============ Equivalence Partitions Tests ==============
			//TC04:the point on edge 
			Triangle tri4 = new Triangle(new Point(0, 3, -3), new Point(3, 0, -3), new Point(-3, 0, -3));
			Ray ray4=new Ray(new Vector(0,0,-1), new Point(-2,1,-1));
			List<Point> result4=tri4.findIntsersections(ray4);
			assertEquals(null, result4,"wrong number of points");
			
			 // ============ Equivalence Partitions Tests ==============
			//TC05:the point In vertex of the triangle
			Triangle tri5 = new Triangle(new Point(0, 3, -3), new Point(3, 0, -3), new Point(-3, 0, -3));
			Ray ray5=new Ray(new Vector(0,0,-1), new Point(3,0,-2));
			List<Point> result5=tri5.findIntsersections(ray5);
			assertEquals(null, result5,"wrong number of points");
			
			// ============ Equivalence Partitions Tests ==============
						//TC06:the point Outside against vertex
			Triangle tri6 = new Triangle(new Point(0, 3, -3), new Point(3, 0, -3), new Point(-3, 0, -3));
			Ray ray6 =new Ray(new Vector(-1,-1,-1), new Point(-4,-1,-2));
			List<Point> result6=tri6.findIntsersections(ray6);
			assertEquals(null, result6 ,"wrong number of points");			
			
			 
		}
}
