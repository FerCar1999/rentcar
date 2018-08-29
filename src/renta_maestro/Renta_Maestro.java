package renta_maestro;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
@XmlRootElement( name = "rentamaestro" )
public class Renta_Maestro {
	int codi_rent_maes;
	int codi_rent_deta;
	int codi_tipo_serv;
	String tipo_serv;
	String desc_maes;
	@XmlElement
	public int getCodi_rent_maes() {
		return codi_rent_maes;
	}

	public void setCodi_rent_maes(int codi_rent_maes) {
		this.codi_rent_maes = codi_rent_maes;
	}
@XmlElement
	public int getCodi_rent_deta() {
		return codi_rent_deta;
	}

	public void setCodi_rent_deta(int codi_rent_deta) {
		this.codi_rent_deta = codi_rent_deta;
	}
@XmlElement
	public int getCodi_tipo_serv() {
		return codi_tipo_serv;
	}

	public void setCodi_tipo_serv(int codi_tipo_serv) {
		this.codi_tipo_serv = codi_tipo_serv;
	}
	@XmlElement
public String getTipo_serv() {
		return tipo_serv;
	}

	public void setTipo_serv(String tipo_serv) {
		this.tipo_serv = tipo_serv;
	}

@XmlElement
	public String getDesc_maes() {
		return desc_maes;
	}

	public void setDesc_maes(String desc_maes) {
		this.desc_maes = desc_maes;
	}
}
