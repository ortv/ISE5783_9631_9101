package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Util;
import primitives.Vector;

public class SpotLight extends PointLight  {
	
	private final Vector direction;
	@Override
	public Color getIntensity(Point p) {
		 double attenuation = getL(p).dotProduct(direction);
	        return Util.alignZero(attenuation) <= 0 ? Color.BLACK : super.getIntensity(p).scale(attenuation);
	}

	
	@Override
	public Vector getL(Point p) {
		// TODO Auto-generated method stub
		return super.getL(p);
	}

	public SpotLight(Color in, Point post,Vector dirctVector) {
		super(in, post);
		// TODO Auto-generated constructor stub
		direction=dirctVector.normalize();
	}
	
	
}

