package licencia;

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

import combustible.CombustibleList;

@Path("/")
public class LicenciaRS {
	private static LicenciaList licenciaList;

	public LicenciaRS() {
	}

	@GET
	@Path("/xml")
	@Produces({ MediaType.APPLICATION_XML })
	public LicenciaList getXml() {
		licenciaList = new LicenciaList();
		return licenciaList;
	}

	@GET
	@Path("/xml/{nomb}")
	@Produces({ MediaType.APPLICATION_XML })
	public LicenciaList getXml(@PathParam("nomb") String nomb) {
		licenciaList = new LicenciaList(nomb);
		return licenciaList;
	}

	@GET
	@Path("/json")
	@Produces({ MediaType.APPLICATION_JSON })
	public LicenciaList getJson() {
		licenciaList = new LicenciaList();
		return licenciaList;
	}

	@GET
	@Path("/json/{nomb}")
	@Produces({ MediaType.APPLICATION_JSON })
	public LicenciaList getJson(@PathParam("nomb") String nomb) {
		licenciaList = new LicenciaList(nomb);
		return licenciaList;
	}

	@POST
	@Path("/create")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response Update(@FormParam("nomb") String nomb) {
		licenciaList = new LicenciaList(false);
		String msg;
		if (nomb == null) {
			msg = "Debe especificar el combustible";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		try {
			String resp = licenciaList.add(nomb);
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
		licenciaList = new LicenciaList(false);
		String msg;
		if (nomb == null) {
			msg = "Debe especificar el nombre";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		try {
			String id = licenciaList.update(codi, nomb);
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
		licenciaList = new LicenciaList(false);
		int affectedRows = -1;
		try {
			affectedRows = licenciaList.delete(codi);
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