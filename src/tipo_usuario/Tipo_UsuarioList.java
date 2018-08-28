package tipo_usuario;

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

@XmlRootElement(name = "tipoUsuarioList")
@XmlSeeAlso({ Tipo_Usuario.class })
public class Tipo_UsuarioList {
	private List<Tipo_Usuario> arts;
	String url = "jdbc:mysql://localhost:3306/";
	String dbName = "rentadb";
	String driver = "com.mysql.jdbc.Driver";
	String userName = "root";
	String password = "6178";
	String param;

	Tipo_UsuarioList() {
		arts = new CopyOnWriteArrayList<Tipo_Usuario>();
		param = null;
	}

	Tipo_UsuarioList(String nomb) {
		arts = new CopyOnWriteArrayList<Tipo_Usuario>();
		param = nomb;
	}

	Tipo_UsuarioList(boolean charge) {
		if (charge == false) {
//don't load data
		} else {
			arts = new CopyOnWriteArrayList<Tipo_Usuario>();
			param = null;
		}
	}

	@XmlElement
	public List getTipo_Usuario() {
		try {
			arts = getTipo_UsuarioList(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.arts;
	}

	public void setTipo_Usuario(List<Tipo_Usuario> arts) {
		this.arts = arts;
	}

	public Connection conn()
			throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		Class.forName(driver).newInstance();
		Connection conn = DriverManager.getConnection(url + dbName, userName, password);
		return conn;
	}

	public List<Tipo_Usuario> getTipo_UsuarioList(String param) throws Exception {
		String whereQuery = "";
		if (param != null) {
				whereQuery = " WHERE codi_tipo = '" + param + "'";
		}
		Connection conn = conn();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery("SELECT * FROM tipo_usuario " + whereQuery);
		while (res.next()) {
			Tipo_Usuario tmpTipo_Usuario = new Tipo_Usuario();
			tmpTipo_Usuario.setCodi_tipo(Integer.parseInt(res.getString("codi_tipo")));
			tmpTipo_Usuario.setTipo_usua(res.getString("tipo_usua"));
			arts.add(tmpTipo_Usuario);
		}
		return arts;
	}
	public String add(String nomb) throws Exception {
		String resp = "0";
		try {
			Connection conn = conn();
			Statement st = conn.createStatement();
			String sql = "INSERT INTO tipo_usuario(tipo_usua) VALUES('"+nomb+"')";
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
			String sql = "UPDATE tipo_usuario SET tipo_usuario = '"+nomb+"' WHERE codi_tipo ='"+codi+"'";
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
		String sql = "DELETE FROM tipo_usuario WHERE codi_tipo= " + codi;
		Connection conn = conn();
		Statement st = conn.createStatement();
		affectedRows = st.executeUpdate(sql);
		return affectedRows;
	}
}