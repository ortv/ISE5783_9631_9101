package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource{
	/**
	 * Constructs a PointLight object with the given intensity and position.
	 * 
	 * @param intensity The intensity of the point light.
	 * @param position The position of the point light.
	 */
	public PointLight(Color in,Point post) {
		super(in);
		// TODO Auto-generated constructor stub
		this.position=post;
	}
	private Point  position;
	private double kC=1,kL=0,kq=0;
	/**
	 * Sets the constant attenuation factor of the point light.
	 * 
	 * @param kC The constant attenuation factor.
	 * @return This PointLight object for method chaining.
	 */
	public PointLight setkC(double kC) {
		this.kC = kC;
		return this;
	}
	/**
	 * Sets the linear attenuation factor of the point light.
	 * 
	 * @param kL The linear attenuation factor.
	 * @return This PointLight object for method chaining.
	 */
	public PointLight setkL(double kL) {
		this.kL = kL;
		return this;
	}
	
	/**
	 * Sets the quadratic attenuation factor of the point light.
	 * 
	 * @param kq The quadratic attenuation factor.
	 * @return This PointLight object for method chaining.
	 */
	public PointLight setkq(double kq) {
		this.kq = kq;
		return this;
	}
	
	/**
	 * Calculates the intensity of the point light at a given point.
	 * 
	 * @param p The point at which to calculate the intensity.
	 * @return The intensity of the point light at the given point.
	 */
	@Override
	public Color getIntensity(Point p) {
		double distanceS=p.distanceSquared(position);
		double d=p.distance(position);
		double factor = kC + kL * d + kq * distanceS;
        return getIntensity().reduce(factor);
		//Color iL=this.getIntensity().reduce(kC+kL*d+kq*distanceS);//Il=L0/Kc+Kl*d+Kp*d^2
		//return iL;
	}
	/**
	 * Calculates the vector from the point light's position to a given point.
	 * 
	 * @param p The point to which the vector is calculated.
	 * @return The vector from the point light's position to the given point.
	 */
	@Override
	public Vector getL(Point p) {
		return p.subtract(position).normalize();
	}
	/**
	 * Calculates the distance between the point light's position and a given point.
	 * 
	 * @param p The point for which to calculate the distance.
	 * @return The distance between the point light's position and the given point.
	 */
	@Override
	public double getDistance(Point p) {
		// TODO Auto-generated method stub
		 return position.distance(p);
		 }

	
	
}
