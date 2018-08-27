package tipo_usuario;

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

@Path("/")
public class Tipo_UsuarioRS {
	private static Tipo_UsuarioList tipo_usuarioList;

	public Tipo_UsuarioRS() {
	}

	@GET
	@Path("/xml")
	@Produces({ MediaType.APPLICATION_XML })
	public Tipo_UsuarioList getXml() {
		tipo_usuarioList = new Tipo_UsuarioList();
		return tipo_usuarioList;
	}

	@GET
	@Path("/xml/{nomb}")
	@Produces({ MediaType.APPLICATION_XML })
	public Tipo_UsuarioList getXml(@PathParam("nomb") String nomb) {
		tipo_usuarioList = new Tipo_UsuarioList(nomb);
		return tipo_usuarioList;
	}

	@GET
	@Path("/json")
	@Produces({ MediaType.APPLICATION_JSON })
	public Tipo_UsuarioList getJson() {
		tipo_usuarioList = new Tipo_UsuarioList();
		return tipo_usuarioList;
	}

	@GET
	@Path("/json/{nomb}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Tipo_UsuarioList getJson(@PathParam("nomb") String nomb) {
		tipo_usuarioList = new Tipo_UsuarioList(nomb);
		return tipo_usuarioList;
	}

	@POST
	@Path("/create")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response Update(@FormParam("nomb") String nomb) {
		tipo_usuarioList = new Tipo_UsuarioList(false);
		String msg;
		if (nomb == null) {
			msg = "Debe especificar el combustible";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		try {
			String resp = tipo_usuarioList.add(nomb);
			return Response.ok(resp, "text/plain").build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		msg = "Error";
		return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
	}

	@PUT
	@Path("/update")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response Update(@FormParam("codi") int codi,@FormParam("nomb") String nomb) {
		tipo_usuarioList = new Tipo_UsuarioList(false);
		String msg;
		if (nomb == null) {
			msg = "Debe especificar el nombre del album";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		try {
			String id = tipo_usuarioList.update(codi, nomb);
			msg = " Se ha modificado con el id: " + id;
			return Response.ok(msg, "text/plain").build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		msg = "No se modifico el album";
		return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
	}

	@DELETE
	@Path("/delete/{codi: \\d+}")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response delete(@PathParam("codi") int codi) {
		tipo_usuarioList = new Tipo_UsuarioList(false);
		int affectedRows = -1;
		try {
			affectedRows = tipo_usuarioList.delete(codi);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String msg;
		if (affectedRows == 0) {
			msg = "Ese registro no existe";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		} else if (affectedRows == -1) {
			msg = "No se pudo borrar";
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(msg).type(MediaType.TEXT_PLAIN)
					.build();
		} else {
			msg = "Exito";
		}
		return Response.ok(msg, "text/plain").build();
	}
}