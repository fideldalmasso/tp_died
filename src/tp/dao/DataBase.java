package tp.dao;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tp.dominio.Marca;
import tp.dominio.Modelo;

public abstract class DataBase {

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
		
		public static List<String> select(String consulta){
			//https://softwareengineering.stackexchange.com/questions/339598/how-to-write-a-proper-class-to-connect-to-database-in-java
			
			List<String> lista = new ArrayList<String>();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			Connection con = DataBase.getConexion();
		
			String[] columnas = consulta.
							replace(" ", " ").
							replace("SELECT", "").
							split("FROM")[0].
							split(",");
			
			try {
				pstm = con.prepareStatement(consulta);
				rs = pstm.executeQuery();
				while(rs.next()) {
					//Marca marcaTemp = new Marca(rs.getString(2));
					//lista.add(new Modelo(rs.getString(1),marcaTemp));
				}
			}catch(Exception e){
				System.out.println(e.getMessage());	
			}finally {
				DataBase.cerrarRs(rs);
				DataBase.cerrarPstm(pstm);
				DataBase.cerrarConexion(con);
			}
			
			
			
			return lista;
		}
		
//		public static PreparedStatement getPstm(Connection con,String sql) {
//			PreparedStatement pstm = null;
//			try{
//				pstm = con.prepareStatement(sql);
//			}catch(Exception e) {
//				System.out.println(e.getMessage());	
//			}
//			return pstm;
//		}
		
//		public static Boolean ejecutar(PreparedStatement pstm) {
//			try {
//				return pstm.executeUpdate() == 1;
//			} catch (SQLException e) {
//				System.out.println(e.getMessage());	
//			}
//			return false;
//		}
		
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
//----------------------------------------------------------------------------------
//	Connection con;
//	static final String driver = "org.postgresql.Driver";
//	static final String url = "***REMOVED***";
//	static final String user = "***REMOVED***";
//	static final String password = "***REMOVED***";
	


//	void conectarDB() {
//		try{
//			Class.forName(driver);
//			con=DriverManager.getConnection(url,user,password);
//		}catch(ClassNotFoundException e) {
//			System.out.println(e.getMessage());		
//		}catch(SQLException e1) {
//			System.out.println(e1.getMessage());	
//		}
//	}
	
//	void desconectarDB() {
//		try {
//			con.close();
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//		}
//	}
	
	private static void ejecutarScript(String nombreArchivo) {
		//https://stackoverflow.com/questions/1497569/how-to-execute-sql-script-file-using-jdbc
		Connection con = DataBase.getConexion();
		Scanner s = null;
		Statement st = null;
		File f = null;
		try {
			f = new File(nombreArchivo);
			s = new Scanner(f);
			s.useDelimiter("(;(\r)?\n)|(--\n)");
			st = con.createStatement();
			
			while(s.hasNext()) {
				String linea = s.next();
				if(linea.trim().length() > 0) {
					st.execute(linea);
				}
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try{
				
				s.close();
				st.close();
				DataBase.cerrarConexion(con);
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
			
		}
	}
	
	public static void cargarDB() {
		//checkea si existe el schema correspondiente, y si no existe lo crea
		Connection con = DataBase.getConexion();
		Boolean existe_schema = false;
		ResultSet rs = null;
		try {
			rs = con.getMetaData().getSchemas();
			while(rs.next() && !existe_schema) {
				if(rs.getString(1).equals("tp")) { 
					existe_schema = true; //ya existe el schema
				}
			}
		}	
		 catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				rs.close();
				DataBase.cerrarConexion(con);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		
		if(!existe_schema) //si no existe el schema ejecuto el script de creacion
			ejecutarScript("scriptCreacionDeTablas.sql");
		
	}
	
	public static void resetDB() {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"DROP SCHEMA IF EXISTS tp CASCADE");
			pstm.executeUpdate();
		}catch(Exception e) {
			System.out.println(e.getMessage());	
		}
		finally {
			DataBase.cerrarPstm(pstm);
			DataBase.cerrarConexion(con);
		}
		ejecutarScript("scriptCreacionDeTablas.sql");
	}
	
//	void ejecutarSentencia(String sentencia) {
//		Connection con = ConexionDB.getConexion();
//		PreparedStatement pstm = null;
//		try {
//			pstm = con.prepareStatement(sentencia);
//			pstm.executeUpdate();
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());	
//		}
//		finally {
//				ConexionDB.cerrarPstm(pstm);
//				ConexionDB.cerrarConexion(con);	
//		}
//	}
	
//	void insert(Registrable objeto) {
//		ejecutarSentencia(objeto.getSentenciaInsert());
//	}
//	
//	void delete(Registrable objeto) {
//		ejecutarSentencia(objeto.getSentenciaDelete());
//	}
//	
//	void update(Registrable objeto1, Registrable objeto2) {
//		ejecutarSentencia(objeto1.getSentenciaUpdate(objeto2));
//	}
	
//	Boolean existe(String tabla, String campo, String valor) {	
//		Connection con = ConexionDB.getConexion();
//		Boolean existe = false;
//		PreparedStatement pstm = null;
//		ResultSet rs = null;
//		try {
//			
//			pstm = con.prepareStatement("SELECT * FROM tp."+tabla+" WHERE "+campo+" = '"+valor+"'");
//			rs = pstm.executeQuery();
//			while(rs.next() && !existe) {
//				existe = true;
//			}
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());	
//		}
//		finally {
//			ConexionDB.cerrarRs(rs);
//			ConexionDB.cerrarPstm(pstm);
//			ConexionDB.cerrarConexion(con);
//		}
//		return existe;
//		
//	}

}
