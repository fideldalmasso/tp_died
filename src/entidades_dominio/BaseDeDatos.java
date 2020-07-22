package entidades_dominio;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BaseDeDatos {

	Connection con;
	static final String driver = "org.postgresql.Driver";
	static final String url = "***REMOVED***";
	static final String user = "***REMOVED***";
	static final String password = "***REMOVED***";
	
	public BaseDeDatos() {
		super();
	}

	void conectarDB() {
		try{
			Class.forName(driver);
			con=DriverManager.getConnection(url,user,password);
		}catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());		
		}catch(SQLException e1) {
			System.out.println(e1.getMessage());	
		}
	}
	
	void desconectarDB() {
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	void ejecutarScript(String nombreArchivo) {
		//https://stackoverflow.com/questions/1497569/how-to-execute-sql-script-file-using-jdbc
		conectarDB();
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
				desconectarDB();
				s.close();
				st.close();
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
			
		}
	}
	
	void inicializarDB() {
		conectarDB();
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
				desconectarDB();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		
		if(!existe_schema) //si no existe el schema ejecuto el script de creacion
			ejecutarScript("scriptCreacionDeTablas.sql");
		
	}
	

	
	void ejecutarSentencia(String sentencia) {
		PreparedStatement pstm = null;
		try {
			this.conectarDB();
			pstm = con.prepareStatement(sentencia);
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());	
		}
		finally {
				try {
					pstm.close();
					this.desconectarDB();
				} catch (SQLException e) {
					System.out.println(e.getMessage());	
				}
				
		}
	}
	
	void insert(Registrable objeto) {
		ejecutarSentencia(objeto.getSentenciaInsert());
	}
	
	void delete(Registrable objeto) {
		ejecutarSentencia(objeto.getSentenciaDelete());
	}
	
	void update(Registrable objeto1, Registrable objeto2) {
		ejecutarSentencia(objeto1.getSentenciaUpdate(objeto2));
	}
	
	Boolean existe(String tabla, String campo, String valor) {	
		Boolean existe = false;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conectarDB();
			pstm = con.prepareStatement("SELECT * FROM tp."+tabla+" WHERE "+campo+" = '"+valor+"'");
			rs = pstm.executeQuery();
			while(rs.next() && !existe) {
				existe = true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());	
		}
		finally {
			try {
				rs.close();	
				pstm.close();
				desconectarDB();
			} catch (SQLException e) {
				System.out.println(e.getMessage());	
			}
			
		}
		return existe;
		
	}

}
