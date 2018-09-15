package vehiculo;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import transmision.Transmision;
import usuario.Usuario;
import vehiculo.Vehiculo;

@XmlRootElement(name = "vehiculoList")
@XmlSeeAlso({ Vehiculo.class })
public class VehiculoList {
	private List<Vehiculo> arts;
	private Connection conn;
	String param;

	VehiculoList() {
		arts = new CopyOnWriteArrayList<Vehiculo>();
		param = null;
	}

	VehiculoList(String nomb) {
		arts = new CopyOnWriteArrayList<Vehiculo>();
		param = nomb;
	}

	VehiculoList(boolean charge) {
		if (charge == false) {
//don't load data
		} else {
			arts = new CopyOnWriteArrayList<Vehiculo>();
			param = null;
		}
	}

	@XmlElement
	public List getVehiculo() {
		try {
			arts = getVehiculoList(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.arts;
	}

	public void setVehiculo(List<Vehiculo> arts) {
		this.arts = arts;
	}

	public List<Vehiculo> getVehiculoList(String param) throws Exception {
		String whereQuery = "";
		if (param != null) {
			whereQuery = " AND ve.codi_vehi = '" + param + "'";
		}
		this.conn = new Conexion().conn();
		PreparedStatement cmd = this.conn.prepareStatement(
				"SELECT ve.codi_vehi, mo.codi_mode, mo.nomb_mode, ma.nomb_marc,ve.anio_vehi, tr.codi_tran, tr.nomb_tran, ve.pasa_vehi, ve.puer_vehi, ve.aire_vehi,ve.audi_vehi, co.codi_comb, co.nomb_comb, li.codi_lice,li.nomb_lice, ve.esta_vehi, tp.codi_tipo_vehi, tp.tipo_vehi, ve.depo_vehi FROM vehiculo as ve INNER JOIN modelo as mo ON ve.codi_mode = mo.codi_mode INNER JOIN marca ma ON mo.codi_marc= ma.codi_marc INNER JOIN transmision as tr ON ve.codi_tran = tr.codi_tran INNER JOIN combustible as co ON ve.codi_comb = co.codi_comb INNER JOIN licencia as li ON ve.codi_lice=li.codi_lice INNER JOIN tipo_vehiculo as tp ON ve.codi_tipo_vehi=tp.codi_tipo_vehi WHERE ve.esta_vehi=1 "
						+ whereQuery);
		ResultSet res = cmd.executeQuery();
		while (res.next()) {
			Vehiculo tmpVehiculo = new Vehiculo();
			tmpVehiculo.setCodi_vehi(res.getInt(1));
			tmpVehiculo.setCodi_mode(res.getInt(2));
			tmpVehiculo.setNomb_mode(res.getString(3));
			tmpVehiculo.setNomb_marc(res.getString(4));
			tmpVehiculo.setAnio_vehi(res.getString(5));
			tmpVehiculo.setCodi_tran(res.getInt(6));
			tmpVehiculo.setNomb_tran(res.getString(7));
			tmpVehiculo.setPasa_vehi(res.getInt(8));
			tmpVehiculo.setPuer_vehi(res.getInt(9));
			tmpVehiculo.setAire_vehi(res.getInt(10));
			tmpVehiculo.setAudi_vehi(res.getString(11));
			tmpVehiculo.setCodi_comb(res.getInt(12));
			tmpVehiculo.setNomb_comb(res.getString(13));
			tmpVehiculo.setCodi_lice(res.getInt(14));
			tmpVehiculo.setNomb_lice(res.getString(15));
			tmpVehiculo.setEsta_vehi(res.getInt(16));
			tmpVehiculo.setCodi_tipo(res.getInt(17));
			tmpVehiculo.setNomb_tipo(res.getString(18));
			tmpVehiculo.setDepo_vehi(res.getDouble(19));
			arts.add(tmpVehiculo);
		}
		return arts;
	}

	public String add(int codi_mode, String anio_vehi, int codi_tran, int pasa_vehi, int puer_vehi, int aire_vehi,
			String audi_vehi, int codi_comb, int codi_lice, int codi_tipo, double depo_vehi) throws Exception {
		String resp = "0";
		try {
			this.conn = new Conexion().conn();
			PreparedStatement cmd = this.conn
					.prepareStatement("INSERT INTO vehiculo VALUES(NULL,?,?,?,?,?,?,?,?,?,1,?,?)");
			cmd.setInt(1, codi_mode);
			cmd.setString(2, anio_vehi);
			cmd.setInt(3, codi_tran);
			cmd.setInt(4, pasa_vehi);
			cmd.setInt(5, puer_vehi);
			cmd.setInt(6, aire_vehi);
			cmd.setString(7, audi_vehi);
			cmd.setInt(8, codi_comb);
			cmd.setInt(9, codi_lice);
			cmd.setInt(10, codi_tipo);
			cmd.setDouble(11, depo_vehi);
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

	public String update(int codi_vehi, int codi_mode, String anio_vehi, int codi_tran, int pasa_vehi, int puer_vehi,
			int aire_vehi, String audi_vehi, int codi_comb, int codi_lice, int codi_tipo, double depo_vehi)
			throws Exception {
		String resp = "0";
		try {
			this.conn = new Conexion().conn();
			PreparedStatement cmd = this.conn.prepareStatement(
					"UPDATE vehiculo SET codi_mode=?, anio_vehi=?, codi_tran=?, pasa_vehi=?, puer_vehi=?, aire_vehi=?, audi_vehi=?, codi_comb=?, codi_lice=?, codi_tipo_vehi=?, depo_vehi=? WHERE codi_vehi=?");
			cmd.setInt(1, codi_mode);
			cmd.setString(2, anio_vehi);
			cmd.setInt(3, codi_tran);
			cmd.setInt(4, pasa_vehi);
			cmd.setInt(5, puer_vehi);
			cmd.setInt(6, aire_vehi);
			cmd.setString(7, audi_vehi);
			cmd.setInt(8, codi_comb);
			cmd.setInt(9, codi_lice);
			cmd.setInt(10, codi_tipo);
			cmd.setDouble(11, depo_vehi);
			cmd.setInt(12, codi_vehi);
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

	public int delete(int codi_vehi) throws Exception {
		int affectedRows = -1;
		String sql = "UPDATE vehiculo SET esta_vehi = 0 WHERE codi_vehi= " + codi_vehi;
		this.conn = new Conexion().conn();
		Statement st = conn.createStatement();
		affectedRows = st.executeUpdate(sql);
		return affectedRows;
	}
}
