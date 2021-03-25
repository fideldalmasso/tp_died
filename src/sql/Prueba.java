package sql;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import tp.controller.Mensaje;
import tp.dao.DataBase.Modo;

public class Prueba {

	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner s = null;
		File f = null;
		
		JSONArray total = new JSONArray();
		

		try (FileWriter file = new FileWriter("salida.json")){
			f = new File("datos.pgn");
			s = new Scanner(f);
			s.useDelimiter("\n");
			int x =0;
			while(s.hasNext()) {
				JSONArray partida = new JSONArray();
				String linea = s.next();
				if(linea.charAt(0)=='[' || linea.length()==0 || linea.charAt(0)=='\r')
					continue;
				linea = linea.replaceAll("\\d+\\.\\s","");
				//System.out.println(linea);
				String[] recortada = linea.split("\\s+");
				int tam = recortada.length;
				
				for (int i = 0; i < recortada.length; i++) {
					if(recortada[i].length()==0)
						continue;
					if(recortada[i].charAt(0)=='{')
						break;
//					if(recortada[i].charAt(0)=='{' || recortada[i].charAt(recortada[i].length()-1)=='}')
//						continue;
					JSONObject movimiento = new JSONObject();
					movimiento.put("codigo",recortada[i]);
					movimiento.put("tiempo",ThreadLocalRandom.current().nextInt(0,120));
					partida.add(movimiento);
				}
				
				JSONObject partida2 = new JSONObject();
				partida2.put("movimientos",partida);
				String nombre = recortada[recortada.length-1];
				if(nombre.length()>3)
					nombre = "empate";
//				String nombre = "";
//				switch(recortada[recortada.length-1]) {
//				case "0-1":
//					nombre= "gana negras ";
//					break;
//				case "1-0":
//					nombre= "gana blancas";
//					break;
//				default:
//					nombre = "empate";
//				}
				FileWriter file2 = new FileWriter("salida/"+String.format("%04d",x)+" "+nombre+".json");
				file2.write(partida2.toJSONString().replaceAll("\\{", "\n\\{"));
				file2.close();
				x++;
				total.add(partida2);
				
			}
			file.write(total.toJSONString());
			file.flush();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println(total.size());
		
	}

}
