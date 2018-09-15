package marca;

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

@XmlRootElement(name = "marcaList")
@XmlSeeAlso({ Marca.class })
public class MarcaList {
	private Connection conn;
	private List<Marca> arts;
	String param;

	MarcaList() {
		arts = new CopyOnWriteArrayList<Marca>();
		param = null;
	}

	MarcaList(String nomb) {
		arts = new CopyOnWriteArrayList<Marca>();
		param = nomb;
	}

	MarcaList(boolean charge) {
		if (charge == false) {
//don't load data
		} else {
			arts = new CopyOnWriteArrayList<Marca>();
			param = null;
		}
	}

	@XmlElement
	public List getMarca() {
		try {
			arts = getMarcaList(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.arts;
	}

	public void setMarca(List<Marca> arts) {
		this.arts = arts;
	}

	public List<Marca> getMarcaList(String param) throws Exception {
		String whereQuery = "";
		if (param != null) {
				whereQuery = " AND codi_marc = '" + param + "'";
		}
		this.conn =  new Conexion().conn();
		PreparedStatement cmd = this.conn.prepareStatement("SELECT * FROM marca WHERE esta_marc = 1 " + whereQuery);
		ResultSet res = cmd.executeQuery();
		while (res.next()) {
			Marca tmpMarca = new Marca();
			tmpMarca.setCodi_marc(res.getInt(1));
			tmpMarca.setNomb_marc(res.getString(2));
			arts.add(tmpMarca);
		}
		return arts;
	}
	public String add(String nomb) throws Exception {
		String resp = "0";
		try {
			this.conn =  new Conexion().conn();
			PreparedStatement cmd = this.conn.prepareStatement("INSERT INTO marca VALUES (NULL, ?, 1)");
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
			this.conn =  new Conexion().conn();
			PreparedStatement cmd = this.conn.prepareStatement("UPDATE marca SET nomb_marc = ? WHERE codi_marc = ?");
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
			PreparedStatement cmd = this.conn.prepareStatement("UPDATE marca SET esta_marc = 0 WHERE codi_marc = ?");
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
