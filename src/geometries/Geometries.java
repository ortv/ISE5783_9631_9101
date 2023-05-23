package geometries;
import scene.*;

import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import geometries.Intersectable.GeoPoint;

/**
 * Geometries class represents a list of shapes of all kinds
 *
 * @author AAA
 */
public class Geometries extends Intersectable {

    private final List<Intersectable> geometries = new LinkedList<>();

    /**
     * constructor for list of geometries
     *
     * @param geometries list of shapes of all kinds
     */
    public Geometries(Intersectable... geometries) {
        this.add(geometries);
    }

    /**
     * adds geometries to list
     *
     * @param geometries list of shapes of all kinds
     */
    public void add(Intersectable... geometries) {
        if (geometries.length != 0)
            this.geometries.addAll(List.of(geometries));
    }

	/*@Override
	public List<Point> findIntsersections(Ray ray) {
		
		 List<Point> result = null;
	        for (Intersectable item : geometries) {
	            List<Point> itemResult = item.findIntsersections(ray);
	            if (itemResult != null) {
	                if (result == null)
	                    result = new LinkedList<>();
	                result.addAll(itemResult);
	            }
	        }
	        return result;
    }*/
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		// TODO Auto-generated method stub
		 List<GeoPoint> result = null;
		 for (Intersectable geometry :geometries) {
			List<GeoPoint> geoIntersections=geometry.findGeoIntersectionsHelper(ray);
			if (geoIntersections != null){//there are interactions 
                if (result == null)//if a new list
                    result = new LinkedList<>();
                result.addAll(geoIntersections);
			}
		}
		 return result;
	}
}
