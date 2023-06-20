package renderer;
import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.Double3;

import java.sql.NClob;
import java.util.List;
import primitives.Util;
import primitives.Color;
import primitives.Material;
import primitives.Point;
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
	private static final double DELTA=0.1;
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final double MIN_CALC_COLOR_K = 0.001;
	private static final double INITIAL_K = 1.0;
	/**
	 * Constructs a reflected ray at a given intersection point.
	 * 
	 * @param p The point of reflection.
	 * @param v The incident ray direction.
	 * @param n The surface normal.
	 * @return The reflected ray.
	 */
	private Ray constructReflectedRay(Point p, Vector v, Vector n) {/*for reflected*/
        //r = v - 2.(v.n).n
        double vn = v.dotProduct(n);
        if (Util.isZero(vn)) return null;

        Vector r = v.subtract(n.scale(2 * vn));
        return new Ray(p, r, n);
    }

	/**
	 * Constructs a refracted ray at a given intersection point.
	 * 
	 * @param p The point of refraction.
	 * @param v The incident ray direction.
	 * @param n The surface normal.
	 * @return The refracted ray.
	 */
    private Ray constructRefractedRay(Point p, Vector v, Vector n) {/*for transparency*/
        return new Ray(p, v, n);
    }

	
	
	/**
	 * Finds the closest intersection point of a given ray with the scene's geometries.
	 * 
	 * @param ray The ray for which to find the closest intersection.
	 * @return The closest intersection point, or null if no intersection exists.
	 */
	private GeoPoint findClosestIntersection(Ray ray)
	{//need to return the closest intersection point
		List<GeoPoint>lst=scene.geometries.findGeoIntsersections(ray);//return list of intersection
		return lst==null?null:ray.finfClosestGeoPoint(lst);//if no intersections-return null. otherwise return
		//the closest intersection point from all the intersections
	}
	/**
	 * Calculates the color of a given ray by finding the closest intersection point with the scene's geometries.
	 * 
	 * @param ray The ray for which to calculate the color.
	 * @return The color at the intersection point, or the scene's background color if there is no intersection.
	 */
	public Color traceRay(Ray ray)
	{
		GeoPoint closestPoint=findClosestIntersection(ray);
		return  closestPoint==null?scene.background:calcColor(closestPoint, ray);
	}
	
	
	/**
	  * @param rays List of surrounding rays(the beam)
	  * @return average color
	  */
	public Color traceRay(List<Ray> rays) 
	{
	 	if(rays == null)
	 		return scene.background;
	     Color color = scene.background;
	     for (Ray ray : rays) //runs on all the rays in the beam
	     {
	     	color = color.add(traceRay(ray));//add the color of each ray
	     }
	     color = color.add(scene.ambientLight.getIntensity());
	     int size = rays.size();
	     return color.reduce(size);//return the average color of all the rays
	
	 }
	
	private Color calcColor(GeoPoint gp,Ray ray)//warp function-send to recursive calcColor
	{
		return calcColor(gp,ray,MAX_CALC_COLOR_LEVEL ,new Double3( INITIAL_K)).add(scene.ambientLight.getIntensity());
	}
	
	/**
	 * Calculates the color at a given point in the scene.
	 * 
	 * @param point The point for which to calculate the color.
	 * @return The color at the given point, which is the intensity of the scene's ambient light.
	 */
	public Color calcColor(GeoPoint gp,Ray ray, int level,Double3 k)//gets a point and return the color there
	{
		Color color=calcLocalEffects(gp, ray,k);
		return 1==level?color:color.add(calcGlobalEffects(gp,ray,level,k));
	}
	
	/**
	 * Calculates the global effects, such as reflection and refraction, at a given intersection point in the scene.
	 * 
	 * @param intersection The intersection point for which to calculate the global effects.
	 * @param ray The ray that intersected with the geometry.
	 * @param level The recursion level for calculating global effects.
	 * @param k The accumulation factor for global effects.
	 * @return The color contribution from global effects.
	 */
	private Color calcGlobalEffects(GeoPoint intersection, Ray ray, int level, Double3 mink) {
       // Color color=Color.BLACK;
        Vector v = ray.getDir();
		Vector n = intersection.geometry.getNormal(intersection.point);
        Material material = intersection.geometry.getMaterial();
        Ray refractedRay = constructRefractedRay(intersection.point, v, n);
        Ray reflectedRay = constructReflectedRay(intersection.point, v, n);
        return calcGlobalEffect(refractedRay, level, mink, material.kT)
                .add(calcGlobalEffect(reflectedRay, level, mink, material.kR));
    }
	
	
	
	/**
	 * Calculates the color contribution from global effects such as reflection or refraction.
	 * 
	 * @param ray The ray for which to calculate the global effect.
	 * @param level The recursion level for calculating global effects.
	 * @param k The accumulation factor for global effects.
	 * @param kx The specific coefficient for the global effect.
	 * @return The color contribution from the global effect.
	 */
	 private Color calcGlobalEffect(Ray ray, int level, Double3 k, Double3 kx) {
	        Double3 kkx = k.product(kx);
	        if (kkx.lowerThan(MIN_CALC_COLOR_K))
	        	return Color.BLACK;
	        GeoPoint gp = findClosestIntersection(ray);
	        if( gp == null)
	        	return scene.background.scale(kx);
	        level=level-1;
	        return Util.isZero(gp.geometry.getNormal(gp.point).dotProduct(ray.getDir()))? Color.BLACK: calcColor(gp, ray, level, kkx).scale(kx);
	
	 }
	
	
	
	
	
	
	/**
	* Calculates the color at a given point in the scene, considering local effects such as diffuse and specular reflections.
	* 
	* @param gp The intersection point for which to calculate the color.
	* @param ray The ray that intersected with the geometry.
	* @param k The accumulation factor for global effects.
	* @return The color at the given point, considering local effects.
	*/
	private Color calcLocalEffects(GeoPoint gp,Ray ray,Double3 k)
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
				//if(unshaded(gp, light, l, n, nv))//if there is no shading-can add color
				Double3 ktr=transparency(gp, light, l, n, nv);
				if(new Double3(MIN_CALC_COLOR_K).lowerThan(ktr))//ktr*k>MIN_CALC_COLOR_K
				{

					Color iL=light.getIntensity(gp.point).scale(ktr);
					color=color.add(iL.scale((calcDiffse(material,nl))),iL.scale(calcSpecular(material,n,l,nl,v)));//adding diffuse and specular	
				}
				
			}
		}
		return color;			
	}
	
	
	
	/**
	 * Calculates the diffuse reflection coefficient for a given material and light-normal dot product.
	 * 
	 * @param mat The material.
	 * @param ln The dot product between the light direction and the surface normal.
	 * @return The diffuse reflection coefficient.
	 */
	private Double3 calcDiffse(Material mat,double ln)
	{//return the diffuse
		return mat.kD.scale(Math.abs(ln));//kd*|l*n|*iL
	}
	/**
	 * Calculates the specular reflection coefficient for a given material, light direction, and normal vector.
	 * 
	 * @param mat The material.
	 * @param n The surface normal.
	 * @param l The light direction.
	 * @param nl The dot product between the normal and the light direction.
	 * @param v The viewer direction.
	 * @return The specular reflection coefficient.
	 */
	private Double3 calcSpecular(Material mat,Vector n,Vector l,double nl,Vector v)
	{
		Vector r=l.add(n.scale(-2*nl));//l-2*(ln)*n
		

		double vr=Util.alignZero(v.dotProduct(r));
		if(vr>=0)
			return Double3.ZERO;
		return (mat.kS.scale(Math.pow(-vr,mat.Shininess)));
		
		//double vr=Util.alignZero(v.dotProduct(r));
		//return (mat.kS.scale(Math.pow(Math.max(0,vr ),mat.Shininess)));
	}
	 
	/**
	 * Calculates the transparency coefficient between a given point and a light source, considering intersections with other objects.
	 * 
	 * @param gp The point in the scene.
	 * @param light The light source.
	 * @param l The vector from the light source to the point.
	 * @param n The surface normal at the point.
	 * @param nv The dot product between the normal and the ray direction.
	 * @return The transparency coefficient.
	 */
	private Double3 transparency(GeoPoint gp,LightSource light,Vector l,Vector n,double nv)
	{
		Vector lightDirection=l.scale(-1);//change direction from point to light
		Vector epsVector=n.scale(nv<0?DELTA:-DELTA);
		Point point=gp.point.add(epsVector);
		//Ray lightRay=new Ray(lightDirection,point);
		Ray lightRay=new Ray(gp.point,lightDirection,n);
        Double3 ktr = Double3.ONE;
		//created a ray-now need to check if there are any intersections from the point to the light source from any type of object
		List<GeoPoint> intersections=scene.geometries.findGeoIntsersections(lightRay);
		if(intersections==null) 
			return ktr;//no object on the way,no shading
		for (GeoPoint intersection : intersections) {
			if(Util.alignZero(intersection.point.distance(point)-light.getDistance(point))<0)
			{
				ktr = ktr.product(intersection.geometry.getMaterial().kT);//ktr*kt
	            if (ktr.lowerThan(MIN_CALC_COLOR_K))
	                return Double3.ZERO;
			}
			 
		}
			return ktr;
	}
	
	
	/**
	 * Checks if a given point in the scene is unshaded by other objects, given a light source and normal vectors.
	 * 
	 * @param gp The point in the scene.
	 * @param light The light source.
	 * @param l The vector from the light source to the point.
	 * @param n The surface normal at the point.
	 * @param nv The dot product between the normal and the ray direction.
	 * @return true if the point is unshaded, false otherwise.
	 */
	private boolean unshaded(GeoPoint gp,LightSource light,Vector l,Vector n,double nv)
	{
		Vector lightDirection=l.scale(-1);//change direction from point to light
		Vector epsVector=n.scale(nv<0?DELTA:-DELTA);
		Point point=gp.point.add(epsVector);
	//	Ray lightRay=new Ray(lightDirection,point);
		Ray lightRay=new Ray(lightDirection,point);

		//created a ray-now need to check if there are any intersections from the point to the light source from any type of object
		List<GeoPoint> intersections=scene.geometries.findGeoIntsersections(lightRay);
		if(intersections==null) return true;//no object on the way,no shading
		
		//there are intersections, there might be an object-if the intersection points are between the given point and no before/after
		for (GeoPoint intersection : intersections) {
			if(Util.alignZero(intersection.point.distance(point)-light.getDistance(point))<0)
				//if the distance between the intersection to the point is between the point to the light source-there is a shadow
				return false;
		}
		return true;
	}
}

