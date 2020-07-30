package tp.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Utilidades {

	public static Boolean esDouble(String linea) {
		Pattern r = Pattern.compile("(\\d+)(((,|.)?)\\d+)?");
		Matcher m = r.matcher(linea);
		return m.matches();
	}

	public static boolean esFecha(String linea) {
		try {
			parsearFecha(linea);
		}
		catch( DateTimeParseException e) {
			return false;
		}
		return true;
	}

	public static LocalDate parsearFecha(String linea) throws DateTimeParseException{
		return LocalDate.parse(linea,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
	public static String formatearFecha(LocalDate fecha) {
		return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
	public static String[] enumToStringArray(Class<? extends Enum<?>> e) {
		//https://stackoverflow.com/a/13783744/13176588
	    return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
	}
	
	
	
}
