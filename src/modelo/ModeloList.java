package modelo;

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

@XmlRootElement(name = "modeloList")
@XmlSeeAlso({ Modelo.class })
public class ModeloList {
	private Connection conn;
	private List<Modelo> arts;
	String param;

	ModeloList() {
		arts = new CopyOnWriteArrayList<Modelo>();
		param = null;
	}

	ModeloList(String nomb) {
		arts = new CopyOnWriteArrayList<Modelo>();
		param = nomb;
	}

	ModeloList(boolean charge) {
		if (charge == false) {
//don't load data
		} else {
			arts = new CopyOnWriteArrayList<Modelo>();
			param = null;
		}
	}

	@XmlElement
	public List getModelo() {
		try {
			arts = getModeloList(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.arts;
	}

	public void setModelo(List<Modelo> arts) {
		this.arts = arts;
	}
	
	public List<Modelo> getModeloList(String param) throws Exception {
		String whereQuery = "";
		if (param != null) {
			whereQuery = " AND codi_mode = '" + param + "'";
		}
		this.conn = new Conexion().conn();
		PreparedStatement cmd = this.conn.prepareStatement("SELECT mode.codi_mode, mode.nomb_mode, marc.* FROM modelo as mode INNER JOIN marca as marc ON mode.codi_marc=marc.codi_marc WHERE esta_mode = 1 "+ whereQuery);
		ResultSet res = cmd.executeQuery();
		while (res.next()) {
			Modelo tmpModelo = new Modelo();
			tmpModelo.setCodi_mode(res.getInt(1));
			tmpModelo.setNomb_mode(res.getString(2));
			tmpModelo.setCodi_marc(res.getInt(3));
			tmpModelo.setNomb_marc(res.getString(4));
			tmpModelo.setEsta_mode(res.getInt(5));
			arts.add(tmpModelo);
		}
		return arts;
	}

	public String add(String nomb, int codi_marc) throws Exception {
		String resp = "0";
		try {
			this.conn = new Conexion().conn();
			PreparedStatement cmd = this.conn.prepareStatement("INSERT INTO modelo VALUES(NULL,?, ?, 1)");
			cmd.setString(1, nomb);
			cmd.setInt(2, codi_marc);
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

	public String update(int codi, String nomb, int codi_marc) throws Exception {
		String resp = "0";
		try {
			this.conn = new Conexion().conn();
			PreparedStatement cmd = this.conn.prepareStatement("UPDATE modelo SET nomb_mode = ?, codi_marc = ? WHERE codi_mode=?");
			cmd.setString(1, nomb);
			cmd.setInt(2, codi_marc);
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
			PreparedStatement cmd = this.conn
					.prepareStatement("UPDATE modelo SET esta_mode = 0 WHERE codi_mode = ?");
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
