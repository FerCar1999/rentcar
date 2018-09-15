package inventario_vehiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import combustible.Combustible;
import conexion.Conexion;

@XmlRootElement(name = "descuentoVehiculoList")
@XmlSeeAlso({ Inventario_Vehiculo.class })
public class Inventario_VehiculoList {
	private Connection conn;
	private List<Inventario_Vehiculo> arts;
	String param;

	Inventario_VehiculoList() {
		arts = new CopyOnWriteArrayList<Inventario_Vehiculo>();
		param = null;
	}

	Inventario_VehiculoList(String vehi) {
		arts = new CopyOnWriteArrayList<Inventario_Vehiculo>();
		param = vehi;
	}

	Inventario_VehiculoList(boolean charge) {
		if (charge == false) {
			// don't load data
		} else {
			arts = new CopyOnWriteArrayList<Inventario_Vehiculo>();
			param = null;
		}
	}

	@XmlElement
	public List getInventarioVehiculo() {
		try {
			arts = getInventarioVehiculoList(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.arts;
	}

	public void setInventarioVehiculo(List<Inventario_Vehiculo> arts) {
		this.arts = arts;
	}

	public List<Inventario_Vehiculo> getInventarioVehiculoList(String param) throws Exception {
		String whereQuery = "";
		if (param != null) {
			whereQuery = " WHERE codi_vehi= '" + param + "' ";
		}
		this.conn = new Conexion().conn();
		PreparedStatement cmd = this.conn.prepareStatement("SELECT * FROM inventario_vehiculo " + whereQuery);
		ResultSet res = cmd.executeQuery();
		while (res.next()) {
			Inventario_Vehiculo tmpInventarioVehiculo = new Inventario_Vehiculo();
			tmpInventarioVehiculo.setCodi_inve(res.getInt(1));
			tmpInventarioVehiculo.setCodi_vehi(res.getInt(2));
			tmpInventarioVehiculo.setCant_vehi(res.getInt(3));
			arts.add(tmpInventarioVehiculo);
		}
		return arts;
	}

	public String add(int vehi, int cant) throws Exception {
		String resp = "0";
		try {
			this.conn = new Conexion().conn();
			PreparedStatement cmd = conn.prepareStatement("INSERT INTO inventario_vehiculo VALUES (NULL, ?, ?)");
			cmd.setInt(1, vehi);
			cmd.setInt(2, cant);
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

	public String update(int vehi, int cant) throws Exception {
		String resp = "0";
		try {
			this.conn = new Conexion().conn();
			PreparedStatement cmd = this.conn.prepareStatement("UPDATE inventario_vehiculo SET cant_vehi = ? WHERE codi_vehi= ?");
			cmd.setInt(1, cant);
			cmd.setInt(2, vehi);
			cmd.executeUpdate();
			resp = "1";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}

	public int update() {
		return 0;
	}

	public boolean delete(int codi) throws Exception {
		boolean resp = false;
		try {
			this.conn = new Conexion().conn();
			PreparedStatement cmd = this.conn.prepareStatement("DELETE FROM inventario_vehiculo WHERE codi_inve = ?");
			cmd.setInt(1, codi);
			cmd.executeUpdate();
			resp = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}

	public int delete() {
		return 0;
	}
}