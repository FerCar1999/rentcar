package tipo_servicio;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
@XmlRootElement( name = "tiposervicio" )
public class Tipo_Servicio {
	int codi_tipo_serv;
	String nomb_serv;
	double prec_serv;
	@XmlElement
	public int getCodi_tipo_serv() {
		return codi_tipo_serv;
	}

	public void setCodi_tipo_serv(int codi_tipo_serv) {
		this.codi_tipo_serv = codi_tipo_serv;
	}
@XmlElement
	public String getNomb_serv() {
		return nomb_serv;
	}

	public void setNomb_serv(String nomb_serv) {
		this.nomb_serv = nomb_serv;
	}
@XmlElement
	public double getPrec_serv() {
		return prec_serv;
	}

	public void setPrec_serv(double prec_serv) {
		this.prec_serv = prec_serv;
	}
}