package renta_detalle;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
@XmlRootElement( name = "rentadetalle" )
public class Renta_Detalle {
	int codi_rent_deta;
	int codi_usua;
	int codi_vehi;
	String fech_rent;
	String fech_devo;
	double tota_deta;
	int tipo_pago;
	int esta_rent;
	@XmlElement
	public int getCodi_rent_deta() {
		return codi_rent_deta;
	}
	
	public void setCodi_rent_deta(int codi_rent_deta) {
		this.codi_rent_deta = codi_rent_deta;
	}
	@XmlElement
	public int getCodi_usua() {
		return codi_usua;
	}
	
	public void setCodi_usua(int codi_usua) {
		this.codi_usua = codi_usua;
	}
	@XmlElement
	public int getCodi_vehi() {
		return codi_vehi;
	}

	public void setCodi_vehi(int codi_vehi) {
		this.codi_vehi = codi_vehi;
	}
@XmlElement
	public String getFech_rent() {
		return fech_rent;
	}

	public void setFech_rent(String fech_rent) {
		this.fech_rent = fech_rent;
	}
@XmlElement
	public String getFech_devo() {
		return fech_devo;
	}

	public void setFech_devo(String fech_devo) {
		this.fech_devo = fech_devo;
	}
@XmlElement
	public double getTota_deta() {
		return tota_deta;
	}

	public void setTota_deta(double tota_deta) {
		this.tota_deta = tota_deta;
	}
@XmlElement
	public int getTipo_pago() {
		return tipo_pago;
	}

	public void setTipo_pago(int tipo_pago) {
		this.tipo_pago = tipo_pago;
	}
@XmlElement
	public int getEsta_rent() {
		return esta_rent;
	}

	public void setEsta_rent(int esta_rent) {
		this.esta_rent = esta_rent;
	}
}
