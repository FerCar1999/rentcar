package tipo_vehiculo;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
@ApplicationPath("/Combustible")
/*Extiende a javax.ws.rs.core.Application*/
public class Tipo_VehiculoRestful extends Application{
public Set<Class<?>> getClasses(){
Set<Class<?>> set = new HashSet<Class<?>>();
set.add(Tipo_VehiculoRS.class);
return set;
}
}