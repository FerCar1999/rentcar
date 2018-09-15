package tipo_pago;

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

@XmlRootElement(name = "tipoPagoList")
@XmlSeeAlso({ Tipo_Pago.class })
public class Tipo_PagoList {
	private List<Tipo_Pago> arts;
	private Connection conn;
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
	public List getTipoPago() {
		try {
			arts = getTipoPagoList(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.arts;
	}

	public void setTipoPago(List<Tipo_Pago> arts) {
		this.arts = arts;
	}

	public List<Tipo_Pago> getTipoPagoList(String param) throws Exception {
		String whereQuery = "";
		if (param != null) {
				whereQuery = " AND codi_tipo_pago = '" + param + "'";
		}
		this.conn = new Conexion().conn();
		PreparedStatement cmd = this.conn.prepareStatement("SELECT * FROM tipo_pago WHERE esta_tipo_pago = 1"+whereQuery);
		ResultSet res =cmd.executeQuery();
		while (res.next()) {
			Tipo_Pago tmpTipo_Pago = new Tipo_Pago();
			tmpTipo_Pago.setCodi_tipo_pago(res.getInt(1));
			tmpTipo_Pago.setTipo_pago(res.getString(2));
			tmpTipo_Pago.setEsta_tipo_pago(res.getInt(3));
			arts.add(tmpTipo_Pago);
		}
		return arts;
	}
	public String add(String nomb) throws Exception {
		String resp = "0";
		try {
			this.conn = new Conexion().conn();
			PreparedStatement cmd = this.conn.prepareStatement("INSERT INTO tipo_pago VALUES(NULL, ?, 1)");
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
	
	public String update(int codi,String nomb) throws Exception {
		String resp = "0";
		try {
			this.conn = new Conexion().conn();
			PreparedStatement cmd = this.conn.prepareStatement("UPDATE tipo_pago SET tipo_pago = ? WHERE codi_tipo_pago = ?");
			cmd.setString(1, nomb);
			cmd.setInt(2, codi);
			cmd.executeUpdate();
			resp= "1";
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
			PreparedStatement cmd = this.conn.prepareStatement("UPDATE tipo_pago SET esta_tipo_pago= 0 WHERE codi_tipo_pago= ?");
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