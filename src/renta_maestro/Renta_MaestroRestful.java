package renta_maestro;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
@ApplicationPath("/RentaMaestro")
/*Extiende a javax.ws.rs.core.Application*/
public class Renta_MaestroRestful extends Application{
public Set<Class<?>> getClasses(){
Set<Class<?>> set = new HashSet<Class<?>>();
set.add(Renta_MaestroRS.class);
return set;
}
}