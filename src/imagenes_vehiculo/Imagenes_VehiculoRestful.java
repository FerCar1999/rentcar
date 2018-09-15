package imagenes_vehiculo;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/ImagenesVehiculo")
/* Extiende a javax.ws.rs.core.Application */
public class Imagenes_VehiculoRestful extends Application {
	public Set<Class<?>> getClasses() {
		Set<Class<?>> set = new HashSet<Class<?>>();
		set.add(Imagenes_VehiculoRS.class);
		return set;
	}
}
