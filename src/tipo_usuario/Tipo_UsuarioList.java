package tipo_usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import conexion.Conexion;

@XmlRootElement(name = "tipoUsuarioList")
@XmlSeeAlso({ Tipo_Usuario.class })
public class Tipo_UsuarioList {
	private List<Tipo_Usuario> arts;
	private Connection conn;
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
	public List getTipoUsuario() {
		try {
			arts = getTipoUsuarioList(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.arts;
	}

	public void setTipoUsuario(List<Tipo_Usuario> arts) {
		this.arts = arts;
	}

	public List<Tipo_Usuario> getTipoUsuarioList(String param) throws Exception {
		String whereQuery = "";
		if (param != null) {
			whereQuery = " AND codi_tipo = '" + param + "'";
		}
		this.conn = new Conexion().conn();
		PreparedStatement cmd = this.conn
				.prepareStatement("SELECT * FROM tipo_usuario WHERE esta_tipo_usua = 1 " + whereQuery);
		ResultSet res = cmd.executeQuery();
		while (res.next()) {
			Tipo_Usuario tmpTipo_Usuario = new Tipo_Usuario();
			tmpTipo_Usuario.setCodi_tipo(res.getInt(1));
			tmpTipo_Usuario.setTipo_usua(res.getString(2));
			tmpTipo_Usuario.setEsta_tipo_usua(res.getInt(3));
			arts.add(tmpTipo_Usuario);
		}
		return arts;
	}

	public String add(String nomb) throws Exception {
		String resp = "0";
		try {
			this.conn = new Conexion().conn();
			PreparedStatement cmd = this.conn.prepareStatement("INSERT INTO tipo_usuario VALUES(NULL, ? 1)");
			cmd.setString(1, nomb);
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

	public String update(int codi, String nomb) throws Exception {
		String resp = "0";
		try {
			this.conn = new Conexion().conn();
			PreparedStatement cmd = this.conn.prepareStatement("UPDATE tipo_usuario SET tipo_usua = ? WHERE codi_tipo_usua=?");
			cmd.setString(1, nomb);
			cmd.setInt(2, codi);
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

	public boolean delete(int codi) throws Exception {
		boolean resp = false;
		try {
			this.conn = new Conexion().conn() ;
			PreparedStatement cmd = this.conn.prepareStatement("UPDATE tipo_usuario SET esta_tipo_usua = 0 WHERE codi_tipo_usua = ?");
			cmd.setInt(1, codi);
			cmd.executeUpdate();
			resp = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
	public int delete() {
		return 0;
	}
}