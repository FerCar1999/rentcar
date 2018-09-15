package descuento_vehiculo;

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
public class Descuento_VehiculoRS {
	private static Descuento_VehiculoList descuentoVehiculoList;

	public Descuento_VehiculoRS() {
	}

	@GET
	@Path("/xml")
	@Produces({ MediaType.APPLICATION_XML })
	public Descuento_VehiculoList getXml() {
		descuentoVehiculoList = new Descuento_VehiculoList();
		return descuentoVehiculoList;
	}

	@GET
	@Path("/xml/{vehi}")
	@Produces({ MediaType.APPLICATION_XML })
	public Descuento_VehiculoList getXml(@PathParam("vehi") String vehi) {
		descuentoVehiculoList = new Descuento_VehiculoList(vehi);
		return descuentoVehiculoList;
	}

	@GET
	@Path("/json")
	@Produces({ MediaType.APPLICATION_JSON })
	public Descuento_VehiculoList getJson() {
		descuentoVehiculoList = new Descuento_VehiculoList();
		return descuentoVehiculoList;
	}

	@GET
	@Path("/json/{vehi}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Descuento_VehiculoList getJson(@PathParam("vehi") String vehi) {
		descuentoVehiculoList = new Descuento_VehiculoList(vehi);
		return descuentoVehiculoList;
	}

	@POST
	@Path("/create")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response Update(@FormParam("vehi") int vehi,@FormParam("min") int min,@FormParam("max") int max,@FormParam("prec") double prec) {
		descuentoVehiculoList = new Descuento_VehiculoList(false);
		String msg;
		if (vehi == 0) {
			msg = "No se ha especificado el vehiculo";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (min == 0) {
			msg = "La cantidad de dias tiene que ser mayor a 0";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (max == 0) {
			msg = "La cantidad de dias tiene que ser mayor a 0";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (min >= max ) {
			msg = "La cantidad minima de dias no puede ser mayor a la cantidad maxima";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (prec == 0) {
			msg = "No se ha especificado el precio";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		try {
			String resp = descuentoVehiculoList.add(vehi,max,min,prec);
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
	public Response UpdateDos(@FormParam("codi") int codi, @FormParam("max") int max,@FormParam("min") int min,@FormParam("prec") double prec) {
		descuentoVehiculoList = new Descuento_VehiculoList(false);
		String msg;
		if (codi == 0) {
			msg = "No se ha especificado el descuento a modificar";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (min == 0) {
			msg = "La cantidad de dias tiene que ser mayor a 0";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (max == 0) {
			msg = "La cantidad de dias tiene que ser mayor a 0";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (min >= max ) {
			msg = "La cantidad minima de dias no puede ser mayor a la cantidad maxima";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		if (prec == 0) {
			msg = "No se ha especificado el precio";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		try {
			String id = descuentoVehiculoList.update(codi, max, min, prec);
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
		descuentoVehiculoList = new Descuento_VehiculoList(false);
		String msg;
			try {
				if (descuentoVehiculoList.delete(codi)) {
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