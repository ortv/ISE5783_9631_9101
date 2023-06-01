package primitives;

import java.util.Objects;

public class Material {
	 
	@Override
	public String toString() {
		return "Material [kD=" + kD + ", kS=" + kS + ", Shininess=" + Shininess + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Material other)
			return Shininess == other.Shininess && Objects.equals(kD, other.kD) && Objects.equals(kS, other.kS);
		return false;
		
		
	}
	public Double3 kD=Double3.ZERO,kS=Double3.ZERO;
	public int Shininess=0;
	public Double3 kT=Double3.ZERO;//for transparency
	public Double3 kR=Double3.ZERO;//for reflection
	
	public Material setkT(Double3 kT) {
		this.kT = kT;
		return this;
	}
	public Material setkR(Double3 kR) {
		this.kR = kR;
		return this;
	}
	
	public Material setkT(Double kT) {
		this.kT = new Double3(kT);
		return this;
	}
	public Material setkR(Double kR) {
		this.kR = new Double3(kR);
		return this;
	}
	
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
