package renta_maestro;

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

@XmlRootElement(name = "rentaMaestroList")
@XmlSeeAlso({ Renta_Maestro.class })
public class Renta_MaestroList {
	private List<Renta_Maestro> arts;
	private Connection conn;
	String param;

	Renta_MaestroList() {
		arts = new CopyOnWriteArrayList<Renta_Maestro>();
		param = null;
	}

	Renta_MaestroList(String nomb) {
		arts = new CopyOnWriteArrayList<Renta_Maestro>();
		param = nomb;
	}

	Renta_MaestroList(boolean charge) {
		if (charge == false) {
//don't load data
		} else {
			arts = new CopyOnWriteArrayList<Renta_Maestro>();
			param = null;
		}
	}

	@XmlElement
	public List getRentaMaestro() {
		try {
			arts = getRentaMaestroList(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.arts;
	}

	public void setRentaMaestro(List<Renta_Maestro> arts) {
		this.arts = arts;
	}

	public List<Renta_Maestro> getRentaMaestroList(String param) throws Exception {
		String whereQuery = "";
		if (param != null) {
			whereQuery = " WHERE rm.codi_rent_deta = '" + param + "'";
		}
		this.conn = new Conexion().conn();
		PreparedStatement cmd = this.conn.prepareStatement(
				"SELECT rm.codi_rent_maes, rm.codi_rent_deta, ts.codi_tipo_serv, ts.nomb_serv,rm.desc_maes FROM renta_maestro as rm INNER JOIN tipo_servicio as ts ON rm.codi_tipo_serv=ts.codi_tipo_serv "
						+ whereQuery);
		ResultSet res = cmd.executeQuery();
		while (res.next()) {
			Renta_Maestro tmpRenta_Maestro = new Renta_Maestro();
			tmpRenta_Maestro.setCodi_rent_maes(res.getInt(1));
			tmpRenta_Maestro.setCodi_rent_deta(res.getInt(2));
			tmpRenta_Maestro.setCodi_tipo_serv(res.getInt(3));
			tmpRenta_Maestro.setTipo_serv(res.getString(4));
			tmpRenta_Maestro.setDesc_maes(res.getString(5));
			arts.add(tmpRenta_Maestro);
		}
		return arts;
	}

	public String add(int usua, int tipo_serv, String desc) throws Exception {
		String resp = "0";
		try {
			this.conn = new Conexion().conn();
			PreparedStatement cmd = this.conn.prepareStatement("INSERT INTO renta_maestro VALUES(NULL,(SELECT renta_detalle.codi_rent_deta FROM renta_detalle WHERE renta_detalle.codi_usua = ? AND renta_detalle.esta_rent = 0 ), ?, ?)");
			cmd.setInt(1, usua);
			cmd.setInt(2, tipo_serv);
			cmd.setString(3, desc);
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

	public String update(int codi, int tipo_serv, String desc) throws Exception {
		String resp = "0";
		try {
			this.conn = new Conexion().conn();
			PreparedStatement cmd = this.conn.prepareStatement("UPDATE renta_maestro SET tipo_serv = ?, desc_maes = ? WHERE codi_rent_maes = ?");
			cmd.setInt(1, tipo_serv);
			cmd.setString(2, desc);
			cmd.setInt(3, codi);
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

	public int delete(int codi) throws Exception {
		int affectedRows = -1;
		this.conn = new Conexion().conn();
		PreparedStatement cmd = this.conn.prepareStatement("DELETE FROM renta_maestro WHERE codi_rent_maes = ?");
		cmd.setInt(1, codi);
		affectedRows = cmd.executeUpdate();
		return affectedRows;
	}
}
