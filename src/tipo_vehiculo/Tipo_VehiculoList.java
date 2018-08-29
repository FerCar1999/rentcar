package tipo_vehiculo;
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

@XmlRootElement(name = "tipoVehiculoList")
@XmlSeeAlso({ Tipo_Vehiculo.class })
public class Tipo_VehiculoList {
	private List<Tipo_Vehiculo> arts;
	String url = "jdbc:mysql://rentadb.c9bf3mte5srb.us-east-2.rds.amazonaws.com:3306/";
	String dbName = "rentadb";
	String driver = "com.mysql.jdbc.Driver";
	String userName = "adminrenta";
	String password = "fslrenta";
	String param;

	Tipo_VehiculoList() {
		arts = new CopyOnWriteArrayList<Tipo_Vehiculo>();
		param = null;
	}

	Tipo_VehiculoList(String nomb) {
		arts = new CopyOnWriteArrayList<Tipo_Vehiculo>();
		param = nomb;
	}

	Tipo_VehiculoList(boolean charge) {
		if (charge == false) {
//don't load data
		} else {
			arts = new CopyOnWriteArrayList<Tipo_Vehiculo>();
			param = null;
		}
	}

	@XmlElement
	public List getTipo_Vehiculo() {
		try {
			arts = getTipo_VehiculoList(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.arts;
	}

	public void setTipo_Vehiculo(List<Tipo_Vehiculo> arts) {
		this.arts = arts;
	}

	public Connection conn()
			throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		Class.forName(driver).newInstance();
		Connection conn = DriverManager.getConnection(url + dbName, userName, password);
		return conn;
	}

	public List<Tipo_Vehiculo> getTipo_VehiculoList(String param) throws Exception {
		String whereQuery = "";
		if (param != null) {
				whereQuery = " WHERE codi_tipo = '" + param + "'";
		}
		Connection conn = conn();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery("SELECT * FROM tipo_vehiculo " + whereQuery);
		while (res.next()) {
			Tipo_Vehiculo tmpTipo_Vehiculo = new Tipo_Vehiculo();
			tmpTipo_Vehiculo.setCodi_tipo(Integer.parseInt(res.getString("codi_tipo")));
			tmpTipo_Vehiculo.setTipo_vehi(res.getString("tipo_vehi"));
			arts.add(tmpTipo_Vehiculo);
		}
		return arts;
	}
	public String add(String nomb) throws Exception {
		String resp = "0";
		try {
			Connection conn = conn();
			Statement st = conn.createStatement();
			String sql = "INSERT INTO tipo_vehiculo(tipo_vehi) VALUES('"+nomb+"')";
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
			String sql = "UPDATE tipo_vehiculo SET tipo_vehi = '"+nomb+"' WHERE codi_tipo ='"+codi+"'";
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
		String sql = "DELETE FROM tipo_vehi WHERE codi_tipo= " + codi;
		Connection conn = conn();
		Statement st = conn.createStatement();
		affectedRows = st.executeUpdate(sql);
		return affectedRows;
	}
}