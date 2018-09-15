package tipo_vehiculo;
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

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import conexion.Conexion;

@XmlRootElement(name = "tipoVehiculoList")
@XmlSeeAlso({ Tipo_Vehiculo.class })
public class Tipo_VehiculoList {
	private List<Tipo_Vehiculo> arts;
	private Connection conn;
	String param;

	Tipo_VehiculoList() {
		arts = new CopyOnWriteArrayList<Tipo_Vehiculo>();
		param = null;
	}

	Tipo_VehiculoList(String nomb) {
		arts = new CopyOnWriteArrayList<Tipo_Vehiculo>();
		param = nomb;
	}

	Tipo_VehiculoList(boolean charge) {
		if (charge == false) {
//don't load data
		} else {
			arts = new CopyOnWriteArrayList<Tipo_Vehiculo>();
			param = null;
		}
	}

	@XmlElement
	public List getTipoVehiculo() {
		try {
			arts = getTipoVehiculoList(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.arts;
	}

	public void setTipoVehiculo(List<Tipo_Vehiculo> arts) {
		this.arts = arts;
	}

	public List<Tipo_Vehiculo> getTipoVehiculoList(String param) throws Exception {
		String whereQuery = "";
		if (param != null) {
				whereQuery = " AND codi_tipo = '" + param + "'";
		}
		this.conn = new Conexion().conn();
		PreparedStatement cmd = this.conn.prepareStatement("SELECT * FROM tipo_vehiculo WHERE esta_tipo_vehi=1 "+ whereQuery);
		ResultSet res = cmd.executeQuery();
		while (res.next()) {
			Tipo_Vehiculo tmpTipo_Vehiculo = new Tipo_Vehiculo();
			tmpTipo_Vehiculo.setCodi_tipo(res.getInt(1));
			tmpTipo_Vehiculo.setTipo_vehi(res.getString(2));
			tmpTipo_Vehiculo.setEsta_tipo_vehi(res.getInt(3));
			arts.add(tmpTipo_Vehiculo);
		}
		return arts;
	}
	public String add(String nomb) throws Exception {
		String resp = "0";
		try {
			this.conn = new Conexion().conn();
			PreparedStatement cmd = this.conn.prepareStatement("INSERT INTO tipo_vehiculo VALUES(NULL, ?, 1)");
			cmd.setString(1, nomb);
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
	
	public String update(int codi,String nomb) throws Exception {
		String resp = "0";
		try {
			this.conn = new Conexion().conn();
			PreparedStatement cmd = this.conn.prepareStatement("UPDATE tipo_vehiculo SET tipo_vehi=? WHERE codi_tipo_vehi=?");
			cmd.setString(1, nomb);
			cmd.setInt(2, codi);
			cmd.executeUpdate();
			resp= "1";
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
			this.conn = new Conexion().conn() ;
			PreparedStatement cmd = this.conn.prepareStatement("UPDATE tipo_vehiculo SET esta_tipo_vehi= 0 WHERE codi_tipo_vehi= ?");
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