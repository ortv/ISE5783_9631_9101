package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Util;
import primitives.Vector;

public class SpotLight extends PointLight  {
	
	private final Vector direction;
	/**
	 * Calculates the intensity of the spot light at a given point.
	 * 
	 * @param p The point at which to calculate the intensity.
	 * @return The intensity of the spot light at the given point.
	 */
	@Override
	public Color getIntensity(Point p) {
		 double attenuation = getL(p).dotProduct(direction);
	        return Util.alignZero(attenuation) <= 0 ? Color.BLACK : super.getIntensity(p).scale(attenuation);
	}

	/**
	 * Calculates the vector from the spot light's position to a given point.
	 * 
	 * @param p The point to which the vector is calculated.
	 * @return The vector from the spot light's position to the given point.
	 */
	@Override
	public Vector getL(Point p) {
		// TODO Auto-generated method stub
		return super.getL(p);
	}
	/**
	 * Constructs a SpotLight object with the given intensity, position, and direction.
	 * 
	 * @param intensity The intensity of the spot light.
	 * @param position The position of the spot light.
	 * @param direction The direction of the spot light.
	 */
	public SpotLight(Color in, Point post,Vector dirctVector) {
		super(in, post);
		// TODO Auto-generated constructor stub
		direction=dirctVector.normalize();
	}
	
	
}

