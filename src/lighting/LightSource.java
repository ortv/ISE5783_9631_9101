package lighting;

import primitives.Vector;
import primitives.Color;
import primitives.Point;

public interface LightSource {
	public Color getIntensity(Point p);
	public Vector getL(Point p);
	
	public double getDistance(Point p);//calculate distance between a point to the light source
	
}
