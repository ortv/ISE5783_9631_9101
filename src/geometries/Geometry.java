package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

public abstract class Geometry extends Intersectable{
	protected Color emission=Color.BLACK;
	private Material material=new Material();
	public Geometry setMaterial(Material material) {
		this.material = material;
		return this;
	}
	public Material getMaterial() {
		return material;
	}
	public Geometry setEmission(Color emission) {
		this.emission = emission;
		return this;
	}
	public Color getEmission() {
		return emission;
	}
	public abstract Vector getNormal(Point p);
}
