package base_de_datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ConexionDB {
	
	//private static Connection con = null;
	//private static PreparedStatement pstm = null;
	static final String driver = "org.postgresql.Driver";
	static final String url = "***REMOVED***";
	static final String user = "***REMOVED***";
	static final String password = "***REMOVED***";
	
	public static Connection getConexion() {
		Connection con = null;
		try{
			Class.forName(driver);
			con =DriverManager.getConnection(url,user,password);
		}catch(Exception e) {
			System.out.println(e.getMessage());		
		}
		return con;
	}
	
//	public static PreparedStatement getPstm(Connection con,String sql) {
//		PreparedStatement pstm = null;
//		try{
//			pstm = con.prepareStatement(sql);
//		}catch(Exception e) {
//			System.out.println(e.getMessage());	
//		}
//		return pstm;
//	}
	
//	public static Boolean ejecutar(PreparedStatement pstm) {
//		try {
//			return pstm.executeUpdate() == 1;
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());	
//		}
//		return false;
//	}
	
	public static void cerrarConexion(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());	
		}
	}
	
	public static void cerrarPstm(PreparedStatement pstm) {
		try {
			pstm.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());	
		}
	}
	
	public static void cerrarRs(ResultSet rs) {
		try {
			rs.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());	
		}
	}
	
	
}
