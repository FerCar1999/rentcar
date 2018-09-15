package tipo_servicio;

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

@XmlRootElement(name = "tipoServicioList")
@XmlSeeAlso({ Tipo_Servicio.class })
public class Tipo_ServicioList {
	private List<Tipo_Servicio> arts;
	private Connection conn;
	String param;

	Tipo_ServicioList() {
		arts = new CopyOnWriteArrayList<Tipo_Servicio>();
		param = null;
	}

	Tipo_ServicioList(String nomb) {
		arts = new CopyOnWriteArrayList<Tipo_Servicio>();
		param = nomb;
	}

	Tipo_ServicioList(boolean charge) {
		if (charge == false) {
//don't load data
		} else {
			arts = new CopyOnWriteArrayList<Tipo_Servicio>();
			param = null;
		}
	}

	@XmlElement
	public List getTipoServicio() {
		try {
			arts = getTipoServicioList(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.arts;
	}

	public void setTipoServicio(List<Tipo_Servicio> arts) {
		this.arts = arts;
	}

	public List<Tipo_Servicio> getTipoServicioList(String param) throws Exception {
		String whereQuery = "";
		if (param != null) {
				whereQuery = " AND codi_tipo_serv = '" + param + "'";
		}
		this.conn = new Conexion().conn();
		PreparedStatement cmd = this.conn.prepareStatement("SELECT * FROM tipo_servicio WHERE esta_tipo_serv = 1 "+ whereQuery);
		ResultSet res = cmd.executeQuery();
		while (res.next()) {
			Tipo_Servicio tmpTipo_Servicio = new Tipo_Servicio();
			tmpTipo_Servicio.setCodi_tipo_serv(res.getInt(1));
			tmpTipo_Servicio.setNomb_serv(res.getString(2));
			tmpTipo_Servicio.setPrec_serv(res.getDouble(3));
			tmpTipo_Servicio.setEsta_tipo_serv(res.getInt(4));
			arts.add(tmpTipo_Servicio);
		}
		return arts;
	}
	public String add(String nomb, double prec) throws Exception {
		String resp = "0";
		try {
			this.conn = new Conexion().conn();
			PreparedStatement cmd = this.conn.prepareStatement("INSERT INTO tipo_servicio VALUES(NULL, ?, ?, 1)");
			cmd.setString(1, nomb);
			cmd.setDouble(2, prec);
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
	
	public String update(int codi,String nomb, double prec) throws Exception {
		String resp = "0";
		try {
			this.conn = new Conexion().conn();
			PreparedStatement cmd = this.conn.prepareStatement("UPDATE tipo_servicio SET nomb_serv = ?, prec_serv = ? WHERE codi_tipo_serv = ?");
			cmd.setString(1, nomb);
			cmd.setDouble(2, prec);
			cmd.setInt(3, codi);
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
			PreparedStatement cmd = this.conn.prepareStatement("UPDATE tipo_servicio SET esta_tipo_serv = 0 WHERE codi_tipo_serv = ?");
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