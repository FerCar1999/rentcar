package vehiculo;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
@XmlRootElement( name = "vehiculo" )
public class Vehiculo {
	int codi_vehi;
	int codi_mode;
	String anio_vehi;
	int codi_tran;
	int pasa_vehi;
	int puer_vehi;
	int aire_vehi;
	String audi_vehi;
	int codi_comb;
	int codi_lice;
	int esta_vehi;
	String foto_vehi;
	double prec_vehi;
	int codi_tipo;
	
	@XmlElement
	public int getCodi_vehi() {
		return codi_vehi;
	}
	@XmlElement
	public void setCodi_vehi(int codi_vehi) {
		this.codi_vehi = codi_vehi;
	}
	@XmlElement
	public int getCodi_mode() {
		return codi_mode;
	}
	@XmlElement
	public void setCodi_mode(int codi_mode) {
		this.codi_mode = codi_mode;
	}
	@XmlElement
	public String getAnio_vehi() {
		return anio_vehi;
	}
	@XmlElement
	public void setAnio_vehi(String anio_vehi) {
		this.anio_vehi = anio_vehi;
	}
	@XmlElement
	public int getCodi_tran() {
		return codi_tran;
	}
	@XmlElement
	public void setCodi_tran(int codi_tran) {
		this.codi_tran = codi_tran;
	}
	@XmlElement
	public int getPasa_vehi() {
		return pasa_vehi;
	}
	@XmlElement
	public void setPasa_vehi(int pasa_vehi) {
		this.pasa_vehi = pasa_vehi;
	}
	@XmlElement
	public int getPuer_vehi() {
		return puer_vehi;
	}
	@XmlElement
	public void setPuer_vehi(int puer_vehi) {
		this.puer_vehi = puer_vehi;
	}
	@XmlElement
	public int getAire_vehi() {
		return aire_vehi;
	}
	@XmlElement
	public void setAire_vehi(int aire_vehi) {
		this.aire_vehi = aire_vehi;
	}
	@XmlElement
	public String getAudi_vehi() {
		return audi_vehi;
	}
	@XmlElement
	public void setAudi_vehi(String audi_vehi) {
		this.audi_vehi = audi_vehi;
	}
	@XmlElement
	public int getCodi_comb() {
		return codi_comb;
	}
	@XmlElement
	public void setCodi_comb(int codi_comb) {
		this.codi_comb = codi_comb;
	}
	@XmlElement
	public int getCodi_lice() {
		return codi_lice;
	}
	@XmlElement
	public void setCodi_lice(int codi_lice) {
		this.codi_lice = codi_lice;
	}
	@XmlElement
	public int getEsta_vehi() {
		return esta_vehi;
	}
	@XmlElement
	public void setEsta_vehi(int esta_vehi) {
		this.esta_vehi = esta_vehi;
	}
	@XmlElement
	public String getFoto_vehi() {
		return foto_vehi;
	}
	@XmlElement
	public void setFoto_vehi(String foto_vehi) {
		this.foto_vehi = foto_vehi;
	}
	@XmlElement
	public double getPrec_vehi() {
		return prec_vehi;
	}
	@XmlElement
	public void setPrec_vehi(double prec_vehi) {
		this.prec_vehi = prec_vehi;
	}
	@XmlElement
	public int getCodi_tipo() {
		return codi_tipo;
	}
	@XmlElement
	public void setCodi_tipo(int codi_tipo) {
		this.codi_tipo = codi_tipo;
	}
}
