package imagenes_vehiculo;

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
public class Imagenes_VehiculoRS {
	private static Imagenes_VehiculoList imagenesVehiculoList;

	public Imagenes_VehiculoRS() {
	}

	@GET
	@Path("/xml")
	@Produces({ MediaType.APPLICATION_XML })
	public Imagenes_VehiculoList getXml() {
		imagenesVehiculoList = new Imagenes_VehiculoList();
		return imagenesVehiculoList;
	}

	@GET
	@Path("/xml/{vehi}")
	@Produces({ MediaType.APPLICATION_XML })
	public Imagenes_VehiculoList getXml(@PathParam("vehi") String vehi) {
		imagenesVehiculoList = new Imagenes_VehiculoList(vehi);
		return imagenesVehiculoList;
	}

	@GET
	@Path("/json")
	@Produces({ MediaType.APPLICATION_JSON })
	public Imagenes_VehiculoList getJson() {
		imagenesVehiculoList = new Imagenes_VehiculoList();
		return imagenesVehiculoList;
	}

	@GET
	@Path("/json/{vehi}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Imagenes_VehiculoList getJson(@PathParam("vehi") String vehi) {
		imagenesVehiculoList = new Imagenes_VehiculoList(vehi);
		return imagenesVehiculoList;
	}

	@POST
	@Path("/create")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response Update(@FormParam("vehi") int vehi,@FormParam("foto") String foto,@FormParam("esta") int esta) {
		imagenesVehiculoList = new Imagenes_VehiculoList(false);
		String msg;
		if (vehi == 0) {
			msg = "No se ha especificado el vehiculo";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (foto == null) {
			msg = "Debe agregar una foto";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		try {
			String resp = imagenesVehiculoList.add(vehi,foto,esta);
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
	public Response UpdateDos(@FormParam("codi") int codi, @FormParam("foto") String foto, @FormParam("esta") int esta) {
		imagenesVehiculoList = new Imagenes_VehiculoList(false);
		String msg;
		if (codi == 0) {
			msg = "No se ha especificado la foto a modificar";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (foto == null) {
			msg = "Debe agregar una foto";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		try {
			String id = imagenesVehiculoList.update(codi, foto, esta);
			msg = " Se ha modificado con el id: " + id;
			return Response.ok(msg, "text/plain").build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		msg = "No se modifico la imagen";
		return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
	}

	@DELETE
	@Path("/delete/{codi: \\d+}")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response delete(@PathParam("codi") int codi){
		imagenesVehiculoList = new Imagenes_VehiculoList(false);
		String msg;
			try {
				if (imagenesVehiculoList.delete(codi)) {
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