package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

public class Scene {
	//all fields are public because this class is PDS-does not include any active operations or behaviors. In other words,
	//it only holds data and provides access to that data, but does not perform any operations on the data.
	public String name;
	public Color background=Color.BLACK;
	public AmbientLight ambientLight=AmbientLight.NONE;
	public Geometries geometries=new Geometries();
	
	public Scene(String namString)
	{
		name=namString;
	}

	public Scene setName(String name) {
		this.name = name;
		return this;
	}

	public Scene setBackground(Color background) {
		this.background = background;
		return this;
	}

	public Scene setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
		return this;
	}

	public Scene setGeometries(Geometries geometries) {
		this.geometries = geometries;
		return this;
	}
	
	
}
