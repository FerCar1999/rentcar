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
				whereQuery = " WHERE renta_detalle.codi_rent_deta = '" + param + "'";
		}
		Connection conn = conn();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery("SELECT renta_detalle.codi_rent_deta, usuario.codi_usua, usuario.nomb_usua, usuario.apel_usua, vehiculo.codi_vehi, modelo.nomb_mode, marca.nomb_marc, renta_detalle.fech_rent, renta_detalle.fech_devo, renta_detalle.tota_deta, tipo_pago.codi_tipo_pago, tipo_pago.tipo_pago, renta_detalle.esta_rent  FROM (((((renta_detalle INNER JOIN usuario ON renta_detalle.codi_usua = usuario.codi_usua)INNER JOIN vehiculo ON renta_detalle.codi_vehi = vehiculo.codi_vehi)INNER JOIN modelo ON vehiculo.codi_mode = modelo.codi_mode) INNER JOIN marca ON modelo.codi_marc= marca.codi_marc)INNER JOIN tipo_pago ON renta_detalle.tipo_pago=tipo_pago.codi_tipo_pago) " + whereQuery);
		while (res.next()) {
			Renta_Detalle tmpRenta_Detalle = new Renta_Detalle();
			tmpRenta_Detalle.setCodi_rent_deta(Integer.parseInt(res.getString(1)));
			tmpRenta_Detalle.setCodi_usua(Integer.parseInt(res.getString(2)));
			tmpRenta_Detalle.setNomb_usua(res.getString(3));
			tmpRenta_Detalle.setApel_usua(res.getString(4));
			tmpRenta_Detalle.setCodi_vehi(Integer.parseInt(res.getString(5)));
			tmpRenta_Detalle.setMode_vehi(res.getString(6));
			tmpRenta_Detalle.setMarc_vehi(res.getString(7));
			tmpRenta_Detalle.setFech_rent(res.getString(8));
			tmpRenta_Detalle.setFech_devo(res.getString(9));
			tmpRenta_Detalle.setTota_deta(Double.parseDouble(res.getString(10)));
			tmpRenta_Detalle.setTipo_pago(Integer.parseInt(res.getString(11)));
			tmpRenta_Detalle.setNomb_pago(res.getString(12));
			tmpRenta_Detalle.setEsta_rent(Integer.parseInt(res.getString(13)));
			arts.add(tmpRenta_Detalle);
		}
		return arts;
	}
	public String add(int codi_usua, int codi_vehi, String fech_rent, String fech_devo, int tipo_pago) throws Exception {
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

	public String updateEstado(int codi,int esta) throws Exception {
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
