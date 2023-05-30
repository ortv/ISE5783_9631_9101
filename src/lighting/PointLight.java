package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource{
	
	public PointLight(Color in,Point post) {
		super(in);
		// TODO Auto-generated constructor stub
		this.position=post;
	}
	private Point  position;
	private double kC=1,kL=0,kq=0;
	public PointLight setkC(double kC) {
		this.kC = kC;
		return this;
	}
	
	public PointLight setkL(double kL) {
		this.kL = kL;
		return this;
	}
	public PointLight setkq(double kq) {
		this.kq = kq;
		return this;
	}
	@Override
	public Color getIntensity(Point p) {
		double distanceS=p.distanceSquared(position);
		double d=p.distance(position);
		double factor = kC + kL * d + kq * distanceS;
        return getIntensity().reduce(factor);
		//Color iL=this.getIntensity().reduce(kC+kL*d+kq*distanceS);//Il=L0/Kc+Kl*d+Kp*d^2
		//return iL;
	}
	@Override
	public Vector getL(Point p) {
		return p.subtract(position).normalize();
	}

	@Override
	public double getDistance(Point p) {
		// TODO Auto-generated method stub
		 return position.distance(p);
		 }

	
	
}
