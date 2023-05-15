package lighting;

import primitives.Color;
import primitives.Double3;

public class AmbientLight {
	private Color intesity;
	public static AmbientLight NONE=new AmbientLight(Color.BLACK, Double3.ZERO);
	
	public AmbientLight(Color iA,Double3 kA)
	{
		intesity=iA.scale(kA);//saves the intensity of the color in this point
	}
	
	public AmbientLight(Color iA,Double kA)
	{
		intesity=iA.scale(kA);//saves the intensity of the color in this point
	}
	
	public Color getIntensity()
	{
		return intesity;
	}
	
	
	
	
}
