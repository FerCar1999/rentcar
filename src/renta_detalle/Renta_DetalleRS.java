package renta_detalle;

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
	public Response UpdateCero(@FormParam("usua") int usua,@FormParam("vehi") int vehi,@FormParam("fechRe") String fechRe,@FormParam("fechDe") String fechDe,@FormParam("tipo") int tipo) {
		renta_detalleList = new Renta_DetalleList(false);
		String msg;
		if (usua == 0) {
			msg = "No esta logeado";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		try {
			String resp = renta_detalleList.add(usua,vehi,fechRe,fechDe,tipo);
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
	public Response UpdateUno(@FormParam("codi") int codi,@FormParam("total") double total) {
		renta_detalleList = new Renta_DetalleList(false);
		String msg;
		if (total == 0.00) {
			msg = "El total no puede ser 0";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		try {
			String id = renta_detalleList.updateTotal(codi, total);
			msg = " Se ha modificado con el id: " + id;
			return Response.ok(msg, "text/plain").build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		msg = "No se modifico";
		return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
	}
	@PUT
	@Path("/updateEsta")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response UpdateDos(@FormParam("codi") int codi) {
		renta_detalleList = new Renta_DetalleList(false);
		String msg;
		try {
			String id = renta_detalleList.updateEstado(codi);
			msg = " Se ha modificado con el id: " + id;
			return Response.ok(msg, "text/plain").build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		msg = "No se modifico";
		return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
	}

}