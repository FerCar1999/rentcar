package combustible;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement( name = "combustible" )
public class Combustible {
	int codi_comb;
	String nomb_comb;
	@XmlElement
	public int getCodi_comb() {
		return codi_comb;
	}
	@XmlElement
	public void setCodi_comb(int codi_comb) {
		this.codi_comb = codi_comb;
	}
	@XmlElement
	public String getNomb_comb() {
		return nomb_comb;
	}
	@XmlElement
	public void setNomb_comb(String nomb_comb) {
		this.nomb_comb = nomb_comb;
	}
	
	
}
