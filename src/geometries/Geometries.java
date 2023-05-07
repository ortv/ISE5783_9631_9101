package geometries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import primitives.Point;
import primitives.Ray;

public class Geometries implements Intersectable{
	
	private List<Intersectable> geoShapes;
	
	public Geometries()
	{
		geoShapes=new LinkedList<Intersectable>();
	}
	public Geometries(Intersectable ...geomet)
	{
		geoShapes=new LinkedList<Intersectable>();
		Collections.addAll(geoShapes,geomet);
	}
	/*
	 * adding an item into the list
	 * @param geometires*/
	
	public void add(Intersectable geometries)
	{
		Collections.addAll(geoShapes,geometries);
	}
	
	
	
	@Override
	public List<Point> findIntsersections(Ray ray) {
		
		 List<Point> result = null;
	        for (Intersectable item : geoShapes) {
	            List<Point> itemResult = item.findIntsersections(ray);
	            if (itemResult != null) {
	                if (result == null)
	                    result = new LinkedList<>();
	                result.addAll(itemResult);
	            }
	        }
	        return result;
		/*
		List<Point> result = new LinkedList<>();;
        for (Intersectable item : geoShapes) {
            List<Point> shapeIntersList = item.findIntsersections(ray);
            if (shapeIntersList != null) {
                if (result == null)
                    result = new LinkedList<>();
                else
                    result.addAll(shapeIntersList);
            }
        }
        return null;*/
    }
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
