package renta_detalle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import conexion.Conexion;

@XmlRootElement(name = "rentaDetalleList")
@XmlSeeAlso({ Renta_Detalle.class })
public class Renta_DetalleList {
	private Connection conn;
	private List<Renta_Detalle> arts;
	String param;

	Renta_DetalleList() {
		arts = new CopyOnWriteArrayList<Renta_Detalle>();
		param = null;
	}

	Renta_DetalleList(String nomb) {
		arts = new CopyOnWriteArrayList<Renta_Detalle>();
		param = nomb;
	}

	Renta_DetalleList(boolean charge) {
		if (charge == false) {
//don't load data
		} else {
			arts = new CopyOnWriteArrayList<Renta_Detalle>();
			param = null;
		}
	}

	@XmlElement
	public List getRentaDetalle() {
		try {
			arts = getRentaDetalleList(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.arts;
	}

	public void setRentaDetalle(List<Renta_Detalle> arts) {
		this.arts = arts;
	}

	public List<Renta_Detalle> getRentaDetalleList(String param) throws Exception {
		String whereQuery = "";
		if (param != null) {
			whereQuery = " WHERE renta_detalle.codi_rent_deta = '" + param + "'";
		}
		this.conn = new Conexion().conn();
		PreparedStatement cmd = this.conn.prepareStatement("SELECT renta_detalle.codi_rent_deta, usuario.codi_usua, usuario.nomb_usua, usuario.apel_usua, vehiculo.codi_vehi, modelo.nomb_mode, marca.nomb_marc, renta_detalle.fech_rent, renta_detalle.fech_devo, renta_detalle.tota_deta, tipo_pago.codi_tipo_pago, tipo_pago.tipo_pago, renta_detalle.esta_rent  FROM (((((renta_detalle INNER JOIN usuario ON renta_detalle.codi_usua = usuario.codi_usua)INNER JOIN vehiculo ON renta_detalle.codi_vehi = vehiculo.codi_vehi)INNER JOIN modelo ON vehiculo.codi_mode = modelo.codi_mode) INNER JOIN marca ON modelo.codi_marc= marca.codi_marc)INNER JOIN tipo_pago ON renta_detalle.tipo_pago=tipo_pago.codi_tipo_pago) "+ whereQuery);
		ResultSet res = cmd.executeQuery();
		while (res.next()) {
			Renta_Detalle tmpRenta_Detalle = new Renta_Detalle();
			tmpRenta_Detalle.setCodi_rent_deta(res.getInt(1));
			tmpRenta_Detalle.setCodi_usua(res.getInt(2));
			tmpRenta_Detalle.setNomb_usua(res.getString(3));
			tmpRenta_Detalle.setApel_usua(res.getString(4));
			tmpRenta_Detalle.setCodi_vehi(res.getInt(5));
			tmpRenta_Detalle.setMode_vehi(res.getString(6));
			tmpRenta_Detalle.setMarc_vehi(res.getString(7));
			tmpRenta_Detalle.setFech_rent(res.getString(8));
			tmpRenta_Detalle.setFech_devo(res.getString(9));
			tmpRenta_Detalle.setTota_deta(res.getDouble(10));
			tmpRenta_Detalle.setTipo_pago(res.getInt(11));
			tmpRenta_Detalle.setNomb_pago(res.getString(12));
			tmpRenta_Detalle.setEsta_rent(res.getInt(13));
			arts.add(tmpRenta_Detalle);
		}
		return arts;
	}

	public String add(int codi_usua, int codi_vehi, String fech_rent, String fech_devo, int tipo_pago)
			throws Exception {
		String resp = "0";
		try {
			this.conn = new Conexion().conn();
			PreparedStatement cmd = this.conn.prepareStatement("INSERT INTO renta_detalle VALUES(NULL,?,?,?,?,(SELECT prec_vehi FROM descuento_vehiculo WHERE descuento_vehiculo.codi_vehi = ?),?,0)");
			cmd.setInt(1, codi_usua);
			cmd.setInt(2, codi_vehi);
			cmd.setString(3, fech_rent);
			cmd.setString(4, fech_devo);
			cmd.setInt(5, codi_vehi);
			cmd.setInt(6, tipo_pago);
			cmd.executeUpdate();
			resp = "1";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}

	public int add() {
		return 0;
	}

	public String updateTotal(int codi, double total) throws Exception {
		String resp = "0";
		try {
			this.conn= new Conexion().conn();
			PreparedStatement cmd = this.conn.prepareStatement("UPDATE renta_detalle SET tota_deta = ? WHERE codi_rent_deta=?");
			cmd.setDouble(1, total);
			cmd.setInt(2, codi);
			cmd.executeUpdate();
			resp = "1";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}

	public int updateTotal() {
		return 0;
	}

	public String updateEstado(int codi) throws Exception {
		String resp = "0";
		try {
			this.conn = new Conexion().conn();
			PreparedStatement cmd = this.conn.prepareStatement("UPDATE renta_detalle SET esta_rent = 1 WHERE codi_rent_deta = ?");
			cmd.setInt(1, codi);
			cmd.executeUpdate();
			resp = "1";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}

	public int updateEstado() {
		return 0;
	}
}
