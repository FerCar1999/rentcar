package tipo_usuario;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
@XmlRootElement( name = "tipousuario" )
public class Tipo_Usuario {
	
	int codi_tipo;
	String tipo_usua;
	@XmlElement
	public int getCodi_tipo() {
		return codi_tipo;
	}

	public void setCodi_tipo(int codi_tipo) {
		this.codi_tipo = codi_tipo;
	}
@XmlElement
	public String getTipo_usua() {
		return tipo_usua;
	}

	public void setTipo_usua(String tipo_usua) {
		this.tipo_usua = tipo_usua;
	}
	
	
}
