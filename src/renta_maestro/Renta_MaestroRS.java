package renta_maestro;

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
public class Renta_MaestroRS {
	private static Renta_MaestroList rentaMaestroList;

	public Renta_MaestroRS() {
	}

	@GET
	@Path("/xml")
	@Produces({ MediaType.APPLICATION_XML })
	public Renta_MaestroList getXml() {
		rentaMaestroList = new Renta_MaestroList();
		return rentaMaestroList;
	}

	@GET
	@Path("/xml/{nomb}")
	@Produces({ MediaType.APPLICATION_XML })
	public Renta_MaestroList getXml(@PathParam("nomb") String nomb) {
		rentaMaestroList = new Renta_MaestroList(nomb);
		return rentaMaestroList;
	}

	@GET
	@Path("/json")
	@Produces({ MediaType.APPLICATION_JSON })
	public Renta_MaestroList getJson() {
		rentaMaestroList = new Renta_MaestroList();
		return rentaMaestroList;
	}

	@GET
	@Path("/json/{nomb}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Renta_MaestroList getJson(@PathParam("nomb") String nomb) {
		rentaMaestroList = new Renta_MaestroList(nomb);
		return rentaMaestroList;
	}

	@POST
	@Path("/create")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response Update(@FormParam("usua") int usua, @FormParam("serv") int serv, @FormParam("desc") String desc) {
		rentaMaestroList = new Renta_MaestroList(false);
		String msg;
		if (usua == 0) {
			msg = "Debe estar logeado";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		try {
			String resp = rentaMaestroList.add(usua, serv, desc);
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
	public Response UpdateX(@FormParam("codi") int codi,@FormParam("serv") int serv, @FormParam("desc") String desc) {
		rentaMaestroList = new Renta_MaestroList(false);
		String msg;
		if (codi == 0) {
			msg = "Debe especificar el maestro";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		try {
			String id = rentaMaestroList.update(codi, serv, desc);
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
		rentaMaestroList = new Renta_MaestroList(false);
		int affectedRows = -1;
		try {
			affectedRows = rentaMaestroList.delete(codi);
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