package transmision;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
@XmlRootElement( name = "transmision" )
public class Transmision {
	int codi_tran;
	String nomb_tran;
	@XmlElement
	public int getCodi_tran() {
		return codi_tran;
	}
	
	public void setCodi_tran(int codi_tran) {
		this.codi_tran = codi_tran;
	}
	@XmlElement
	public String getNomb_tran() {
		return nomb_tran;
	}

	public void setNomb_tran(String nomb_tran) {
		this.nomb_tran = nomb_tran;
	}
	
	
}
