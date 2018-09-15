package licencia;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement( name = "licencia" )
public class Licencia {
	int codi_lice;
	String nomb_lice;
	int esta_lice;
	@XmlElement
	public int getCodi_lice() {
		return codi_lice;
	}
	
	public void setCodi_lice(int codi_lice) {
		this.codi_lice = codi_lice;
	}
	@XmlElement
	public String getNomb_lice() {
		return nomb_lice;
	}
	
	public void setNomb_lice(String nomb_lice) {
		this.nomb_lice = nomb_lice;
	}
	
	@XmlElement
	public int getEsta_lice() {
		return esta_lice;
	}
	
	public void setEsta_lice(int esta_lice) {
		this.esta_lice = esta_lice;
	}
}
