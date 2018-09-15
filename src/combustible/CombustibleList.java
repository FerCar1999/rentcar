package combustible;

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

@XmlRootElement(name = "combustibleList")
@XmlSeeAlso({ Combustible.class })
public class CombustibleList {
	private Connection conn;
	private List<Combustible> arts;
	String param;

	CombustibleList() {
		arts = new CopyOnWriteArrayList<Combustible>();
		param = null;
	}

	CombustibleList(String nomb) {
		arts = new CopyOnWriteArrayList<Combustible>();
		param = nomb;
	}

	CombustibleList(boolean charge) {
		if (charge == false) {
//don't load data
		} else {
			arts = new CopyOnWriteArrayList<Combustible>();
			param = null;
		}
	}

	@XmlElement
	public List getCombustible() {
		try {
			arts = getCombustibleList(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.arts;
	}

	public void setCombustible(List<Combustible> arts) {
		this.arts = arts;
	}

	public List<Combustible> getCombustibleList(String param) throws Exception {
		String whereQuery = "";
		if (param != null) {
				whereQuery = " AND codi_comb = '" + param + "' ";
		}
		this.conn = new Conexion().conn() ;
		PreparedStatement cmd = this.conn.prepareStatement("SELECT * FROM combustible WHERE esta_comb = 1" + whereQuery);
		ResultSet res = cmd.executeQuery();
		while (res.next()) {
			Combustible tmpCombustible = new Combustible();
			tmpCombustible.setCodi_comb(Integer.parseInt(res.getString(1)));
			tmpCombustible.setNomb_comb(res.getString(2));
			tmpCombustible.setEsta_Comb(Integer.parseInt(res.getString(3)));
			arts.add(tmpCombustible);
		}
		return arts;
	}
	public String add(String nomb) throws Exception {
		String resp = "0";
		try {
			this.conn = new Conexion().conn() ;
			PreparedStatement cmd = conn.prepareStatement("INSERT INTO combustible VALUES (NULL, ?, 1)");
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
			this.conn = new Conexion().conn() ;
			PreparedStatement cmd = this.conn.prepareStatement("UPDATE combustible SET nomb_comb = ? WHERE codi_comb = ?");
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
			PreparedStatement cmd = this.conn.prepareStatement("UPDATE combustible SET esta_comb = 0 WHERE codi_comb = ?");
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
