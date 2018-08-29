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
				whereQuery = " WHERE ve.codi_vehi = '" + param + "'";
		}
		Connection conn = conn();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery("SELECT ve.codi_vehi, mo.codi_mode, mo.nomb_mode, ma.nomb_marc,ve.anio_vehi, tr.codi_tran, tr.nomb_tran, ve.pasa_vehi, ve.puer_vehi, ve.aire_vehi,ve.audi_vehi, co.codi_comb, co.nomb_comb, li.codi_lice,li.nomb_lice, ve.esta_vehi, ve.foto_vehi, ve.prec_vehi, tp.codi_tipo, tp.tipo_vehi FROM vehiculo as ve INNER JOIN modelo as mo ON ve.codi_mode = mo.codi_mode INNER JOIN marca ma ON mo.codi_marc= ma.codi_marc INNER JOIN transmision as tr ON ve.codi_tran = tr.codi_tran INNER JOIN combustible as co ON ve.codi_comb = co.codi_comb INNER JOIN licencia as li ON ve.codi_lice=li.codi_lice INNER JOIN tipo_vehiculo as tp ON ve.codi_tipo=tp.codi_tipo " + whereQuery);
		while (res.next()) {
			Vehiculo tmpVehiculo = new Vehiculo();
			tmpVehiculo.setCodi_vehi(Integer.parseInt(res.getString(1)));
			tmpVehiculo.setCodi_mode(Integer.parseInt(res.getString(2)));
			tmpVehiculo.setNomb_mode(res.getString(3));
			tmpVehiculo.setNomb_marc(res.getString(4));
			tmpVehiculo.setAnio_vehi(res.getString(5));
			tmpVehiculo.setCodi_tran(Integer.parseInt(res.getString(6)));
			tmpVehiculo.setNomb_tran(res.getString(7));
			tmpVehiculo.setPasa_vehi(Integer.parseInt(res.getString(8)));
			tmpVehiculo.setPuer_vehi(Integer.parseInt(res.getString(9)));
			tmpVehiculo.setAire_vehi(Integer.parseInt(res.getString(10)));
			tmpVehiculo.setAudi_vehi(res.getString(11));
			tmpVehiculo.setCodi_comb(Integer.parseInt(res.getString(12)));
			tmpVehiculo.setNomb_comb(res.getString(13));
			tmpVehiculo.setCodi_lice(Integer.parseInt(res.getString(14)));
			tmpVehiculo.setNomb_lice(res.getString(15));
			tmpVehiculo.setEsta_vehi(Integer.parseInt(res.getString(16)));
			tmpVehiculo.setFoto_vehi(res.getString(17));
			tmpVehiculo.setPrec_vehi(Double.parseDouble(res.getString(18)));
			tmpVehiculo.setCodi_tipo(Integer.parseInt(res.getString(19)));
			tmpVehiculo.setNomb_tipo(res.getString(20));
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
