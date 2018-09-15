package tipo_vehiculo;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
@XmlRootElement( name = "tipovehiculo" )
public class Tipo_Vehiculo {
	int codi_tipo;
	String tipo_vehi;
	int esta_tipo_vehi;
	@XmlElement
	public int getCodi_tipo() {
		return codi_tipo;
	}

	public void setCodi_tipo(int codi_tipo) {
		this.codi_tipo = codi_tipo;
	}
	@XmlElement
	public String getTipo_vehi() {
		return tipo_vehi;
	}

	public void setTipo_vehi(String tipo_vehi) {
		this.tipo_vehi = tipo_vehi;
	}
	@XmlElement
	public int getEsta_tipo_vehi() {
		return esta_tipo_vehi;
	}

	public void setEsta_tipo_vehi(int esta_tipo_vehi) {
		this.esta_tipo_vehi = esta_tipo_vehi;
	}
	
	
}
