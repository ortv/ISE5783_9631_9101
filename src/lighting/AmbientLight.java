package lighting;

import primitives.Color;
import primitives.Double3;
/**
 * The AmbientLight class represents ambient light in a scene.
 * It defines the intensity of the ambient light, which contributes to the overall lighting in the scene.
 */
public class AmbientLight extends Light{
	
	/**
     * A constant representing no ambient light (intensity is zero).
     */
	public static AmbientLight NONE=new AmbientLight(Color.BLACK, Double3.ZERO);
	
	/**
     * Constructs an AmbientLight object with the given intensity and coefficient.
     *
     * @param intensity The intensity of the ambient light.
     * @param coefficient The coefficient determining the overall strength of the ambient light.
     */
	public AmbientLight(Color iA,Double3 kA)
	{
		super(iA.scale(kA));
	}
	 /**
     * Constructs an AmbientLight object with the given intensity and coefficient.
     *
     * @param intensity The intensity of the ambient light.
     * @param coefficient The coefficient determining the overall strength of the ambient light.
     */
	public AmbientLight(Color iA,Double kA)
	{
		super(iA.scale(kA));
	}

	
	
	
}
