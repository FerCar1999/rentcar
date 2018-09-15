package combustible;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "combustible")
public class Combustible {
	int codi_comb;
	String nomb_comb;
	int esta_comb;

	@XmlElement
	public int getCodi_comb() {
		return codi_comb;
	}

	public void setCodi_comb(int codi_comb) {
		this.codi_comb = codi_comb;
	}

	@XmlElement
	public String getNomb_comb() {
		return nomb_comb;
	}

	public void setNomb_comb(String nomb_comb) {
		this.nomb_comb = nomb_comb;
	}

	@XmlElement
	public int getEsta_comb() {
		return esta_comb;
	}

	public void setEsta_Comb(int esta_comb) {
		this.esta_comb = esta_comb;
	}

}
