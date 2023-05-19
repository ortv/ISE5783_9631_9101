package scene;

import java.util.LinkedList;
import java.util.List;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;
/**
 * The Scene class represents a scene in a ray tracing application.
 * It holds the data related to the scene, such as the background color, ambient light, and geometries.
 * This class does not include any active operations or behaviors and only provides access to the data.
 */
public class Scene {
	//all fields are public because this class is PDS-does not include any active operations or behaviors. In other words,
	//it only holds data and provides access to that data, but does not perform any operations on the data.
	
	 /**
     * The name of the scene.
     */
	public String name;
	/**
     * The background color of the scene.
     * Defaults to Color.BLACK.
     */
	public Color background=Color.BLACK;
	/**
     * The ambient light in the scene.
     * Defaults to AmbientLight.NONE.
     */
	public AmbientLight ambientLight=AmbientLight.NONE;
	/**
     * The geometries in the scene.
     */
	public Geometries geometries=new Geometries();
	
	public List<LightSource> lights=new LinkedList<>();
	
	
	public Scene setLightSources(List<LightSource> lightSources) {
		this.lights = lightSources;
		return this;
	}
	/**
     * Constructs a Scene object with the given name.
     *
     * @param name The name of the scene.
     */
	public Scene(String namString)
	{
		name=namString;
	}
	/**
     * Sets the name of the scene.
     *
     * @param name The name of the scene.
     * @return The Scene instance with the updated name.
     */
	public Scene setName(String name) {
		this.name = name;
		return this;
	}
	/**
     * Sets the background color of the scene.
     *
     * @param background The background color of the scene.
     * @return The Scene instance with the updated background color.
     */
	public Scene setBackground(Color background) {
		this.background = background;
		return this;
	}
	/**
     * Sets the ambient light in the scene.
     *
     * @param ambientLight The ambient light in the scene.
     * @return The Scene instance with the updated ambient light.
     */
	public Scene setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
		return this;
	}
	/**
     * Sets the geometries in the scene.
     *
     * @param geometries The geometries in the scene.
     * @return The Scene instance with the updated geometries.
     */
	public Scene setGeometries(Geometries geometries) {
		this.geometries = geometries;
		return this;
	}
	
	
}
