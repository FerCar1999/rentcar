package tipo_usuario;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/TipoUsuario")
/* Extiende a javax.ws.rs.core.Application */
public class Tipo_UsuarioRestful extends Application {
	public Set<Class<?>> getClasses() {
		Set<Class<?>> set = new HashSet<Class<?>>();
		set.add(Tipo_UsuarioRS.class);
		return set;
	}
}