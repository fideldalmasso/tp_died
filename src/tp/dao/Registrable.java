package tp.dao;

import java.util.List;
import java.util.Optional;

public interface Registrable<T> {
	
	public Boolean add(T t);
	
	public Boolean delete(String ...s);
	
	public Boolean update(T original, T nuevo);
	
	public Optional<T> get(String ...s);
	
	public List<T> getAll();
	
	
	
//	public String getSentenciaInsert();
//	
//	public String getSentenciaDelete();
//	
//	public String getSentenciaUpdate(Object object);
//	
//	enum tipoDato{String,Integer,Double,LocalDateTime};
	
//	public static String getSentenciaInsert2(String tabla, List<Object> lista) {
//		String temp = "INSERT INTO tp." + tabla + " values (";
//		for(Object o : lista) {
//			tipoDato tipo = tipoDato.valueOf(o.getClass().getSimpleName());
//			switch (tipo) {
//			case String:
//				temp+="'"+o+"'";
//				break;
//			case Double:
//				temp+=((Double)o).toString();
//				break;
//			case Integer:
//				temp+=((Integer)o).toString();
//				break;
//			case LocalDateTime:
//				temp+="'"+((LocalDateTime)o).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"'";
//				break;
//			}
//			temp+=",";
//		}
//		temp= temp.substring(0,temp.length()) + ")";
//		return temp;
//	}
	
	//public Registrable cargarObjeto(BaseDeDatos db, String consulta);
	//public List<Registrable> cargarTabla(BaseDeDatos db, String consulta, int inicio,int limite);
	//public List<Registrable> cargarTablaOrdenada(BaseDeDatos db, String consulta, int inicio,int limite);
	
}
