package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource{

	public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
	}

	private Vector direction;
	
	@Override
	public Color getIntensity(Point p)
	{//get a point and return intensity of light for the given point
		return getIntensity();//Doesn't reduce through time
	}
	@Override
	public Vector getL(Point p)
	{
        return direction;
	}
	
}
