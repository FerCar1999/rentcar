package renta_detalle;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class Renta_DetalleRS {
	private static Renta_DetalleList renta_detalleList;

	public Renta_DetalleRS() {
	}

	@GET
	@Path("/xml")
	@Produces({ MediaType.APPLICATION_XML })
	public Renta_DetalleList getXml() {
		renta_detalleList = new Renta_DetalleList();
		return renta_detalleList;
	}

	@GET
	@Path("/xml/{nomb}")
	@Produces({ MediaType.APPLICATION_XML })
	public Renta_DetalleList getXml(@PathParam("nomb") String nomb) {
		renta_detalleList = new Renta_DetalleList(nomb);
		return renta_detalleList;
	}

	@GET
	@Path("/json")
	@Produces({ MediaType.APPLICATION_JSON })
	public Renta_DetalleList getJson() {
		renta_detalleList = new Renta_DetalleList();
		return renta_detalleList;
	}

	@GET
	@Path("/json/{nomb}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Renta_DetalleList getJson(@PathParam("nomb") String nomb) {
		renta_detalleList = new Renta_DetalleList(nomb);
		return renta_detalleList;
	}

	@POST
	@Path("/create")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response Update(@FormParam("nomb") String nomb) {
		renta_detalleList = new Renta_DetalleList(false);
		String msg;
		if (nomb == null) {
			msg = "Debe especificar el combustible";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		try {
			String resp = renta_detalleList.add(nomb);
			return Response.ok(resp, "text/plain").build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		msg = "Error";
		return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
	}

	@PUT
	@Path("/updateTotal")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response Update(@FormParam("codi") String codi,@FormParam("total") double total) {
		renta_detalleList = new Renta_DetalleList(false);
		String msg;
		if (total == null) {
			msg = "Debe especificar el total";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		try {
			String id = renta_detalleList.updateTotal(codi, total);
			msg = name + " Se ha modificado con el id: " + id;
			return Response.ok(msg, "text/plain").build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		msg = "No se modifico el album";
		return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
	}
	@PUT
	@Path("/updateEsta")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response Update(@FormParam("codi") String codi,@FormParam("esta") String esta) {
		renta_detalleList = new Renta_DetalleList(false);
		String msg;
		if (esta == null) {
			msg = "Debe especificar el estado";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		try {
			String id = renta_detalleList.updateEsta(codi, esta);
			msg = name + " Se ha modificado con el id: " + id;
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
		renta_detalleList = new Renta_DetalleList(false);
		int affectedRows = -1;
		try {
			affectedRows = renta_detalleList.delete(codi);
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