package renta_detalle;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/RentaDetalle")
/* Extiende a javax.ws.rs.core.Application */
public class Renta_DetalleRestful extends Application {
	public Set<Class<?>> getClasses() {
		Set<Class<?>> set = new HashSet<Class<?>>();
		set.add(Renta_DetalleRS.class);
		return set;
	}
}