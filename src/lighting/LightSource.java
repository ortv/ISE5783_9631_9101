package lighting;

import primitives.Vector;
import primitives.Color;
import primitives.Point;

public interface LightSource {
	public Color getIntensity(Point p);
	public Vector getL(Point p);
}
