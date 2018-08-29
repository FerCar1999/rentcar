package login;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@ApplicationPath("/Login")
/*Extiende a javax.ws.rs.core.Application*/
public class LoginRestful extends Application{
	public Set<Class<?>> getClasses(){
		Set<Class<?>> set = new HashSet<Class<?>>();
		set.add(LoginRS.class);
		return set;
	}
}
