package usuario;


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

import usuario.UsuarioList;
/**
 * Servlet implementation class UsuarioRS
 */
@Path("/")
public class UsuarioRS {
	private static UsuarioList usuarioList;
    public UsuarioRS() {
        super();
        // TODO Auto-generated constructor stub
    }
    @GET
	@Path("/xml")
	@Produces({ MediaType.APPLICATION_XML })
	public UsuarioList getXml() {
		usuarioList = new UsuarioList();
		return usuarioList;
	}

	@GET
	@Path("/xml/{nomb}")
	@Produces({ MediaType.APPLICATION_XML })
	public UsuarioList getXml(@PathParam("nomb") String nomb) {
		usuarioList = new UsuarioList(nomb);
		return usuarioList;
	}

	@GET
	@Path("/json")
	@Produces({ MediaType.APPLICATION_JSON })
	public UsuarioList getJson() {
		usuarioList = new UsuarioList();
		return usuarioList;
	}

	@GET
	@Path("/json/{nomb}")
	@Produces({ MediaType.APPLICATION_JSON })
	public UsuarioList getJson(@PathParam("nomb") String nomb) {
		usuarioList = new UsuarioList(nomb);
		return usuarioList;
	}

	@POST
	@Path("/create")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response Update(@FormParam("nomb_usua") String nomb_usua,@FormParam("apel_usua") String apel_usua,@FormParam("dire_usua") String dire_usua,@FormParam("tele_usua") String tele_usua,
			@FormParam("dui_usua") String dui_usua,@FormParam("nit_usua") String nit_usua,@FormParam("pasa_usua") String pasa_usua,@FormParam("corr_usua") String corr_usua,@FormParam("codi_tipo") int codi_tipo,
			@FormParam("cont_usua") String cont_usua) {
		usuarioList = new UsuarioList(false);
		String msg;
		if (nomb_usua == null) {
			msg = "Debe especificar el nombre del usuario";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (apel_usua == null) {
			msg = "Debe especificar el apellido del usuario";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (dire_usua == null) {
			msg = "Debe especificar la direccion del usuario";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (tele_usua == null) {
			msg = "Debe especificar el telefono del usuario";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (dui_usua == null) {
			msg = "Debe especificar dui del usuario";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (nit_usua == null) {
			msg = "Debe especificar el nit del usuario";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (pasa_usua == null) {
			msg = "Debe especificar el pasaporte del usuario";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (corr_usua == null) {
			msg = "Debe especificar el correo del usuario";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (codi_tipo < 1) {
			msg = "Debe especificar el tipo de usuario";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (cont_usua == null) {
			msg = "Debe especificar la contraseña del usuario";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		try {
			String resp = usuarioList.add(nomb_usua,apel_usua,dire_usua,tele_usua,dui_usua, nit_usua,pasa_usua,  corr_usua, codi_tipo, cont_usua);
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
	public Response Update(@FormParam("codi_usua") int codi_usua,@FormParam("nomb_usua") String nomb_usua,@FormParam("apel_usua") String apel_usua,@FormParam("dire_usua") String dire_usua,@FormParam("tele_usua") String tele_usua,
			@FormParam("dui_usua") String dui_usua,@FormParam("nit_usua") String nit_usua,@FormParam("pasa_usua") String pasa_usua,@FormParam("corr_usua") String corr_usua,@FormParam("codi_tipo") int codi_tipo,
			@FormParam("cont_usua") String cont_usua) {
		usuarioList = new UsuarioList(false);
		String msg;
		if (codi_usua < 0) {
			msg = "Debe seleccionar un usuario";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (nomb_usua == null) {
			msg = "Debe especificar el nombre del usuario";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (apel_usua == null) {
			msg = "Debe especificar el apellido del usuario";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (dire_usua == null) {
			msg = "Debe especificar la direccion del usuario";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (tele_usua == null) {
			msg = "Debe especificar el telefono del usuario";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (dui_usua == null) {
			msg = "Debe especificar dui del usuario";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (nit_usua == null) {
			msg = "Debe especificar el nit del usuario";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (pasa_usua == null) {
			msg = "Debe especificar el pasaporte del usuario";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (corr_usua == null) {
			msg = "Debe especificar el correo del usuario";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (codi_tipo < 1) {
			msg = "Debe especificar el tipo de usuario";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (cont_usua == null) {
			msg = "Debe especificar la contraseña del usuario";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		try {
			String id = usuarioList.update(codi_usua, nomb_usua,apel_usua,dire_usua,tele_usua,dui_usua, nit_usua,pasa_usua,  corr_usua, codi_tipo, cont_usua);
			msg = " Se ha modificado con el id: " + id;
			return Response.ok(msg, "text/plain").build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		msg = "No se modifico el usuario";
		return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
	}

	@DELETE
	@Path("/delete/{codi: \\d+}")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response delete(@PathParam("codi") int codi) {
		usuarioList = new UsuarioList(false);
		int affectedRows = -1;
		try {
			affectedRows = usuarioList.delete(codi);
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
