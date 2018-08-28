package usuario;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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


import usuario.Usuario;

@XmlRootElement(name = "usuarioList")
@XmlSeeAlso({ Usuario.class })
public class UsuarioList {
	private List<Usuario> arts;
	String url = "jdbc:mysql://localhost:3306/";
	String dbName = "rentadb";
	String driver = "com.mysql.jdbc.Driver";
	String userName = "root";
	String password = "";
	String param;

	UsuarioList() {
		arts = new CopyOnWriteArrayList<Usuario>();
		param = null;
	}

	UsuarioList(String nomb) {
		arts = new CopyOnWriteArrayList<Usuario>();
		param = nomb;
	}

	UsuarioList(boolean charge) {
		if (charge == false) {
//don't load data
		} else {
			arts = new CopyOnWriteArrayList<Usuario>();
			param = null;
		}
	}

	@XmlElement
	public List getUsuario() {
		try {
			arts = getUsuarioList(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.arts;
	}

	public void setUsuario(List<Usuario> arts) {
		this.arts = arts;
	}

	public Connection conn()
			throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		Class.forName(driver).newInstance();
		Connection conn = DriverManager.getConnection(url + dbName, userName, password);
		return conn;
	}

	public List<Usuario> getUsuarioList(String param) throws Exception {
		String whereQuery = "";
		if (param != null) {
				whereQuery = " WHERE codi_usua = '" + param + "'";
		}
		Connection conn = conn();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery("select usuario.codi_usua,usuario.nomb_usua,usuario.apel_usua,usuario.dire_usua,usuario.tele_usua, usuario.dui_usua,\n" + 
				"usuario.nit_usua,usuario.pasa_usua,usuario.corr_usua,usuario.codi_tipo\n" + 
				"from usuario  " + whereQuery);
		while (res.next()) {
			Usuario tmpUsuario = new Usuario();
			tmpUsuario.setCodi_usua(Integer.parseInt(res.getString("codi_usua")));
			tmpUsuario.setCont_usua(null);
			tmpUsuario.setApel_usua(res.getString("apel_usua"));
			tmpUsuario.setNomb_usua(res.getString("nomb_usua"));
			tmpUsuario.setNit_usua(res.getString("nit_usua"));
			tmpUsuario.setDui_usua(res.getString("dui_usua"));
			tmpUsuario.setDire_usua(res.getString("dire_usua"));
			tmpUsuario.setCodi_tipo(Integer.parseInt(res.getString("codi_tipo")));
			tmpUsuario.setCorr_usua(res.getString("corr_usua"));
			tmpUsuario.setTele_usua(res.getString("tele_usua"));
			tmpUsuario.setPasa_usua(res.getString("pasa_usua"));
			arts.add(tmpUsuario);
		}
		return arts;
	}
	
	public String add(String nomb_usua,String apel_usua,String dire_usua,String tele_usua,String dui_usua,String nit_usua, String pasa_usua, String corr_usua, int codi_tipo,String cont_usua) throws Exception {
		String resp = "0";
		try {
			Connection conn = conn();
			Statement st = conn.createStatement();
			String pass_usua=encriptar(cont_usua);
			String sql = "INSERT INTO rentadb.usuario\n" + 
					"(nomb_usua, apel_usua, dire_usua, tele_usua, dui_usua, nit_usua, pasa_usua, corr_usua, cont_usua, codi_tipo)\n" + 
					"VALUES('"+nomb_usua+"', '"+apel_usua+"', '"+dire_usua+"', '"+tele_usua+"', '"+dui_usua+"', "
							+ "'"+nit_usua+"', '"+pasa_usua+"', '"+corr_usua+"', '"+pass_usua+"', "+codi_tipo+");";
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
	
	public String update(int codi_usua,String nomb_usua,String apel_usua,String dire_usua,String tele_usua,String dui_usua,String nit_usua, String pasa_usua, String corr_usua, int codi_tipo,String cont_usua) throws Exception {
		String resp = "0";
		try {
			Connection conn = conn();
			Statement st = conn.createStatement();
			String sql = "UPDATE rentadb.usuario\n" + 
					"SET nomb_usua='"+nomb_usua+"', apel_usua='"+apel_usua+"', dire_usua='"+dire_usua+"', dui_usua='"+tele_usua+"', nit_usua='"+nit_usua+"', pasa_usua='"+pasa_usua+"', corr_usua='"+corr_usua+"', cont_usua='"+encriptar(cont_usua)+"', codi_tipo='"+codi_tipo+"',tele_usua='"+tele_usua+"'\n" + 
					"WHERE codi_usua='"+codi_usua+"';\n" + 
					"";
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

	public int delete(int codi_usua) throws Exception {
		int affectedRows = -1;
		String sql = "DELETE FROM usuario WHERE codi_usua= " + codi_usua;
		Connection conn = conn();
		Statement st = conn.createStatement();
		affectedRows = st.executeUpdate(sql);
		return affectedRows;
	}
	public static String encriptar(String input) {
		 try {
			 MessageDigest md = MessageDigest.getInstance("MD5");
			 byte[] messageDigest = md.digest(input.getBytes());
			 BigInteger number = new BigInteger(1, messageDigest);
			 String hashtext = number.toString(16);
	
			 while (hashtext.length() < 32) {
				 hashtext = "0" + hashtext;
			 }
		 return hashtext;
		 }
		 catch (NoSuchAlgorithmException e) {
			 throw new RuntimeException(e);
		 }
	}
}
