package login;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import login.LoginList;


/**
 * Servlet implementation class UsuarioRS
 */
@Path("/")
public class LoginRS {
	private static LoginList loginList;
	public LoginRS() {
		super();
		// TODO Auto-generated constructor stub
	}
	@GET
	@Path("/json/{corr}")
	@Produces({ MediaType.APPLICATION_JSON })
	public LoginList getXml(@PathParam("corr") String corr) {
		loginList = new LoginList(corr);
		return loginList;
	}


}
