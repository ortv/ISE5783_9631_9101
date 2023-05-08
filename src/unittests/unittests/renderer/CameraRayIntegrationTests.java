package unittests.unittests.renderer;

import primitives.*;
import geometries.*;
import static org.junit.jupiter.api.Assertions.*;
import renderer.*;
import org.junit.jupiter.api.Test;

class CameraRayIntegrationTests {
	
	static final Point ZERO_POINT = new Point(0, 0, 0);
	
	void CameraSphereIntersections()
	{
        Camera camera = new Camera(ZERO_POINT, new Vector(0, 0, -1), new Vector(0, 1, 0)).setVUPDistance(1).setVPSize(3, 3);
        //TC01:2 intersections
		Sphere s1=new Sphere(1, new Point(0,0,-3));
		assertEquals(2,countRayIntersections(camera, s1),"wrong number of intersections");
		//TC02:18 intersections
		Sphere s2=new Sphere(2.5, new Point(0,0,-2.5));
		camera = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVUPDistance(1).setVPSize(3, 3);
		assertEquals(18,countRayIntersections(camera, s2),"wrong number of intersections");
		//TC03:10 intersections
		Sphere s3=new Sphere(2, new Point(0,0,-2));
		assertEquals(10,countRayIntersections(camera, s3),"wrong number of intersections");
		//TC04:0 intersections
		Sphere s4=new Sphere(0.5,new Point(0,0,1));
		assertEquals(0,countRayIntersections(camera, s4),"wrong number of intersections");
			
	}

	void CameraPlaneIntersections()
	{
        Camera camera = new Camera(ZERO_POINT, new Vector(0, 0, -1), new Vector(0, 1, 0)).setVUPDistance(1).setVPSize(3, 3);
        //TC01:9 intersections
        Plane p1= new Plane(new Point(0, 0, -3), new Vector(0, 0, -1));
		assertEquals(9,countRayIntersections(camera, p1),"wrong number of intersections");
		//TC02:9 intersections
		Plane p2=new Plane(new Point(0, 0, -2), new Vector(0, 2, 3));
		assertEquals(9,countRayIntersections(camera, p2),"wrong number of intersections");
		//TC03:6 intersections
		Plane p3=new Plane(new Point(0, 0, -3), new Vector(1, 0, -1));
		assertEquals(6,countRayIntersections(camera, p3),"wrong number of intersections");

	}

	void CameraTriangleIntersections()
	{
        Camera camera = new Camera(ZERO_POINT, new Vector(0, 0, -1), new Vector(0, 1, 0)).setVUPDistance(1).setVPSize(3, 3);
        Triangle t1=new Triangle(new Point(0,1,-2), new Point(1, -1, -2), new Point(-1,-1,-2));
		assertEquals(1,countRayIntersections(camera, t1),"wrong number of intersections");
		Triangle t2=new Triangle(new Point(0,20,-2), new Point(1,-1,-2), new Point(-1,-1,-2));
		assertEquals(2,countRayIntersections(camera, t2),"wrong number of intersections");
	
	}
	
	/**

	Counts the number of intersections between a given shape and rays that are constructed by a given camera.
	@param camera the camera that is used to construct the rays.
	@param shape the shape to test the intersection with.
	@return the number of intersections between the given shape and the rays constructed by the given camera.
	*/
	public int countRayIntersections(Camera camera,Intersectable shape)
	{
		int countIntersections=0;
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				var inter=shape.findIntsersections(camera.constructRay(3, 3, j, i));//return a list of intersections for this shape with the current ray
				if(inter!=null)//there are intersections
				{
					countIntersections+=inter.size();
				}
			}
		}
		return countIntersections;//return number of intersections
	}
	

	@Test
	void testConstructRay() {
		CameraSphereIntersections();
		CameraPlaneIntersections();
		CameraTriangleIntersections();
	}



	

	
	
}
