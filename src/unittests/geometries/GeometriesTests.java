package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.Geometries;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

class GeometriesTests {

	@Test
	void testFindIntsersections() {
		
		
		Triangle triangle = new Triangle(new Point(0.81486, 1.38087, 0), new Point(0.95156, 4.48537, 0), new Point(-0.88168, 4.58488, 0));
	        Sphere sphere = new Sphere(1,new Point(0, 0, 0.2));
	        Plane plane = new Plane(new Point(-3.07591, -1.36608, 0), new Point(-1.58192, 4.18223, 0), new Point(-4.15138, 1.01425, -2.21357));

	        // ============ Equivalence Partitions Tests ==============
	        // TC01: Test that findIntersections work currently when the ray intersect some shapes
	        Geometries ge = new Geometries(triangle, sphere, plane);
	        assertEquals(3, ge.findIntsersections(new Ray( new Vector(-1, 0, 0),new Point(3, 0, 0))).size(),
	                "TC01: The findIntersections did`nt work currently when the ray intersect some shapes");


	        // =============== Boundary Values Tests ==================
	        // TC02: Test that findIntersections returns NULL when there is no Geometries
	        Geometries geo = new Geometries();
	        assertNull(geo.findIntsersections(new Ray( new Vector(0.25, 0, -1),new Point(0, 0, 1))),
	                "should throw null-no intersections");

	        // TC03: Test that findIntersections returns NULL when there is no intersections on the Geometries
	        Geometries geom = new Geometries(triangle, sphere);
	        
	        assertNull(geom.findIntsersections(new Ray( new Vector(-1.27, -1.41, 0),new Point(3, 0, 0))),
	                "should throw null-no intersections");

	        // TC04: Test that findIntersections work currently when the ray intersect just one shape
	        assertEquals(2, geom.findIntsersections(new Ray( new Vector(-1, 0, 0),new Point(3, 0, 0))).size(),
	                "TC13: The findIntersections did`nt work currently when the ray intersect just one shape");

	        
	        // TC05: Test that findIntersections work currently when the ray intersect all shapes
	        Geometries geo5 = new Geometries(triangle, sphere);

	        assertEquals(3, geo5.findIntsersections(new Ray( new Vector(-1.12, -1.84, 0.28),new Point(1.79961, 4.57061, -0.28315))).size(),
	                "TC14: The findIntersections did`nt work currently when the ray intersect all shapes");
		
		
		
		
		
		
	}

}
