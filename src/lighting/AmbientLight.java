package lighting;

import primitives.Color;
import primitives.Double3;
/**
 * The AmbientLight class represents ambient light in a scene.
 * It defines the intensity of the ambient light, which contributes to the overall lighting in the scene.
 */
public class AmbientLight {
	
	/**
     * A constant representing no ambient light (intensity is zero).
     */
	public static AmbientLight NONE=new AmbientLight(Color.BLACK, Double3.ZERO);
	
	private Color intesity;
	/**
     * Constructs an AmbientLight object with the given intensity and coefficient.
     *
     * @param intensity The intensity of the ambient light.
     * @param coefficient The coefficient determining the overall strength of the ambient light.
     */
	public AmbientLight(Color iA,Double3 kA)
	{
		intesity=iA.scale(kA);//saves the intensity of the color in this point
	}
	 /**
     * Constructs an AmbientLight object with the given intensity and coefficient.
     *
     * @param intensity The intensity of the ambient light.
     * @param coefficient The coefficient determining the overall strength of the ambient light.
     */
	public AmbientLight(Color iA,Double kA)
	{
		intesity=iA.scale(kA);//saves the intensity of the color in this point
	}
	/**
     * Returns the intensity of the ambient light.
     *
     * @return The intensity of the ambient light.
     */
	public Color getIntensity()
	{
		return intesity;
	}
	
	
	
	
}
