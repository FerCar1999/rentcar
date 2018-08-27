package marca;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
@ApplicationPath("/Marca")
/*Extiende a javax.ws.rs.core.Application*/
public class MarcaRestful extends Application{
public Set<Class<?>> getClasses(){
Set<Class<?>> set = new HashSet<Class<?>>();
set.add(MarcaRS.class);
return set;
}
}