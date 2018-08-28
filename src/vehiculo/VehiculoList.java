package vehiculo;
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

import transmision.Transmision;
import usuario.Usuario;
import vehiculo.Vehiculo;
@XmlRootElement(name = "vehiculoList")
@XmlSeeAlso({ Vehiculo.class })
public class VehiculoList {
	private List<Vehiculo> arts;
	String url = "jdbc:mysql://localhost:3306/";
	String dbName = "rentadb";
	String driver = "com.mysql.jdbc.Driver";
	String userName = "root";
	String password = "";
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

	public Connection conn()
			throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		Class.forName(driver).newInstance();
		Connection conn = DriverManager.getConnection(url + dbName, userName, password);
		return conn;
	}

	public List<Vehiculo> getVehiculoList(String param) throws Exception {
		String whereQuery = "";
		if (param != null) {
				whereQuery = " WHERE codi_vehi = '" + param + "'";
		}
		Connection conn = conn();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery("SELECT codi_vehi, codi_mode, anio_vehi, codi_tran, pasa_vehi, puer_vehi, aire_vehi, audi_vehi, codi_comb, codi_lice, esta_vehi, foto_vehi, prec_vehi, codi_tipo\n" + 
				"FROM rentadb.vehiculo  " + whereQuery);
		while (res.next()) {
			Vehiculo tmpVehiculo = new Vehiculo();
			tmpVehiculo.setCodi_vehi(Integer.parseInt(res.getString("codi_vehi")));
			tmpVehiculo.setCodi_mode(Integer.parseInt(res.getString("codi_mode")));
			tmpVehiculo.setAnio_vehi(res.getString("anio_vehi"));
			tmpVehiculo.setCodi_tran(Integer.parseInt(res.getString("codi_tran")));
			tmpVehiculo.setPasa_vehi(Integer.parseInt(res.getString("pasa_vehi")));
			tmpVehiculo.setPuer_vehi(Integer.parseInt(res.getString("aire_vehi")));
			tmpVehiculo.setAudi_vehi(res.getString("audi_vehi"));
			tmpVehiculo.setAire_vehi(Integer.parseInt(res.getString("aire_vehi")));
			tmpVehiculo.setCodi_comb(Integer.parseInt(res.getString("codi_comb")));
			tmpVehiculo.setCodi_lice(Integer.parseInt(res.getString("codi_lice")));
			tmpVehiculo.setEsta_vehi(Integer.parseInt(res.getString("esta_vehi")));
			tmpVehiculo.setFoto_vehi(res.getString("foto_vehi"));
			tmpVehiculo.setPrec_vehi(Double.parseDouble(res.getString("prec_vehi")));
			tmpVehiculo.setCodi_tipo(Integer.parseInt(res.getString("codi_tipo")));
			tmpVehiculo.setPuer_vehi(Integer.parseInt(res.getString("puer_vehi")));
			arts.add(tmpVehiculo);
		}
		return arts;
	}
	
	public String add(int codi_mode,String anio_vehi,int codi_tran,int pasa_vehi,int puer_vehi,int aire_vehi,String audi_vehi,int codi_comb,int codi_lice,int esta_vehi,String 
			foto_vehi,double prec_vehi,int codi_tipo) throws Exception {
		String resp = "0";
		try {
			Connection conn = conn();
			Statement st = conn.createStatement();
			String sql = "INSERT INTO rentadb.vehiculo\n" + 
					"(codi_mode, anio_vehi, codi_tran, pasa_vehi, puer_vehi, aire_vehi, audi_vehi, codi_comb, codi_lice, esta_vehi, foto_vehi, prec_vehi, codi_tipo)\n" + 
					"VALUES("+codi_mode+", '"+anio_vehi+"', "+codi_tran+", "+pasa_vehi+", "+puer_vehi+", "+aire_vehi+", '"+audi_vehi+"', "+codi_comb+", "+codi_lice+", "+esta_vehi+", '"+foto_vehi+"', "+prec_vehi+", "+codi_tipo+");";
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
	
	public String update(int codi_vehi,int codi_mode,String anio_vehi,int codi_tran,int pasa_vehi,int puer_vehi,int aire_vehi,String audi_vehi,int codi_comb,int codi_lice,int esta_vehi,String 
			foto_vehi,double prec_vehi,int codi_tipo) throws Exception {
		String resp = "0";
		try {
			Connection conn = conn();
			Statement st = conn.createStatement();
			String sql = "UPDATE rentadb.vehiculo\n" + 
					"SET codi_mode="+codi_mode+", anio_vehi='"+anio_vehi+"', codi_tran="+codi_tran+", pasa_vehi="+pasa_vehi+", puer_vehi="+puer_vehi+", aire_vehi="+aire_vehi+", audi_vehi='"+audi_vehi+"', codi_comb="+codi_comb+", codi_lice="+codi_lice+", esta_vehi="+esta_vehi+", foto_vehi='"+foto_vehi+"', prec_vehi="+prec_vehi+", codi_tipo="+codi_tipo+"\n" + 
					"WHERE codi_vehi="+codi_vehi+";";
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

	public int delete(int codi_vehi) throws Exception {
		int affectedRows = -1;
		String sql = "DELETE FROM vehiculo WHERE codi_vehi= " + codi_vehi;
		Connection conn = conn();
		Statement st = conn.createStatement();
		affectedRows = st.executeUpdate(sql);
		return affectedRows;
	}
}
