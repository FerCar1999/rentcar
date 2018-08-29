package vehiculo;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
@XmlRootElement( name = "vehiculo" )
public class Vehiculo {
	int codi_vehi;
	int codi_mode;
	String nomb_mode;
	String nomb_marc;
	String anio_vehi;
	int codi_tran;
	String nomb_tran;
	int pasa_vehi;
	int puer_vehi;
	int aire_vehi;
	String audi_vehi;
	int codi_comb;
	String nomb_comb;
	int codi_lice;
	String nomb_lice;
	int esta_vehi;
	String foto_vehi;
	double prec_vehi;
	int codi_tipo;
	String nomb_tipo;
	@XmlElement
	public int getCodi_vehi() {
		return codi_vehi;
	}
	
	public void setCodi_vehi(int codi_vehi) {
		this.codi_vehi = codi_vehi;
	}
	@XmlElement
	public int getCodi_mode() {
		return codi_mode;
	}
	
	public void setCodi_mode(int codi_mode) {
		this.codi_mode = codi_mode;
	}
	@XmlElement
	public String getAnio_vehi() {
		return anio_vehi;
	}
	
	public void setAnio_vehi(String anio_vehi) {
		this.anio_vehi = anio_vehi;
	}
	@XmlElement
	public int getCodi_tran() {
		return codi_tran;
	}
	
	public void setCodi_tran(int codi_tran) {
		this.codi_tran = codi_tran;
	}
	@XmlElement
	public int getPasa_vehi() {
		return pasa_vehi;
	}
	
	public void setPasa_vehi(int pasa_vehi) {
		this.pasa_vehi = pasa_vehi;
	}
	@XmlElement
	public int getPuer_vehi() {
		return puer_vehi;
	}
	
	public void setPuer_vehi(int puer_vehi) {
		this.puer_vehi = puer_vehi;
	}
	@XmlElement
	public int getAire_vehi() {
		return aire_vehi;
	}
	
	public void setAire_vehi(int aire_vehi) {
		this.aire_vehi = aire_vehi;
	}
	@XmlElement
	public String getAudi_vehi() {
		return audi_vehi;
	}
	
	public void setAudi_vehi(String audi_vehi) {
		this.audi_vehi = audi_vehi;
	}
	@XmlElement
	public int getCodi_comb() {
		return codi_comb;
	}
	
	public void setCodi_comb(int codi_comb) {
		this.codi_comb = codi_comb;
	}
	@XmlElement
	public int getCodi_lice() {
		return codi_lice;
	}
	
	public void setCodi_lice(int codi_lice) {
		this.codi_lice = codi_lice;
	}
	@XmlElement
	public int getEsta_vehi() {
		return esta_vehi;
	}
	
	public void setEsta_vehi(int esta_vehi) {
		this.esta_vehi = esta_vehi;
	}
	@XmlElement
	public String getFoto_vehi() {
		return foto_vehi;
	}
	
	public void setFoto_vehi(String foto_vehi) {
		this.foto_vehi = foto_vehi;
	}
	@XmlElement
	public double getPrec_vehi() {
		return prec_vehi;
	}
	
	public void setPrec_vehi(double prec_vehi) {
		this.prec_vehi = prec_vehi;
	}
	@XmlElement
	public int getCodi_tipo() {
		return codi_tipo;
	}
	
	public void setCodi_tipo(int codi_tipo) {
		this.codi_tipo = codi_tipo;
	}
	@XmlElement
	public String getNomb_mode() {
		return nomb_mode;
	}

	public void setNomb_mode(String nomb_mode) {
		this.nomb_mode = nomb_mode;
	}
	@XmlElement
	public String getNomb_marc() {
		return nomb_marc;
	}

	public void setNomb_marc(String nomb_marc) {
		this.nomb_marc = nomb_marc;
	}
	@XmlElement
	public String getNomb_tran() {
		return nomb_tran;
	}

	public void setNomb_tran(String nomb_tran) {
		this.nomb_tran = nomb_tran;
	}
	@XmlElement
	public String getNomb_comb() {
		return nomb_comb;
	}

	public void setNomb_comb(String nomb_comb) {
		this.nomb_comb = nomb_comb;
	}
	@XmlElement
	public String getNomb_lice() {
		return nomb_lice;
	}

	public void setNomb_lice(String nomb_lice) {
		this.nomb_lice = nomb_lice;
	}
	@XmlElement
	public String getNomb_tipo() {
		return nomb_tipo;
	}

	public void setNomb_tipo(String nomb_tipo) {
		this.nomb_tipo = nomb_tipo;
	}
	
	
}
