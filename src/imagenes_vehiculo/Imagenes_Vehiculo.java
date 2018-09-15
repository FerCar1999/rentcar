package imagenes_vehiculo;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "imagenesvehiculo")
public class Imagenes_Vehiculo {
	int codi_imag, codi_vehi, esta_imag;
	String foto_vehi;
	@XmlElement
	public int getCodi_imag() {
		return codi_imag;
	}
	public void setCodi_imag(int codi_imag) {
		this.codi_imag = codi_imag;
	}
	@XmlElement
	public int getCodi_vehi() {
		return codi_vehi;
	}
	public void setCodi_vehi(int codi_vehi) {
		this.codi_vehi = codi_vehi;
	}
	@XmlElement
	public int getEsta_imag() {
		return esta_imag;
	}
	public void setEsta_imag(int esta_imag) {
		this.esta_imag = esta_imag;
	}
	@XmlElement
	public String getFoto_vehi() {
		return foto_vehi;
	}
	public void setFoto_vehi(String foto_vehi) {
		this.foto_vehi = foto_vehi;
	}
	
	
}
