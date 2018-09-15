package marca;

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
public class MarcaRS {
	private static MarcaList marcaList;

	public MarcaRS() {
	}

	@GET
	@Path("/xml")
	@Produces({ MediaType.APPLICATION_XML })
	public MarcaList getXml() {
		marcaList = new MarcaList();
		return marcaList;
	}

	@GET
	@Path("/xml/{nomb}")
	@Produces({ MediaType.APPLICATION_XML })
	public MarcaList getXml(@PathParam("nomb") String nomb) {
		marcaList = new MarcaList(nomb);
		return marcaList;
	}

	@GET
	@Path("/json")
	@Produces({ MediaType.APPLICATION_JSON })
	public MarcaList getJson() {
		marcaList = new MarcaList();
		return marcaList;
	}

	@GET
	@Path("/json/{nomb}")
	@Produces({ MediaType.APPLICATION_JSON })
	public MarcaList getJson(@PathParam("nomb") String nomb) {
		marcaList = new MarcaList(nomb);
		return marcaList;
	}

	@POST
	@Path("/create")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response Update(@FormParam("nomb") String nomb) {
		marcaList = new MarcaList(false);
		String msg;
		if (nomb == null) {
			msg = "Debe especificar el combustible";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		try {
			String resp = marcaList.add(nomb);
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
		marcaList = new MarcaList(false);
		String msg;
		if (nomb == null) {
			msg = "Debe especificar el nombre";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		try {
			String id = marcaList.update(codi, nomb);
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
	public Response delete(@PathParam("codi") int codi){
		marcaList = new MarcaList(false);
		String msg;
			try {
				if (marcaList.delete(codi)) {
					msg = "Exito";
					return Response.ok(msg, "text/plain").build();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			msg = "No se pudo eliminar el registro";
		return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
	}
}