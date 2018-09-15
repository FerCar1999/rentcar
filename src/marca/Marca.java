package marca;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
@XmlRootElement( name = "marca" )
public class Marca {
	int codi_marc;
	String nomb_marc;
	int esta_marc;
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
	public int getEsta_marc() {
		return esta_marc;
	}

	public void setEsta_marc(int esta_marc) {
		this.esta_marc = esta_marc;
	}
	
}
