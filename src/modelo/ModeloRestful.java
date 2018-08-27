package modelo;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
@ApplicationPath("/Modelo")
/*Extiende a javax.ws.rs.core.Application*/
public class ModeloRestful extends Application{
public Set<Class<?>> getClasses(){
Set<Class<?>> set = new HashSet<Class<?>>();
set.add(ModeloRS.class);
return set;
}
}