package transmision;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/Transmision")
/* Extiende a javax.ws.rs.core.Application */
public class TransmisionRestful extends Application {
	public Set<Class<?>> getClasses() {
		Set<Class<?>> set = new HashSet<Class<?>>();
		set.add(TransmisionRS.class);
		return set;
	}
}