package tp.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class Empresa {
	private String id_empresa;
	private List<Ruta> rutas;
	private List<Planta> plantas;
	private Planta planta_puerto;
	private Planta planta_final;
	
	
	public Empresa(String id) {
		super();
		this.id_empresa=id;
		this.rutas = new ArrayList<Ruta>();
		this.plantas = new ArrayList<Planta>();
	}
	
	public void agregarRuta(String id,Double distancia,Double duracion,Double peso_maximo_dia,String planta_origen,String planta_destino) {
		rutas.add(new Ruta(id,this.getPlanta(planta_origen),this.getPlanta(planta_destino),distancia,duracion,peso_maximo_dia));
		//rutas.add(new Ruta(id,distancia,duracion,peso_maximo_dia,peso_utilizado,this.getPlanta(planta_origen),this.getPlanta(planta_destino)));
	}
	
	public void agregarPlanta(String nombre){
		plantas.add(new Planta(nombre));
	}
	
	public List<Ruta> getRutas(Planta origen){
		List<Ruta> adyacentes = new ArrayList<Ruta>();
		for(int i=0;i<this.rutas.size();i++) {
			if(this.rutas.get(i).getOrigen()==origen){
				adyacentes.add(this.rutas.get(i));
			}
		}
		return adyacentes;
	}
	
	public Planta getPlanta(String nombre_planta) {
		Planta ans = null;
		
		for(int i=0;i<this.plantas.size();i++) {
			if(this.plantas.get(i).nombreIgual(nombre_planta)) {
				ans=this.plantas.get(i);
				break;
			}
		}
		
		return ans;
	}
	
	/*public List<Ruta> subgrafo(String plantaOrigen, String plantaDestino){
		Double min;
		List<Ruta> camino = new ArrayList<Ruta>();
		Planta origen=this.getPlanta(plantaOrigen);
		Planta destino=this.getPlanta(plantaDestino);
		
		for(int i=0;i<this.rutas.size();i++) this.rutas.get(i).setUtilizado();
		
		while(!this.obtenerCamino(origen,destino).isEmpty()){
			camino = this.obtenerCamino(origen,destino);
			min=camino.get(0).getPeso();
			for(int i=1;i<camino.size();i++) {
				if(min>camino.get(i).getPeso()) min=camino.get(i).getPeso();
			}
			for(int i=0;i<camino.size();i++) {
				camino.get(i).disminuirCapacidad(min);
			}
		}
		
		return camino;
	}*/
	
	public Double flujoMaximo(String plantaOrigen, String plantaDestino){
		Double ans=0D;
		Double min=0D;
		List<Ruta> camino;
		Planta origen=this.getPlanta(plantaOrigen);
		Planta destino=this.getPlanta(plantaDestino);
		
		for(int i=0;i<this.rutas.size();i++) this.rutas.get(i).setUtilizado();
		
		while(!this.obtenerCamino(origen,destino).isEmpty()){
			camino = this.obtenerCamino(origen,destino);
			min=camino.get(0).getPeso();
			for(int i=1;i<camino.size();i++){
				if(min>camino.get(i).getPeso()) min=camino.get(i).getPeso();
			}
			for(int i=0;i<camino.size();i++) {
				camino.get(i).disminuirCapacidad(min);
			}
			ans+=min;
		}
		
		return ans;
	}
	
	public List<Ruta> obtenerCamino(Planta origen, Planta destino){
    	List<Ruta> ans = new ArrayList<Ruta>();
		List<Ruta> camino = new ArrayList<Ruta>();
    	Queue<Planta> q= new LinkedList<Planta>();
    	HashSet<Planta> m= new HashSet<Planta>();
    	
    	q.add(origen);
    	
    	while(!q.isEmpty()){
    		Planta act = q.poll();
    		List<Ruta> ady = this.getRutas(act);
    		for(int i=0;i<ady.size();i++) {
    			if(ady.get(i).getPeso()>0D && !m.contains(ady.get(i).getDestino())) {
    				q.add(ady.get(i).getDestino());
        			m.add(ady.get(i).getDestino());
        			camino.add(ady.get(i));
    			}
    		}
    	}
    	
    	if(m.contains(destino)) {
    		Planta act = destino;
    		for(int i=camino.size()-1;i>-1;i--){
	    		if(camino.get(i).getDestino()==act) {
	    			ans.add(camino.get(i));
	    			act=camino.get(i).getOrigen();
	    		}
    		}
    	}
    	
    	return ans;
    }
	
	public void plantRank() {
		Double d=0.85D;
		Map<Planta,Integer> m = new HashMap<Planta,Integer>();
		Map<Integer,Planta> m2 = new HashMap<Integer,Planta>();
		Map<Double,Planta> ans = new TreeMap<Double,Planta>();
		Double[] actual = new Double[this.plantas.size()];
		Double[] pageRank = new Double[this.plantas.size()];
		Double[] gradoNegativo = new Double[this.plantas.size()];
		Double[][] mat = new Double[this.plantas.size()][this.plantas.size()];
		
		for(int i=0;i<this.plantas.size();i++){
			m2.put(i,this.plantas.get(i));
			m.put(this.plantas.get(i),i);
			gradoNegativo[i]=0D;
			pageRank[i]=1D;
			actual[i]=0D;
		}
		
		for(int i=0;i<mat.length;i++) {
			for(int j=0;j<mat.length;j++) {
				mat[i][j]=0D;
			}
		}
		
		for(int i=0;i<this.rutas.size();i++) {
			gradoNegativo[m.get(rutas.get(i).getOrigen())]+=1D;
		}
		
		for(int i=0;i<this.rutas.size();i++) {
			Integer c = m.get(rutas.get(i).getOrigen());
			Integer f = m.get(rutas.get(i).getDestino());
			mat[f][c]+=1D/gradoNegativo[c];
		}
		
		Boolean corte = true;
		while(corte) {
			corte=false;
			for(int j=0;j<mat.length;j++) {
				for(int k=0;k<mat.length;k++) {
					actual[j] += mat[j][k]*pageRank[k];
				}
				actual[j] = (1-d) + d*actual[j];
			}
			
			for(int j=0;j<actual.length;j++) {
				if(Double.max(actual[j],pageRank[j])-Double.min(actual[j],pageRank[j])>0.0000000001D){
					corte=true;
				}
			}
			for(int i=0;i<pageRank.length;i++) {
				pageRank[i]=actual[i];
				actual[i]=0D;
			}
		}
		
		for(int i=0;i<pageRank.length;i++) {
			m2.get(i).setPlant_rank(pageRank[i]);
			ans.put(pageRank[i],m2.get(i));
		}
		
	}
	
	public Double[][] matrizCaminoMinimo(Integer modo){
		int tam=this.plantas.size();
		Map<Planta,Integer> m = new HashMap<Planta,Integer>();
		Double[][] caminosMinimos = new Double[tam][tam];
		
		for(int i=0;i<tam;i++) {
			m.put(this.plantas.get(i),i);
		}
		
		for(int i=0;i<tam;i++) {
			for(int j=0;j<tam;j++) {
				caminosMinimos[i][j]=Double.MAX_VALUE;
			}
		}
		
		for(int i=0;i<rutas.size();i++) {
			Integer f=m.get(rutas.get(i).getOrigen());
			Integer c=m.get(rutas.get(i).getDestino());
			Double costo;
			
			if(modo==1) {
				costo = rutas.get(i).getTiempo();
			}else {
				costo = rutas.get(i).getDistancia();
			}
			
			if(costo<caminosMinimos[f][c]) caminosMinimos[f][c]=costo;
		}
		
		for(int i=0;i<tam;i++) {
			for(int j=0;j<tam;j++) {
				for(int k=0;k<tam;k++) {
					if(caminosMinimos[j][i]+caminosMinimos[i][k]<caminosMinimos[j][k])
						caminosMinimos[j][k]=caminosMinimos[j][i]+caminosMinimos[i][k];
				}
			}
		}
		
		return caminosMinimos;
	}
	
	public List<List<Ruta>> caminosMinimos(String nombre_origen, String nombre_destino, Integer modo){
		Planta origen = this.getPlanta(nombre_origen);
		Planta destino = this.getPlanta(nombre_destino);
		Double min=Double.MAX_VALUE;
		
		List<List<Ruta>> caminos = new ArrayList<List<Ruta>>();
		List<List<Ruta>> salida = new ArrayList<List<Ruta>>();
		List<Ruta> marcados = new ArrayList<Ruta>();
		
		this.buscarCaminosAuxiliares(origen,destino,marcados,caminos);
		
		for(int i=0;i<caminos.size();i++) {
			Double suma=0D;
			for(int j=0;j<caminos.get(i).size();j++) {
				if(modo==0) {
					suma+=caminos.get(i).get(j).getDistancia();
				}else {
					suma+=caminos.get(i).get(j).getDuracion_en_minutos();
				}
			}
			if(suma==min) {
				salida.add(caminos.get(i));
			}else if(suma<min){
				min=suma;
				salida.clear();
				salida.add(caminos.get(i));
			}
		}
		return salida;
	}
	
	public void buscarCaminosAuxiliares(Planta origen, Planta destino, List<Ruta> marcados, List<List<Ruta>> salida){
		List<Ruta> adyacentes = this.getRutas(origen);
		for(int i=0;i<adyacentes.size();i++) {
			List<Ruta> copiaMarcados = marcados.stream().collect(Collectors.toList());
			if(adyacentes.get(i).getDestino()==destino) {
				copiaMarcados.add(adyacentes.get(i));
				salida.add(copiaMarcados);
			}else if(!copiaMarcados.contains(adyacentes.get(i))){
				copiaMarcados.add(adyacentes.get(i));
				this.buscarCaminosAuxiliares(adyacentes.get(i).getDestino(), destino, copiaMarcados, salida);
			}
		}
	}
	
}