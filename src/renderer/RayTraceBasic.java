package renderer;

import java.util.List;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;
	/**
 	* The RayTraceBasic class is a basic implementation of a ray tracer that calculates the color of a given ray by finding the closest intersection point with the scene's geometries.
 	* It extends the RayTracerBase class.
 	*/
public class RayTraceBasic extends RayTracerBase{
	

	/**
	 * Constructs a RayTraceBasic object with the given scene.
	 * 
	 * @param scene The scene containing the geometries and lights.
	 */
	public RayTraceBasic(Scene scene) {
		super(scene);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Calculates the color of a given ray by finding the closest intersection point with the scene's geometries.
	 * 
	 * @param ray The ray for which to calculate the color.
	 * @return The color at the intersection point, or the scene's background color if there is no intersection.
	 */
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
	/**
	 * Calculates the color at a given point in the scene.
	 * 
	 * @param point The point for which to calculate the color.
	 * @return The color at the given point, which is the intensity of the scene's ambient light.
	 */
	public Color calColor(Point point)//gets a point and return the color there
	{
			return scene.ambientLight.getIntensity();
	}
}
