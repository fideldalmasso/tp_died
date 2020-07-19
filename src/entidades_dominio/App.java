package entidades_dominio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
	public static void main (String[] args){
		//Ejemplos de conexion a bases de datos
		try{
			Class.forName("org.postgresql.Driver");
			/*Connection con=DriverManager
					.getConnection("jdbc:postgresql://motty.db.elephantsql.com:5432/xwhxadsq",
							"xwhxadsq",
							"8Gr8YWh-wiWKRnSzAlMJZEQDFMtCMG3g");
			*/
			Connection con=DriverManager
					.getConnection("***REMOVED***",
							"***REMOVED***",
							"***REMOVED***");
			Statement stat=con.createStatement();
			stat.execute("CREATE TABLE empresa (ID VARCHAR(256))");
			stat.close();
			con.close();
		}catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());		
		}catch(SQLException e1) {
			System.out.println(e1.getMessage());	
		}finally{
			
		}
		
	}
}
