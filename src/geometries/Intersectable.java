package geometries;
import java.util.List;
import java.util.Objects;

import javax.print.attribute.standard.MediaSize.Other;

import primitives.*;

public abstract class Intersectable {

	public List<Point> findIntsersections(Ray ray) {
		 var geoList = findGeoIntsersections(ray);
		 return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
	}
	
	
	public  final List<GeoPoint> findGeoIntsersections(Ray ray){
		return findGeoIntersectionsHelper(ray);
	}

	protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

	public static class GeoPoint{
		public Geometry geometry;
		public Point point;
		public GeoPoint(Geometry geo,Point p)
		{
			this.geometry=geo;
			this.point=p;
		}
		
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null)
				return false;
			if (obj instanceof GeoPoint other)
				return this.point.equals(other.point)&&this.geometry.equals(other.geometry);
			return false;
		}
		@Override
		public String toString() {
			return "GeoPoint [geometry=" + geometry + ", point=" + point + "]";
		}
	}

}
