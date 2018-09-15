package descuento_vehiculo;

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
@XmlSeeAlso({ Descuento_Vehiculo.class })
public class Descuento_VehiculoList {
	private Connection conn;
	private List<Descuento_Vehiculo> arts;
	String param;

	Descuento_VehiculoList() {
		arts = new CopyOnWriteArrayList<Descuento_Vehiculo>();
		param = null;
	}

	Descuento_VehiculoList(String vehi) {
		arts = new CopyOnWriteArrayList<Descuento_Vehiculo>();
		param = vehi;
	}

	Descuento_VehiculoList(boolean charge) {
		if (charge == false) {
//don't load data
		} else {
			arts = new CopyOnWriteArrayList<Descuento_Vehiculo>();
			param = null;
		}
	}

	@XmlElement
	public List getDescuentoVehiculo() {
		try {
			arts = getDescuentoVehiculoList(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.arts;
	}

	public void setDescuentoVehiculo(List<Descuento_Vehiculo> arts) {
		this.arts = arts;
	}

	public List<Descuento_Vehiculo> getDescuentoVehiculoList(String param) throws Exception {
		String whereQuery = "";
		if (param != null) {
				whereQuery = " WHERE codi_vehi= '" + param + "' ";
		}
		this.conn = new Conexion().conn() ;
		PreparedStatement cmd = this.conn.prepareStatement("SELECT * FROM descuento_vehiculo " + whereQuery);
		ResultSet res = cmd.executeQuery();
		while (res.next()) {
			Descuento_Vehiculo tmpDescuentoVehiculo = new Descuento_Vehiculo();
			tmpDescuentoVehiculo.setCodi_desc(Integer.parseInt(res.getString(1)));
			tmpDescuentoVehiculo.setCodi_vehi(Integer.parseInt(res.getString(2)));
			tmpDescuentoVehiculo.setMin_dia(Integer.parseInt(res.getString(3)));
			tmpDescuentoVehiculo.setMax_dia(Integer.parseInt(res.getString(4)));
			tmpDescuentoVehiculo.setPrec_vehi(Double.parseDouble(res.getString(5)));
			arts.add(tmpDescuentoVehiculo);
		}
		return arts;
	}
	public String add(int vehi, int max, int min, double prec) throws Exception {
		String resp = "0";
		try {
			this.conn = new Conexion().conn() ;
			PreparedStatement cmd = conn.prepareStatement("INSERT INTO descuento_vehiculo VALUES (NULL, ?, ?, ?, ?)");
			cmd.setInt(1, vehi);
			cmd.setInt(2, min);
			cmd.setInt(3, max);
			cmd.setDouble(4, prec);
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
	
	public String update(int codi,int max, int min, double prec) throws Exception {
		String resp = "0";
		try {
			this.conn = new Conexion().conn() ;
			PreparedStatement cmd = this.conn.prepareStatement("UPDATE descuento_vehiculo SET min_dia = ?, max_dia = ?, prec_vehi = ? WHERE codi_desc = ?");
			cmd.setInt(1, min);
			cmd.setInt(2, max);
			cmd.setDouble(3, prec);
			cmd.setInt(4, codi);
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
			PreparedStatement cmd = this.conn.prepareStatement("DELETE FROM descuento_vehiculo WHERE codi_desc = ?");
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