package inventario_vehiculo;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "inventariovehiculo")
public class Inventario_Vehiculo {
	int codi_inve, codi_vehi, cant_vehi;
	@XmlElement
	public int getCodi_inve() {
		return codi_inve;
	}

	public void setCodi_inve(int codi_inve) {
		this.codi_inve = codi_inve;
	}
	@XmlElement
	public int getCodi_vehi() {
		return codi_vehi;
	}

	public void setCodi_vehi(int codi_vehi) {
		this.codi_vehi = codi_vehi;
	}
	@XmlElement
	public int getCant_vehi() {
		return cant_vehi;
	}

	public void setCant_vehi(int cant_vehi) {
		this.cant_vehi = cant_vehi;
	}
	
}
