package vehiculo;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import vehiculo.VehiculoRS;
@ApplicationPath("/Vehiculo")
/*Extiende a javax.ws.rs.core.Application*/
public class VehiculoRestful extends Application{
	public Set<Class<?>> getClasses(){
		Set<Class<?>> set = new HashSet<Class<?>>();
		set.add(VehiculoRS.class);
		return set;
	}
}
