package unittests.primitives;
import primitives.Point;
import primitives.Ray;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import primitives.Vector;
class RayTests {
    static final Point ZERO_POINT = new Point(0, 0, 0);

	@Test
	void testFindClosestPoint() {
		Ray ray = new Ray( new Vector(1, 0, 0),ZERO_POINT);
        Point a = new Point(8, 0, 0),
                b = new Point(2, 0, 0),
                c = new Point(5, 0, 0);
		// ============ Equivalence Partitions Tests ==============
		//TC01:point in the middle of the list is the closest
        List<Point> lst = List.of(a, b, c);
        assertEquals(b,ray.findClosestPoint(lst),"wrong closest point");
		// =============== Boundary Values Tests ==================
		//TC02:empty list-should return null
        lst=List.of();
		assertNull(ray.findClosestPoint(lst),"empty list-should be null!");
		//TC03:the first point is the closest
        lst = List.of(b, c, a);
        assertEquals(b,ray.findClosestPoint(lst),"wrong closest point");
		//TC04:the last point is the closest
        lst = List.of(a, c, b);
        assertEquals(b,ray.findClosestPoint(lst),"wrong closest point");
	}

}
