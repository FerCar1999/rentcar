package licencia;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/Licencia")
/* Extiende a javax.ws.rs.core.Application */
public class LicenciaRestful extends Application {
	public Set<Class<?>> getClasses() {
		Set<Class<?>> set = new HashSet<Class<?>>();
		set.add(LicenciaRS.class);
		return set;
	}
}