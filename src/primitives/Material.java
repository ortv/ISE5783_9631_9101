package primitives;

public class Material {

	public Double3 kD=Double3.ZERO,kS=Double3.ZERO;
	public int Shininess=0;
	public Material setkD(Double3 kD) {
		this.kD = kD;
		return this;
	}
	public Material setkS(Double3 kS) {
		this.kS = kS;
		return this;
	}
	public Material setkD(Double kD) {
		this.kD =new Double3(kD);
		return this;
	}
	public Material setkS(Double kS) {
		this.kS = new Double3(kS);
		return this;
	}
	public Material setShininess(int nShininess) {
		this.Shininess = nShininess;
		return this;
	}
	
}
