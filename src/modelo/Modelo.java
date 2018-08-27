package modelo;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
@XmlRootElement( name = "modelo" )
public class Modelo {
	int codi_mode;
	String nomb_mode;
	int codi_marc;
	@XmlElement
	public int getCodi_mode() {
		return codi_mode;
	}
@XmlElement
	public void setCodi_mode(int codi_mode) {
		this.codi_mode = codi_mode;
	}
@XmlElement
	public String getNomb_mode() {
		return nomb_mode;
	}
@XmlElement
	public void setNomb_mode(String nomb_mode) {
		this.nomb_mode = nomb_mode;
	}
@XmlElement
	public int getCodi_marc() {
		return codi_marc;
	}
@XmlElement
	public void setCodi_marc(int codi_marc) {
		this.codi_marc = codi_marc;
	}
}
