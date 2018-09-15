package imagenes_vehiculo;

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

@XmlRootElement(name = "imagenesVehiculoList")
@XmlSeeAlso({ Imagenes_Vehiculo.class })
public class Imagenes_VehiculoList {
	private Connection conn;
	private List<Imagenes_Vehiculo> arts;
	String param;

	Imagenes_VehiculoList() {
		arts = new CopyOnWriteArrayList<Imagenes_Vehiculo>();
		param = null;
	}

	Imagenes_VehiculoList(String vehi) {
		arts = new CopyOnWriteArrayList<Imagenes_Vehiculo>();
		param = vehi;
	}

	Imagenes_VehiculoList(boolean charge) {
		if (charge == false) {
//don't load data
		} else {
			arts = new CopyOnWriteArrayList<Imagenes_Vehiculo>();
			param = null;
		}
	}

	@XmlElement
	public List getImagenesVehiculo() {
		try {
			arts = getImagenesVehiculoList(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.arts;
	}

	public void setImagenesVehiculo(List<Imagenes_Vehiculo> arts) {
		this.arts = arts;
	}

	public List<Imagenes_Vehiculo> getImagenesVehiculoList(String param) throws Exception {
		String whereQuery = "";
		if (param != null) {
			whereQuery = " WHERE codi_vehi= '" + param + "' ";
		}
		this.conn = new Conexion().conn();
		PreparedStatement cmd = this.conn.prepareStatement("SELECT * FROM imagenes_vehiculo " + whereQuery);
		ResultSet res = cmd.executeQuery();
		while (res.next()) {
			Imagenes_Vehiculo tmpImagenesVehiculo = new Imagenes_Vehiculo();
			tmpImagenesVehiculo.setCodi_imag(res.getInt(1));
			tmpImagenesVehiculo.setCodi_vehi(res.getInt(2));
			tmpImagenesVehiculo.setFoto_vehi(res.getString(3));
			tmpImagenesVehiculo.setEsta_imag(res.getInt(4));
			arts.add(tmpImagenesVehiculo);
		}
		return arts;
	}

	public String add(int vehi, String foto, int esta) throws Exception {
		String resp = "0";
		try {
			this.conn = new Conexion().conn();
			PreparedStatement cmd = conn.prepareStatement("INSERT INTO imagenes_vehiculo VALUES (NULL, ?, ?, ?)");
			cmd.setInt(1, vehi);
			cmd.setString(2, foto);
			cmd.setInt(3, esta);
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

	public String update(int codi, String foto, int esta) throws Exception {
		String resp = "0";
		try {
			this.conn = new Conexion().conn();
			PreparedStatement cmd = this.conn.prepareStatement("UPDATE imagenes_vehiculo SET foto_vehi = ?, esta_imag= ? WHERE codi_imag = ?");
			cmd.setString(1, foto);
			cmd.setInt(2, esta);
			cmd.setInt(3, codi);
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
			PreparedStatement cmd = this.conn.prepareStatement("DELETE FROM imagenes_vehiculo WHERE codi_imag = ?");
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
