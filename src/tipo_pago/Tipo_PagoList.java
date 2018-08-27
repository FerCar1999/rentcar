package tipo_pago;

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

@XmlRootElement(name = "tipoPagoList")
@XmlSeeAlso({ Tipo_Pago.class })
public class Tipo_PagoList {
	private List<Tipo_Pago> arts;
	String url = "jdbc:mysql://localhost:3306/";
	String dbName = "rentadb";
	String driver = "com.mysql.jdbc.Driver";
	String userName = "root";
	String password = "";
	String param;

	Tipo_PagoList() {
		arts = new CopyOnWriteArrayList<Tipo_Pago>();
		param = null;
	}

	Tipo_PagoList(String nomb) {
		arts = new CopyOnWriteArrayList<Tipo_Pago>();
		param = nomb;
	}

	Tipo_PagoList(boolean charge) {
		if (charge == false) {
//don't load data
		} else {
			arts = new CopyOnWriteArrayList<Tipo_Pago>();
			param = null;
		}
	}

	@XmlElement
	public List getTipo_Pago() {
		try {
			arts = getTipo_PagoList(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.arts;
	}

	public void setTipo_Pago(List<Tipo_Pago> arts) {
		this.arts = arts;
	}

	public Connection conn()
			throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		Class.forName(driver).newInstance();
		Connection conn = DriverManager.getConnection(url + dbName, userName, password);
		return conn;
	}

	public List<Tipo_Pago> getTipo_PagoList(String param) throws Exception {
		String whereQuery = "";
		if (param != null) {
				whereQuery = " WHERE codi_tipo_pago = '" + param + "'";
		}
		Connection conn = conn();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery("SELECT * FROM tipo_pago " + whereQuery);
		while (res.next()) {
			Tipo_Pago tmpTipo_Pagos = new Tipo_Pago();
			tmpTipo_Pago.setCodi_tipo_pago(Integer.parseInt(res.getString("codi_tipo_pago")));
			tmpTipo_Pago.setNomb_tipo_pago(res.getString("tipo_pago"));
			arts.add(tmpTipo_Pago);
		}
		return arts;
	}
	public String add(String nomb) throws Exception {
		String resp = "0";
		try {
			Connection conn = conn();
			Statement st = conn.createStatement();
			String sql = "INSERT INTO tipo_pago(tipo_pago) VALUES('"+nomb+"')";
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
			String sql = "UPDATE tipo_pago SET tipo_pago = '"+nomb+"' WHERE codi_tipo_pago ='"+codi+"'";
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
		String sql = "DELETE FROM tipo_pago WHERE codi_comb= " + codi;
		Connection conn = conn();
		Statement st = conn.createStatement();
		affectedRows = st.executeUpdate(sql);
		return affectedRows;
	}
}