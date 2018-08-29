package tipo_servicio;

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

@XmlRootElement(name = "tipoServicioList")
@XmlSeeAlso({ Tipo_Servicio.class })
public class Tipo_ServicioList {
	private List<Tipo_Servicio> arts;
	String url = "jdbc:mysql://rentadb.c9bf3mte5srb.us-east-2.rds.amazonaws.com:3306/";
	String dbName = "rentadb";
	String driver = "com.mysql.jdbc.Driver";
	String userName = "adminrenta";
	String password = "fslrenta";
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
	public List getTipo_Servicio() {
		try {
			arts = getTipo_ServicioList(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.arts;
	}

	public void setTipo_Servicio(List<Tipo_Servicio> arts) {
		this.arts = arts;
	}

	public Connection conn()
			throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		Class.forName(driver).newInstance();
		Connection conn = DriverManager.getConnection(url + dbName, userName, password);
		return conn;
	}

	public List<Tipo_Servicio> getTipo_ServicioList(String param) throws Exception {
		String whereQuery = "";
		if (param != null) {
				whereQuery = " WHERE codi_tipo_serv = '" + param + "'";
		}
		Connection conn = conn();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery("SELECT * FROM tipo_servicio " + whereQuery);
		while (res.next()) {
			Tipo_Servicio tmpTipo_Servicio = new Tipo_Servicio();
			tmpTipo_Servicio.setCodi_tipo_serv(Integer.parseInt(res.getString("codi_tipo_serv")));
			tmpTipo_Servicio.setNomb_serv(res.getString("nomb_serv"));
			tmpTipo_Servicio.setPrec_serv(Double.parseDouble(res.getString("prec_serv")));
			arts.add(tmpTipo_Servicio);
		}
		return arts;
	}
	public String add(String nomb, double prec) throws Exception {
		String resp = "0";
		try {
			Connection conn = conn();
			Statement st = conn.createStatement();
			String sql = "INSERT INTO tipo_servicio(nomb_serv, prec_serv) VALUES('"+nomb+"', '"+prec+"')";
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
	
	public String update(int codi,String nomb, double prec) throws Exception {
		String resp = "0";
		try {
			Connection conn = conn();
			Statement st = conn.createStatement();
			String sql = "UPDATE tipo_servicio SET nomb_serv = '"+nomb+"', prec_serv = '"+prec+"' WHERE codi_tipo_serv ='"+codi+"'";
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
		String sql = "DELETE FROM tipo_servicio WHERE codi_tipo_serv= " + codi;
		Connection conn = conn();
		Statement st = conn.createStatement();
		affectedRows = st.executeUpdate(sql);
		return affectedRows;
	}
}