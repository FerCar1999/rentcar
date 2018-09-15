package usuario;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import usuario.UsuarioRS;

@ApplicationPath("/Usuario")
/* Extiende a javax.ws.rs.core.Application */
public class UsuarioRestful extends Application {
	public Set<Class<?>> getClasses() {
		Set<Class<?>> set = new HashSet<Class<?>>();
		set.add(UsuarioRS.class);
		return set;
	}
}
