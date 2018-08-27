package marca;

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

@XmlRootElement(name = "marcaList")
@XmlSeeAlso({ Marca.class })
public class MarcaList {
	private List<Marca> arts;
	String url = "jdbc:mysql://localhost:3306/";
	String dbName = "rentadb";
	String driver = "com.mysql.jdbc.Driver";
	String userName = "root";
	String password = "";
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

	public Connection conn()
			throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		Class.forName(driver).newInstance();
		Connection conn = DriverManager.getConnection(url + dbName, userName, password);
		return conn;
	}

	public List<Marca> getMarcaList(String param) throws Exception {
		String whereQuery = "";
		if (param != null) {
				whereQuery = " WHERE codi_marc = '" + param + "'";
		}
		Connection conn = conn();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery("SELECT * FROM marca " + whereQuery);
		while (res.next()) {
			Marca tmpMarca = new Marca();
			tmpMarca.setCodi_marc(Integer.parseInt(res.getString("codi_marc")));
			tmpMarca.setNomb_marc(res.getString("nomb_marc"));
			arts.add(tmpMarca);
		}
		return arts;
	}
	public String add(String nomb) throws Exception {
		String resp = "0";
		try {
			Connection conn = conn();
			Statement st = conn.createStatement();
			String sql = "INSERT INTO marca(nomb_marc) VALUES('"+nomb+"')";
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
	
	public String update(int codi,String nomb) throws Exception {
		String resp = "0";
		try {
			Connection conn = conn();
			Statement st = conn.createStatement();
			String sql = "UPDATE marca SET nomb_marc = '"+nomb+"' WHERE codi_marc ='"+codi+"'";
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
		String sql = "DELETE FROM marca WHERE codi_marc= " + codi;
		Connection conn = conn();
		Statement st = conn.createStatement();
		affectedRows = st.executeUpdate(sql);
		return affectedRows;
	}
}
