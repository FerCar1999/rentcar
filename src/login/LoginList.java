package login;

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

import com.mysql.jdbc.PreparedStatement;


@XmlRootElement(name = "loginList")
@XmlSeeAlso({ Login.class })
public class LoginList {
	private List<Login> arts;
	String url = "jdbc:mysql://rentadb.c9bf3mte5srb.us-east-2.rds.amazonaws.com:3306/";
	String dbName = "rentadb";
	String driver = "com.mysql.jdbc.Driver";
	String userName = "adminrenta";
	String password = "fslrenta";
	String param;

	LoginList() {
		arts = new CopyOnWriteArrayList<Login>();
		param = null;
	}

	LoginList(String nomb) {
		arts = new CopyOnWriteArrayList<Login>();
		param = nomb;
	}

	LoginList(boolean charge) {
		if (charge == false) {
//don't load data
		} else {
			arts = new CopyOnWriteArrayList<Login>();
			param = null;
		}
	}

	@XmlElement
	public List getUsuario() {
		try {
			arts = getLoginList(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.arts;
	}

	public void setUsuario(List<Login> arts) {
		this.arts = arts;
	}

	public Connection conn()
			throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		Class.forName(driver).newInstance();
		Connection conn = DriverManager.getConnection(url + dbName, userName, password);
		return conn;
	}

	public List<Login> getLoginList(String param) throws Exception {
		String whereQuery = "";
		if (param != null) {
				whereQuery = " WHERE us.corr_usua = '" + param + "'";
		}
		Connection conn = conn();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery("SELECT us.*, tu.tipo_usua FROM usuario as us INNER JOIN tipo_usuario as tu ON us.codi_tipo = tu.codi_tipo " + whereQuery);
		while (res.next()) {
			Login tmpUsuario = new Login();
			tmpUsuario.setCodi_usua(Integer.parseInt(res.getString(1)));
			tmpUsuario.setNomb_usua(res.getString(2));
			tmpUsuario.setApel_usua(res.getString(3));
			tmpUsuario.setDire_usua(res.getString(4));
			tmpUsuario.setTele_usua(res.getString(5));
			tmpUsuario.setDui_usua(res.getString(6));
			tmpUsuario.setNit_usua(res.getString(7));
			tmpUsuario.setPasa_usua(res.getString(8));
			tmpUsuario.setCorr_usua(res.getString(9));
			tmpUsuario.setCont_usua(res.getString(10));
			tmpUsuario.setCodi_tipo(Integer.parseInt(res.getString(11)));
			tmpUsuario.setNomb_tipo(res.getString(12));
			arts.add(tmpUsuario);
		}
		return arts;
	}
}
