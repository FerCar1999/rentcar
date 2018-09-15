package vehiculo;

import java.io.IOException;
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

import vehiculo.VehiculoList;

/**
 * Servlet implementation class VehiculoRS
 */
@Path("/")
public class VehiculoRS {
	private static VehiculoList vehiculoList;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VehiculoRS() {
		super();
		// TODO Auto-generated constructor stub
	}

	@GET
	@Path("/xml")
	@Produces({ MediaType.APPLICATION_XML })
	public VehiculoList getXml() {
		vehiculoList = new VehiculoList();
		return vehiculoList;
	}

	@GET
	@Path("/xml/{nomb}")
	@Produces({ MediaType.APPLICATION_XML })
	public VehiculoList getXml(@PathParam("nomb") String nomb) {
		vehiculoList = new VehiculoList(nomb);
		return vehiculoList;
	}

	@GET
	@Path("/json")
	@Produces({ MediaType.APPLICATION_JSON })
	public VehiculoList getJson() {
		vehiculoList = new VehiculoList();
		return vehiculoList;
	}

	@GET
	@Path("/json/{nomb}")
	@Produces({ MediaType.APPLICATION_JSON })
	public VehiculoList getJson(@PathParam("nomb") String nomb) {
		vehiculoList = new VehiculoList(nomb);
		return vehiculoList;
	}

	@POST
	@Path("/create")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response Update(@FormParam("codi_mode") int codi_mode, @FormParam("anio_vehi") String anio_vehi,
			@FormParam("codi_tran") int codi_tran, @FormParam("pasa_vehi") int pasa_vehi,
			@FormParam("puer_vehi") int puer_vehi, @FormParam("aire_vehi") int aire_vehi,
			@FormParam("audi_vehi") String audi_vehi, @FormParam("codi_comb") int codi_comb,
			@FormParam("codi_lice") int codi_lice, @FormParam("codi_tipo") int codi_tipo,
			@FormParam("depo_vehi") double depo_vehi) {
		vehiculoList = new VehiculoList(false);
		String msg;
		if (codi_mode < 1) {
			msg = "Debe especificar el modelo del vehiculo";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (anio_vehi == null) {
			msg = "Debe especificar el año del vehiculo";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (codi_tran < 1) {
			msg = "codi_tran";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (pasa_vehi < 1) {
			msg = "Debe especificar la cantidad de pasajeros";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (puer_vehi < 1) {
			msg = "Debe especificar la cantidad de puertas";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (aire_vehi < 1) {
			msg = "Debe especificar si el carro posee A/C o no";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (audi_vehi == null) {
			msg = "audi_vehi";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (codi_comb < 1) {
			msg = "codi_comb";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (codi_lice < 1) {
			msg = "Debe especificar el tipo de licencia";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (codi_tipo < 1) {
			msg = "Debe especificar el tipo del vehiculo";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		try {
			String resp = vehiculoList.add(codi_mode, anio_vehi, codi_tran, pasa_vehi, puer_vehi, aire_vehi, audi_vehi,
					codi_comb, codi_lice, codi_tipo, depo_vehi);
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
	public Response Update(@FormParam("codi_vehi") int codi_vehi, @FormParam("codi_mode") int codi_mode,
			@FormParam("anio_vehi") String anio_vehi, @FormParam("codi_tran") int codi_tran,
			@FormParam("pasa_vehi") int pasa_vehi, @FormParam("puer_vehi") int puer_vehi,
			@FormParam("aire_vehi") int aire_vehi, @FormParam("audi_vehi") String audi_vehi,
			@FormParam("codi_comb") int codi_comb, @FormParam("codi_lice") int codi_lice,
			@FormParam("codi_tipo") int codi_tipo, @FormParam("depo_vehi") double depo_vehi) {
		vehiculoList = new VehiculoList(false);
		String msg;
		if (codi_vehi < 1) {
			msg = "Debe especificar el  vehiculo";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (codi_mode < 1) {
			msg = "Debe especificar el modelo del vehiculo";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (anio_vehi == null) {
			msg = "Debe especificar el año del vehiculo";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (codi_tran < 1) {
			msg = "codi_tran";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (pasa_vehi < 1) {
			msg = "Debe especificar la cantidad de pasajeros";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (puer_vehi < 1) {
			msg = "Debe especificar la cantidad de puertas";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (aire_vehi < 1) {
			msg = "Debe especificar si el carro posee A/C o no";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (audi_vehi == null) {
			msg = "audi_vehi";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (codi_comb < 1) {
			msg = "codi_comb";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (codi_lice < 1) {
			msg = "Debe especificar el tipo de licencia";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (codi_tipo < 1) {
			msg = "Debe especificar el tipo del vehiculo";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		try {
			String id = vehiculoList.update(codi_vehi, codi_mode, anio_vehi, codi_tran, pasa_vehi, puer_vehi, aire_vehi,
					audi_vehi, codi_comb, codi_lice, codi_tipo, depo_vehi);
			msg = " Se ha modificado con el id: " + id;
			return Response.ok(msg, "text/plain").build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		msg = "No se modifico el vehiculo";
		return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
	}

	@DELETE
	@Path("/delete/{codi: \\d+}")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response delete(@PathParam("codi") int codi) {
		vehiculoList = new VehiculoList(false);
		int affectedRows = -1;
		try {
			affectedRows = vehiculoList.delete(codi);
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
