package descuento_vehiculo;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "descuentovehiculo")
public class Descuento_Vehiculo {
	int codi_desc, codi_vehi, min_dia, max_dia;
	double prec_vehi;

	@XmlElement
	public int getCodi_desc() {
		return codi_desc;
	}

	public void setCodi_desc(int codi_desc) {
		this.codi_desc = codi_desc;
	}

	@XmlElement
	public int getCodi_vehi() {
		return codi_vehi;
	}

	public void setCodi_vehi(int codi_vehi) {
		this.codi_vehi = codi_vehi;
	}

	@XmlElement
	public int getMin_dia() {
		return min_dia;
	}

	public void setMin_dia(int min_dia) {
		this.min_dia = min_dia;
	}

	@XmlElement
	public int getMax_dia() {
		return max_dia;
	}

	public void setMax_dia(int max_dia) {
		this.max_dia = max_dia;
	}

	@XmlElement
	public double getPrec_vehi() {
		return prec_vehi;
	}

	public void setPrec_vehi(double prec_vehi) {
		this.prec_vehi = prec_vehi;
	}

}
