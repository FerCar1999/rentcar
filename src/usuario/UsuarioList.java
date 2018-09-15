package usuario;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import conexion.Conexion;
import usuario.Usuario;

@XmlRootElement(name = "usuarioList")
@XmlSeeAlso({ Usuario.class })
public class UsuarioList {
	private List<Usuario> arts;
	private Connection conn;
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

	public List<Usuario> getUsuarioList(String param) throws Exception {
		String whereQuery = "";
		if (param != null) {
			whereQuery = " AND us.codi_usua = '" + param + "'";
		}
		this.conn = new Conexion().conn();
		PreparedStatement cmd = this.conn.prepareStatement(
				"SELECT us.*, tu.tipo_usua FROM usuario as us INNER JOIN tipo_usuario as tu ON us.codi_tipo_usua = tu.codi_tipo_usua WHERE us.esta_usua=1 "
						+ whereQuery);
		ResultSet res = cmd.executeQuery();
		while (res.next()) {
			Usuario tmpUsuario = new Usuario();
			tmpUsuario.setCodi_usua(res.getInt(1));
			tmpUsuario.setNomb_usua(res.getString(2));
			tmpUsuario.setApel_usua(res.getString(3));
			tmpUsuario.setDire_usua(res.getString(4));
			tmpUsuario.setTele_usua(res.getString(5));
			tmpUsuario.setDui_usua(res.getString(6));
			tmpUsuario.setNit_usua(res.getString(7));
			tmpUsuario.setPasa_usua(res.getString(8));
			tmpUsuario.setCorr_usua(res.getString(9));
			tmpUsuario.setCont_usua(null);
			tmpUsuario.setCodi_tipo(res.getInt(11));
			tmpUsuario.setLice_usua(res.getString(12));
			tmpUsuario.setEsta_usua(res.getInt(13));
			tmpUsuario.setNomb_tipo(res.getString(14));
			arts.add(tmpUsuario);
		}
		return arts;
	}

	public String add(String nomb_usua, String apel_usua, String dire_usua, String tele_usua, String dui_usua,
			String nit_usua, String pasa_usua, String lice_usua, String corr_usua, int codi_tipo, String cont_usua)
			throws Exception {
		String resp = "0";
		try {
			this.conn = new Conexion().conn();
			String pass_usua = encriptar(cont_usua);
			PreparedStatement cmd = this.conn
					.prepareStatement("INSERT INTO usuario VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			cmd.setString(1, nomb_usua);
			cmd.setString(2, apel_usua);
			cmd.setString(3, dire_usua);
			cmd.setString(4, tele_usua);
			cmd.setString(5, dui_usua);
			cmd.setString(6, nit_usua);
			cmd.setString(7, pasa_usua);
			cmd.setString(8, corr_usua);
			cmd.setString(9, pass_usua);
			cmd.setInt(10, codi_tipo);
			cmd.setString(11, lice_usua);
			cmd.setInt(12, 1);
			cmd.executeUpdate();
			resp = "1";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}

	public int add() {
		return 0;
	}

	public String update(int codi_usua, String nomb_usua, String apel_usua, String dire_usua, String tele_usua,
			String dui_usua, String nit_usua, String pasa_usua, String corr_usua, int codi_tipo, String lice_usua)
			throws Exception {
		String resp = "0";
		try {
			this.conn = new Conexion().conn();
			PreparedStatement cmd = this.conn.prepareStatement(
					"UPDATE usuario SET nomb_usua=?, apel_usua=?, dire_usua=?, tele_usua=?, dui_usua=?, nit_usua=?, pasa_usua=?, corr_usua=?, codi_tipo_usua=?, lice_usua=? WHERE codi_usua=?");
			cmd.setString(1, nomb_usua);
			cmd.setString(2, apel_usua);
			cmd.setString(3, dire_usua);
			cmd.setString(4, tele_usua);
			cmd.setString(5, dui_usua);
			cmd.setString(6, nit_usua);
			cmd.setString(7, pasa_usua);
			cmd.setString(8, corr_usua);
			cmd.setInt(9, codi_tipo);
			cmd.setString(10, lice_usua);
			cmd.setInt(11, codi_usua);
			cmd.executeUpdate();
			resp = "1";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}

	public int update() {
		return 0;
	}

	public boolean verificarPassword(int codi, String vieja)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		boolean resp = false;
		this.conn = new Conexion().conn();
		try {
			PreparedStatement cmd = this.conn.prepareStatement("SELECT cont_usua FROM usuario WHERE codi_usua=?");
			cmd.setInt(1, codi);
			ResultSet res = cmd.executeQuery();
			while (res.next()) {
				if (res.getString(1).equals(vieja)) {
					resp = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}

	public String updatePassword(int codi_usua, String vieja, String nueva)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		String resp = "0";
		this.conn = new Conexion().conn();
		try {
			String pass_viej = encriptar(vieja);
			if (verificarPassword(codi_usua, pass_viej)) {
				String pass_usua = encriptar(nueva);
				PreparedStatement cmd = this.conn.prepareStatement("UPDATE usuario SET cont_usua=? WHERE codi_usua=?");
				cmd.setString(1, pass_usua);
				cmd.setInt(2, codi_usua);
				cmd.executeUpdate();
				resp = "1";
			} else {
				resp = "0";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}

	public int delete(int codi_usua) throws Exception {
		int affectedRows = -1;
		this.conn = new Conexion().conn();
		PreparedStatement cmd = this.conn.prepareStatement("UPDATE usuario SET esta_usua=0 WHERE codi_usua=?");
		cmd.setInt(1, codi_usua);
		affectedRows = cmd.executeUpdate();
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
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
