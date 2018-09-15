package modelo;

import javax.xml.bind.annotation.XmlRootElement;

import marca.Marca;

import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "modelo")
public class Modelo {
	int codi_mode;
	String nomb_mode;
	int codi_marc;
	String nomb_marc;
	int esta_mode;

	@XmlElement
	public int getCodi_mode() {
		return codi_mode;
	}

	public void setCodi_mode(int codi_mode) {
		this.codi_mode = codi_mode;
	}

	@XmlElement
	public String getNomb_mode() {
		return nomb_mode;
	}

	public void setNomb_mode(String nomb_mode) {
		this.nomb_mode = nomb_mode;
	}

	@XmlElement
	public int getCodi_marc() {
		return codi_marc;
	}

	public void setCodi_marc(int codi_marc) {
		this.codi_marc = codi_marc;
	}

	@XmlElement
	public String getNomb_marc() {
		return nomb_marc;
	}

	public void setNomb_marc(String nomb_marc) {
		this.nomb_marc = nomb_marc;
	}
	@XmlElement
	public int getEsta_mode() {
		return esta_mode;
	}

	public void setEsta_mode(int esta_mode) {
		this.esta_mode = esta_mode;
	}
	
	
}
