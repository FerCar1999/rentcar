package tipo_pago;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
@ApplicationPath("/Combustible")
/*Extiende a javax.ws.rs.core.Application*/
public class Tipo_PagoRestful extends Application{
public Set<Class<?>> getClasses(){
Set<Class<?>> set = new HashSet<Class<?>>();
set.add(Tipo_PagoRS.class);
return set;
}
}