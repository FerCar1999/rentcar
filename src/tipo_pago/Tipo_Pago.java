package tipo_pago;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
@XmlRootElement( name = "tipopago" )
public class Tipo_Pago {
	int codi_tipo_pago;
	String tipo_pago;
	@XmlElement
	public int getCodi_tipo_pago() {
		return codi_tipo_pago;
	}
@XmlElement
	public void setCodi_tipo_pago(int codi_tipo_pago) {
		this.codi_tipo_pago = codi_tipo_pago;
	}
@XmlElement
	public String getTipo_pago() {
		return tipo_pago;
	}
@XmlElement
	public void setTipo_pago(String tipo_pago) {
		this.tipo_pago = tipo_pago;
	}
	
	
}
