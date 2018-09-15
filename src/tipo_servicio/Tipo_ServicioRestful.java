package tipo_servicio;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/TipoServicio")
/* Extiende a javax.ws.rs.core.Application */
public class Tipo_ServicioRestful extends Application {
	public Set<Class<?>> getClasses() {
		Set<Class<?>> set = new HashSet<Class<?>>();
		set.add(Tipo_ServicioRS.class);
		return set;
	}
}