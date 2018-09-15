package licencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import conexion.Conexion;

@XmlRootElement(name = "licenciaList")
@XmlSeeAlso({ Licencia.class })
public class LicenciaList {
	private Connection conn;
	private List<Licencia> arts;
	String param;

	LicenciaList() {
		arts = new CopyOnWriteArrayList<Licencia>();
		param = null;
	}

	LicenciaList(String nomb) {
		arts = new CopyOnWriteArrayList<Licencia>();
		param = nomb;
	}

	LicenciaList(boolean charge) {
		if (charge == false) {
//don't load data
		} else {
			arts = new CopyOnWriteArrayList<Licencia>();
			param = null;
		}
	}

	@XmlElement
	public List getLicencia() {
		try {
			arts = getLicenciaList(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.arts;
	}

	public void setLicencia(List<Licencia> arts) {
		this.arts = arts;
	}

	public List<Licencia> getLicenciaList(String param) throws Exception {
		String whereQuery = "";
		if (param != null) {
				whereQuery = " AND codi_lice = '" + param + "'";
		}
		this.conn = new Conexion().conn();
		PreparedStatement cmd = this.conn.prepareStatement("SELECT * FROM licencia WHERE esta_lice = 1 " + whereQuery);
		ResultSet res = cmd.executeQuery();
		while (res.next()) {
			Licencia tmpLicencia = new Licencia();
			tmpLicencia.setCodi_lice(Integer.parseInt(res.getString(1)));
			tmpLicencia.setNomb_lice(res.getString(2));
			tmpLicencia.setEsta_lice(Integer.parseInt(res.getString(3)));
			arts.add(tmpLicencia);
		}
		return arts;
	}
	public String add(String nomb) throws Exception {
		String resp = "0";
		try {
			this.conn = new Conexion().conn();
			PreparedStatement cmd = this.conn.prepareStatement("INSERT INTO licencia VALUES(NULL, ?, 1)");
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
			PreparedStatement cmd = this.conn.prepareStatement("UPDATE licencia SET nomb_lice = ? WHERE codi_lice = ?");
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

	public int delete(int codi) throws Exception {
		int affectedRows = -1;
		String sql = "UPDATE licencia SET esta_lice = 0  WHERE codi_lice= " + codi;
		this.conn = new Conexion().conn();
		Statement st = conn.createStatement();
		affectedRows = st.executeUpdate(sql);
		return affectedRows;
	}
}

