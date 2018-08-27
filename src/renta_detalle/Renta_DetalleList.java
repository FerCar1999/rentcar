package renta_detalle;

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

@XmlRootElement(name = "rentaDetalleList")
@XmlSeeAlso({ Renta_Detalle.class })
public class Renta_DetalleList {
	private List<Renta_Detalle> arts;
	String url = "jdbc:mysql://localhost:3306/";
	String dbName = "rentadb";
	String driver = "com.mysql.jdbc.Driver";
	String userName = "root";
	String password = "";
	String param;

	Renta_DetalleList() {
		arts = new CopyOnWriteArrayList<Renta_Detalle>();
		param = null;
	}

	Renta_DetalleList(String nomb) {
		arts = new CopyOnWriteArrayList<Renta_Detalle>();
		param = nomb;
	}

	Renta_DetalleList(boolean charge) {
		if (charge == false) {
//don't load data
		} else {
			arts = new CopyOnWriteArrayList<Renta_Detalle>();
			param = null;
		}
	}

	@XmlElement
	public List getRenta_Detalle() {
		try {
			arts = getRenta_DetalleList(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.arts;
	}

	public void setRenta_Detalle(List<Renta_Detalle> arts) {
		this.arts = arts;
	}

	public Connection conn()
			throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		Class.forName(driver).newInstance();
		Connection conn = DriverManager.getConnection(url + dbName, userName, password);
		return conn;
	}

	public List<Renta_Detalle> getRenta_DetalleList(String param) throws Exception {
		String whereQuery = "";
		if (param != null) {
				whereQuery = " WHERE codi_rent_deta = '" + param + "'";
		}
		Connection conn = conn();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery("SELECT * FROM renta_detalle " + whereQuery);
		while (res.next()) {
			Renta_Detalle tmpRenta_Detalle = new Renta_Detalle();
			tmpRenta_Detalle.setCodi_rent_deta(Integer.parseInt(res.getString("codi_rent_deta")));
			tmpRenta_Detalle.setCodi_usua(Integer.parseInt(res.getString("codi_usua")));
			tmpRenta_Detalle.setCodi_vehi(Integer.parseInt(res.getString("codi_vehi")));
			tmpRenta_Detalle.setFech_rent(res.getString("fech_rent"));
			tmpRenta_Detalle.setFech_devo(res.getString("fech_devo"));
			tmpRenta_Detalle.setTota_deta(Double.parseDouble(res.getString("tota_deta")));
			tmpRenta_Detalle.setTipo_pago(Integer.parseInt(res.getString("tipo_pago")));
			tmpRenta_Detalle.setEsta_rent(Integer.parseInt(res.getString("esta_rent")));
			arts.add(tmpRenta_Detalle);
		}
		return arts;
	}
	public String add(String codi_usua, String codi_vehi, String fech_rent, String fech_devo, String tipo_pago) throws Exception {
		String resp = "0";
		try {
			Connection conn = conn();
			Statement st = conn.createStatement();
			String sql = "INSERT INTO renta_detalle(codi_usua, codi_vehi, fech_rent, fech_devo, tota_deta, tipo_pago, esta_rent) VALUES('"+codi_usua+"','"+codi_vehi+"','"+fech_rent+"','"+fech_devo+"',SELECT prec_vehi FROM vehiculo WHERE vehiculo.codi_vehi = '"+codi_vehi+"','"+tipo_pago+"',0)";
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
	
	public String updateTotal(int codi,double total) throws Exception {
		String resp = "0";
		try {
			Connection conn = conn();
			Statement st = conn.createStatement();
			String sql = "UPDATE renta_detalle SET tota_deta = '"+total+"' WHERE codi_rent_deta ='"+codi+"'";
			st.executeUpdate(sql);
			resp= "1";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
	public int updateTotal() {
		return 0;
	}

	public String updateEstado(int codi,String esta) throws Exception {
		String resp = "0";
		try {
			Connection conn = conn();
			Statement st = conn.createStatement();
			String sql = "UPDATE renta_detalle SET esta_rent = '"+esta+"' WHERE codi_rent_deta ='"+codi+"'";
			st.executeUpdate(sql);
			resp= "1";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
	public int updateEstado() {
		return 0;
	}

	public int delete(int codi) throws Exception {
		int affectedRows = -1;
		String sql = "DELETE FROM renta_detalle WHERE codi_rent_deta= " + codi;
		Connection conn = conn();
		Statement st = conn.createStatement();
		affectedRows = st.executeUpdate(sql);
		return affectedRows;
	}
}
