package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name = "modeloList")
@XmlSeeAlso({ Modelo.class })
public class ModeloList {
	private List<Modelo> arts;
	String url = "jdbc:mysql://localhost:3306/";
	String dbName = "rentadb";
	String driver = "com.mysql.jdbc.Driver";
	String userName = "root";
	String password = "";
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

	public Connection conn()
			throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		Class.forName(driver).newInstance();
		Connection conn = DriverManager.getConnection(url + dbName, userName, password);
		return conn;
	}

	public List<Modelo> getModeloList(String param) throws Exception {
		String whereQuery = "";
		if (param != null) {
				whereQuery = " WHERE codi_mode = '" + param + "'";
		}
		Connection conn = conn();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery("SELECT * FROM modelo " + whereQuery);
		while (res.next()) {
			Modelo tmpModelo = new Modelo();
			tmpModelo.setCodi_mode(Integer.parseInt(res.getString("codi_mode")));
			tmpModelo.setNomb_mode(res.getString("nomb_mode"));
			tmpModelo.setCodi_marc(Integer.parseInt(res.getString("codi_marc")));
			arts.add(tmpModelo);
		}
		return arts;
	}
	public String add(String nomb, String codi_marc) throws Exception {
		String resp = "0";
		try {
			Connection conn = conn();
			Statement st = conn.createStatement();
			String sql = "INSERT INTO modelo(nomb_mode, codi_marc) VALUES('"+nomb+"','"+codi_marc+"')";
			st.executeUpdate(sql);
			resp = "1";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}

	public int add() {
		return 0;
	}
	
	public String update(int codi,String nomb, String codi_marc) throws Exception {
		String resp = "0";
		try {
			Connection conn = conn();
			Statement st = conn.createStatement();
			String sql = "UPDATE modelo SET nomb_mode = '"+nomb+"', codi_marc = '"+codi_marc+"' WHERE codi_mode ='"+codi+"'";
			st.executeUpdate(sql);
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
		String sql = "DELETE FROM modelo WHERE codi_mode= " + codi;
		Connection conn = conn();
		Statement st = conn.createStatement();
		affectedRows = st.executeUpdate(sql);
		return affectedRows;
	}
}
