package renderer;
import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.Double3;
import java.util.List;
import primitives.Util;
import primitives.Color;
import primitives.Material;
import primitives.Ray;
import primitives.Vector;
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
		List<GeoPoint> intersections=scene.geometries.findGeoIntsersections(ray);
		if(intersections==null)//no intersection point
		{
			return scene.background;//return the ambient light
		}
		//else-there are intersections-return the color of the closest point from all the intersection points
		GeoPoint closestPoint=ray.finfClosestGeoPoint(intersections);
		return calColor(closestPoint,ray);
	}
	/**
	 * Calculates the color at a given point in the scene.
	 * 
	 * @param point The point for which to calculate the color.
	 * @return The color at the given point, which is the intensity of the scene's ambient light.
	 */
	public Color calColor(GeoPoint point,Ray ray)//gets a point and return the color there
	{
			return scene.ambientLight.getIntensity().add(calcLocalEffects(point, ray));

	}
	
	
	
	
	
	private Color calcLocalEffects(GeoPoint gp,Ray ray)
	{
		Color color=gp.geometry.getEmission();
		Vector v=ray.getDir();
		Vector n=gp.geometry.getNormal(gp.point);
		double nv=Util.alignZero(n.dotProduct(v));
		if(Util.isZero(nv))//no effects
			return color;//only emission
		//else
		Material material=gp.geometry.getMaterial();
		for (LightSource light : scene.lights)//runs on all  light sources
		{
			Vector l=light.getL(gp.point);//get the vector from the light to the given point
			double nl=Util.alignZero(n.dotProduct(l));
			if(nl*nv>0)//same sign
			{
				Color iL=light.getIntensity(gp.point);
				color=color.add(iL.scale((calcDiffse(material,nl))),iL.scale(calcSpecular(material,n,l,nl,v)));//adding diffuse and specular
			}
		}
		return color;			
	}
	
	
	
	
	private Double3 calcDiffse(Material mat,double ln)
	{//return the diffuse
		return mat.kD.scale(Math.abs(ln));//kd*|l*n|*iL
	}
	
	private Double3 calcSpecular(Material mat,Vector n,Vector l,double nl,Vector v)
	{
		Vector r=l.add(n.scale(-2*nl));//l-2*(ln)*n
		double vr=Util.alignZero(v.dotProduct(r));
		return (mat.kS.scale(Math.pow(Math.max(0,vr ),mat.Shininess)));
	}
	 
}
