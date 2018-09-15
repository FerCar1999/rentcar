package inventario_vehiculo;

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
public class Inventario_VehiculoRS {
	private static Inventario_VehiculoList inventarioVehiculoList;

	public Inventario_VehiculoRS() {
	}

	@GET
	@Path("/xml")
	@Produces({ MediaType.APPLICATION_XML })
	public Inventario_VehiculoList getXml() {
		inventarioVehiculoList = new Inventario_VehiculoList();
		return inventarioVehiculoList;
	}

	@GET
	@Path("/xml/{vehi}")
	@Produces({ MediaType.APPLICATION_XML })
	public Inventario_VehiculoList getXml(@PathParam("vehi") String vehi) {
		inventarioVehiculoList = new Inventario_VehiculoList(vehi);
		return inventarioVehiculoList;
	}

	@GET
	@Path("/json")
	@Produces({ MediaType.APPLICATION_JSON })
	public Inventario_VehiculoList getJson() {
		inventarioVehiculoList = new Inventario_VehiculoList();
		return inventarioVehiculoList;
	}

	@GET
	@Path("/json/{vehi}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Inventario_VehiculoList getJson(@PathParam("vehi") String vehi) {
		inventarioVehiculoList = new Inventario_VehiculoList(vehi);
		return inventarioVehiculoList;
	}

	@POST
	@Path("/create")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response Update(@FormParam("vehi") int vehi,@FormParam("cant") int cant) {
		inventarioVehiculoList = new Inventario_VehiculoList(false);
		String msg;
		if (vehi == 0) {
			msg = "No se ha especificado el vehiculo";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (cant <= 0) {
			msg = "La cantidad del automovil tiene que ser mayor a 0";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		try {
			String resp = inventarioVehiculoList.add(vehi,cant);
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
	public Response UpdateDos(@FormParam("vehi") int vehi, @FormParam("cant") int cant) {
		inventarioVehiculoList = new Inventario_VehiculoList(false);
		String msg;
		if (vehi == 0) {
			msg = "No se ha especificado el vehiculo a modificar";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (cant <= 0) {
			msg = "La cantidad del automovil tiene que ser mayor a 0";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		try {
			String id = inventarioVehiculoList.update(vehi,cant);
			msg = " Se ha modificado con el id: " + id;
			return Response.ok(msg, "text/plain").build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		msg = "No se modifico el descuento";
		return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
	}

	@DELETE
	@Path("/delete/{codi: \\d+}")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response delete(@PathParam("codi") int codi){
		inventarioVehiculoList = new Inventario_VehiculoList(false);
		String msg;
			try {
				if (inventarioVehiculoList.delete(codi)) {
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