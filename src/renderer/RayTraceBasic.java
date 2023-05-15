package renderer;

import java.util.List;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

public class RayTraceBasic extends RayTracerBase{
	
	

	public RayTraceBasic(Scene scene) {
		super(scene);
		// TODO Auto-generated constructor stub
	}
	public Color traceRay(Ray ray)
	{
		List<Point> intersections=scene.geometries.findIntsersections(ray);
		if(intersections==null)//no intersection point
		{
			return scene.background;//return the ambient light
		}
		//else-there are intersections-return the color of the closest point from all the intersection points
		Point closestPoint=ray.findClosestPoint(intersections);
		return calColor(closestPoint);
	}
	
	public Color calColor(Point point)//gets a point and return the color there
	{
			return scene.ambientLight.getIntensity();
	}
}
