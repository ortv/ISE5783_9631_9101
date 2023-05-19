package lighting;

import primitives.Color;

public abstract class Light {
	
	private Color intensity;
	protected Light(Color in)
	{
		intensity=in;
	}
	public Color getIntensity() {
		return intensity;
	}
	
	
}
