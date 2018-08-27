package transmision;

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

@XmlRootElement(name = "transmisionList")
@XmlSeeAlso({ Transmision.class })
public class TransmisionList {
	private List<Transmision> arts;
	String url = "jdbc:mysql://localhost:3306/";
	String dbName = "rentadb";
	String driver = "com.mysql.jdbc.Driver";
	String userName = "root";
	String password = "";
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

	public Connection conn()
			throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		Class.forName(driver).newInstance();
		Connection conn = DriverManager.getConnection(url + dbName, userName, password);
		return conn;
	}

	public List<Transmision> getTransmisionList(String param) throws Exception {
		String whereQuery = "";
		if (param != null) {
				whereQuery = " WHERE codi_tran = '" + param + "'";
		}
		Connection conn = conn();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery("SELECT * FROM transmision " + whereQuery);
		while (res.next()) {
			Transmision tmpTransmision = new Transmision();
			tmpTransmision.setCodi_tran(Integer.parseInt(res.getString("codi_tran")));
			tmpTransmision.setNomb_tran(res.getString("nomb_tran"));
			arts.add(tmpTransmision);
		}
		return arts;
	}
	public String add(String nomb) throws Exception {
		String resp = "0";
		try {
			Connection conn = conn();
			Statement st = conn.createStatement();
			String sql = "INSERT INTO transmision(nomb_tran) VALUES('"+nomb+"')";
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
			String sql = "UPDATE transmision SET nomb_tran = '"+nomb+"' WHERE codi_tran ='"+codi+"'";
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
		String sql = "DELETE FROM transmision WHERE codi_tran= " + codi;
		Connection conn = conn();
		Statement st = conn.createStatement();
		affectedRows = st.executeUpdate(sql);
		return affectedRows;
	}
}