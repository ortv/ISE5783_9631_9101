package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class SpotLight extends PointLight  {
	
	private Vector direction;
	@Override
	public Color getIntensity(Point p) {
		return this.getIntensity(p).scale(Math.max(0, getL(p).dotProduct(direction)));//(l0*max(0,dir*l)/kc+kl*d+kq*d^2
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

