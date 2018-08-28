package renta_maestro;

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

@XmlRootElement(name = "rentaMaestroList")
@XmlSeeAlso({ Renta_Maestro.class })
public class Renta_MaestroList {
	private List<Renta_Maestro> arts;
	String url = "jdbc:mysql://localhost:3306/";
	String dbName = "rentadb";
	String driver = "com.mysql.jdbc.Driver";
	String userName = "root";
	String password = "6178";
	String param;

	Renta_MaestroList() {
		arts = new CopyOnWriteArrayList<Renta_Maestro>();
		param = null;
	}

	Renta_MaestroList(String nomb) {
		arts = new CopyOnWriteArrayList<Renta_Maestro>();
		param = nomb;
	}

	Renta_MaestroList(boolean charge) {
		if (charge == false) {
//don't load data
		} else {
			arts = new CopyOnWriteArrayList<Renta_Maestro>();
			param = null;
		}
	}

	@XmlElement
	public List getRenta_Maestro() {
		try {
			arts = getRenta_MaestroList(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.arts;
	}

	public void setRenta_Maestro(List<Renta_Maestro> arts) {
		this.arts = arts;
	}

	public Connection conn()
			throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		Class.forName(driver).newInstance();
		Connection conn = DriverManager.getConnection(url + dbName, userName, password);
		return conn;
	}

	public List<Renta_Maestro> getRenta_MaestroList(String param) throws Exception {
		String whereQuery = "";
		if (param != null) {
				whereQuery = " WHERE codi_rent_maes = '" + param + "'";
		}
		Connection conn = conn();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery("SELECT * FROM renta_maestro " + whereQuery);
		while (res.next()) {
			Renta_Maestro tmpRenta_Maestro = new Renta_Maestro();
			tmpRenta_Maestro.setCodi_rent_maes(Integer.parseInt(res.getString("codi_rent_maes")));
			tmpRenta_Maestro.setCodi_rent_deta(Integer.parseInt(res.getString("codi_rent_deta")));
			tmpRenta_Maestro.setCodi_tipo_serv(Integer.parseInt(res.getString("codi_tipo_serv")));
			tmpRenta_Maestro.setDesc_maes(res.getString("desc_maes"));
			arts.add(tmpRenta_Maestro);
		}
		return arts;
	}
	public String add(int usua, int tipo_serv, String desc) throws Exception {
		String resp = "0";
		try {
			Connection conn = conn();
			Statement st = conn.createStatement();
			String sql = "INSERT INTO renta_maestro(codi_rent_deta,codi_tipo_serv,desc_maes) VALUES((SELECT renta_detalle.codi_rent_deta FROM renta_detalle WHERE renta_detalle.codi_usua = '"+usua+"' AND renta_detalle.esta_rent = 0),'"+tipo_serv+"','"+desc+"')";
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
	
	public String update(int codi,int tipo_serv, String desc) throws Exception {
		String resp = "0";
		try {
			Connection conn = conn();
			Statement st = conn.createStatement();
			String sql = "UPDATE renta_maestro SET tipo_serv = '"+tipo_serv+"', desc_maes = '"+desc+"' WHERE codi_rent_maes ='"+codi+"'";
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
		String sql = "DELETE FROM renta_maestro WHERE codi_rent_maes= " + codi;
		Connection conn = conn();
		Statement st = conn.createStatement();
		affectedRows = st.executeUpdate(sql);
		return affectedRows;
	}
}
