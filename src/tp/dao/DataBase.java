package tp.dao;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



import tp.controller.Mensaje;

public abstract class DataBase {

	//private static Connection con = null;
	//private static PreparedStatement pstm = null;
	public static enum Modo {LOAD, RESET};

	public static String url;
	public static String user;
	public static String password;
	public static String filename = "datosDB.json";
	public static Modo modo_operacion;
	public static Boolean funciona = false;
    
	private static Connection conec;

	public static Mensaje probarConexion() {
		//INTENTAR CONECTARSE
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			con =DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
			return new Mensaje(false,"Driver jdbc inválido");
		} catch (SQLException e) {
//			e.printStackTrace();
			return new Mensaje(false,"No se pudo establecer la conexión con la DB, intente otros datos");
		}
		finally {
			funciona = false;
			
		}
		cerrarConexion(con);
		funciona = true;
		return new Mensaje(true,"");
	}
	
	public static Mensaje leerJson() {
		
		//INTENTAR LEER EL ARCHIVO
		JSONParser parser = new JSONParser();
		FileReader reader = null;
		
		try{
			reader = new FileReader(filename);
			JSONObject objeto = (JSONObject) parser.parse(reader);
			url = (String) objeto.get("url");
			user = (String) objeto.get("user");
			password = (String) objeto.get("password");
			modo_operacion = Modo.valueOf((String) objeto.get("modo"));
			reader.close();
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			return new Mensaje(false,"No se encuentra el archivo datosDB.json");
		} catch (IOException e) {
//			e.printStackTrace();
			return new Mensaje(false,"Error de lectura en datosDB.json");
		} catch (ParseException e) {
//			e.printStackTrace();
			return new Mensaje(false,"Archivo datosDB.json inválido");
		}
	
		
		return probarConexion();
	}
	
	@SuppressWarnings("unchecked")
	public static Mensaje escribirJson() {
        try (FileWriter file = new FileWriter(filename)) {
        	JSONObject objeto = new JSONObject();
        	objeto.put("url", url);
        	objeto.put("user", user);
        	objeto.put("password", password);
        	objeto.put("url", url);
        	objeto.put("modo",modo_operacion.toString());
        	
            file.write(objeto.toJSONString());
            file.flush();
 
        } catch (IOException e) {
//        	e.printStackTrace();
        	return new Mensaje(false,"Error de escritura en datosDB.json");
        }
        return new Mensaje(true,"");
	}



	public static Connection getConexion() {
//		conec = null;
//		Scanner s = null;
		try{
//			s = new Scanner(new File("datosDB.txt"));
			Class.forName("org.postgresql.Driver");
			conec =DriverManager.getConnection(url,user,password);
//			s.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());		
		}
		return conec;
	}


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

	public static void inicializarTablas() {
		if(funciona) {
			if(modo_operacion == Modo.LOAD)
				cargarDB();
			else
				resetDB();
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
		ejecutarScript("scriptPobladoDeTablas.sql");
	}


	//Quizás resulte útil:
	//https://softwareengineering.stackexchange.com/questions/339598/how-to-write-a-proper-class-to-connect-to-database-in-java

}
