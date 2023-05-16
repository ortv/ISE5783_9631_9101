package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;
/**
 * The RayTracerBase class is an abstract base class for ray tracer implementations.
 * It defines the common functionality required for tracing rays in a scene.
 */
public abstract class RayTracerBase {

	protected final Scene scene;
	/**
     * Constructs a RayTracerBase object with the given scene.
     *
     * @param scene The scene containing the geometries and lights.
     */
	  RayTracerBase(Scene scene) {
	        this.scene = scene;
	  }
	  /**
	   * Traces a ray and calculates the color at the intersection point with the scene.
	   *
	   * @param ray The ray to trace.
	   * @return The color at the intersection point.
	   */
	 public abstract Color traceRay(Ray ray); 
	
}
