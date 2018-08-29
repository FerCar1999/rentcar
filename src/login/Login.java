package login;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
@XmlRootElement( name = "login" )
public class Login {
	int codi_usua;
	String nomb_usua;
	String apel_usua;
	String dire_usua;
	String tele_usua;
	String dui_usua;
	String nit_usua;
	String pasa_usua;
	String corr_usua;
	String cont_usua;
	int codi_tipo;
	String nomb_tipo;
	@XmlElement
	public int getCodi_usua() {
		return codi_usua;
	}
	
	public void setCodi_usua(int codi_usua) {
		this.codi_usua = codi_usua;
	}
	@XmlElement
	public String getNomb_usua() {
		return nomb_usua;
	}
	
	public void setNomb_usua(String nomb_usua) {
		this.nomb_usua = nomb_usua;
	}
	@XmlElement
	public String getApel_usua() {
		return apel_usua;
	}
	
	public void setApel_usua(String apel_usua) {
		this.apel_usua = apel_usua;
	}
	@XmlElement
	public String getDire_usua() {
		return dire_usua;
	}
	
	public void setDire_usua(String dire_usua) {
		this.dire_usua = dire_usua;
	}
	@XmlElement
	public String getTele_usua() {
		return tele_usua;
	}
	
	public void setTele_usua(String tele_usua) {
		this.tele_usua = tele_usua;
	}
	@XmlElement
	public String getDui_usua() {
		return dui_usua;
	}
	
	public void setDui_usua(String dui_usua) {
		this.dui_usua = dui_usua;
	}
	@XmlElement
	public String getNit_usua() {
		return nit_usua;
	}
	
	public void setNit_usua(String nit_usua) {
		this.nit_usua = nit_usua;
	}
	@XmlElement
	public String getPasa_usua() {
		return pasa_usua;
	}
	
	public void setPasa_usua(String pasa_usua) {
		this.pasa_usua = pasa_usua;
	}
	@XmlElement
	public String getCorr_usua() {
		return corr_usua;
	}
	
	public void setCorr_usua(String corr_usua) {
		this.corr_usua = corr_usua;
	}
	@XmlElement
	public String getCont_usua() {
		return cont_usua;
	}
	
	public void setCont_usua(String cont_usua) {
		this.cont_usua = cont_usua;
	}
	@XmlElement
	public int getCodi_tipo() {
		return codi_tipo;
	}
	
	public void setCodi_tipo(int codi_tipo) {
		this.codi_tipo = codi_tipo;
	}
	@XmlElement
	public String getNomb_tipo() {
		return nomb_tipo;
	}

	public void setNomb_tipo(String nomb_tipo) {
		this.nomb_tipo = nomb_tipo;
	}
}
