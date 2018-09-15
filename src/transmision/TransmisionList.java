package transmision;

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

@XmlRootElement(name = "transmisionList")
@XmlSeeAlso({ Transmision.class })
public class TransmisionList {
	private List<Transmision> arts;
	private Connection conn;
	String param;

	TransmisionList() {
		arts = new CopyOnWriteArrayList<Transmision>();
		param = null;
	}

	TransmisionList(String nomb) {
		arts = new CopyOnWriteArrayList<Transmision>();
		param = nomb;
	}

	TransmisionList(boolean charge) {
		if (charge == false) {
//don't load data
		} else {
			arts = new CopyOnWriteArrayList<Transmision>();
			param = null;
		}
	}

	@XmlElement
	public List getTransmision() {
		try {
			arts = getTransmisionList(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.arts;
	}

	public void setTransmision(List<Transmision> arts) {
		this.arts = arts;
	}
	
	public List<Transmision> getTransmisionList(String param) throws Exception {
		String whereQuery = "";
		if (param != null) {
				whereQuery = " AND codi_tran = '" + param + "'";
		}
		this.conn = new Conexion().conn();
		PreparedStatement cmd = this.conn.prepareStatement("SELECT * FROM transmision wHERE esta_tran=1 "+whereQuery);
		ResultSet res = cmd.executeQuery();
		while (res.next()) {
			Transmision tmpTransmision = new Transmision();
			tmpTransmision.setCodi_tran(res.getInt(1));
			tmpTransmision.setNomb_tran(res.getString(2));
			tmpTransmision.setEsta_tran(res.getInt(3));
			arts.add(tmpTransmision);
		}
		return arts;
	}
	public String add(String nomb) throws Exception {
		String resp = "0";
		try {
			this.conn = new Conexion().conn();
			PreparedStatement cmd = this.conn.prepareStatement("INSERT INTO transmision VALUES(NULL, ?, 1)");
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
			PreparedStatement cmd = this.conn.prepareStatement("UPDATE transmision SET nomb_tran=? WHERE codi_tran=?");
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
			PreparedStatement cmd = this.conn.prepareStatement("UPDATE transmision SET esta_tran = 0 WHERE codi_tran= ?");
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