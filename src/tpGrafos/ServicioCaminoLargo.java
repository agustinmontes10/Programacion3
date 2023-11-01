package tpGrafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ServicioCaminoLargo {

	private Grafo<?> grafo;
	private HashMap<Integer, String> visitados;
	
	public ServicioCaminoLargo(Grafo<?> grafo) {
		this.grafo = grafo;
		visitados = new HashMap<>();
	}
	
	public ArrayList<Integer> encontrarCaminoLargo(int vOrigen, int vDestino) {
		ArrayList<Integer> camino = new ArrayList<>();
		Iterator<Integer> it = this.grafo.obtenerVertices();
		
		while(it.hasNext()) { // PINTO TODOS DE BLANCO
			int verticeId = it.next();
			visitados.put(verticeId, "blanco");
		}
		
		ArrayList<ArrayList<Integer>> caminos = caminoLargo_visit(vOrigen, vDestino);
		
		for (ArrayList<Integer> c: caminos) {
			if(c.size() > camino.size()) {
				camino = c;
			}
		}
		
		return camino;
	}
	
	private ArrayList<ArrayList<Integer>> caminoLargo_visit(int vActual, int vDestino) {
	
		ArrayList<ArrayList<Integer>> solucion = new ArrayList<>();
		
		if(vActual == vDestino) {
			ArrayList<Integer> out = new ArrayList<>();
			out.add(vActual);
			solucion.add(out);
		} 
		
		else {
			visitados.put(vActual, "amarillo"); 
			Iterator<Integer> it = this.grafo.obtenerAdyacentes(vActual); 
			
			while(it.hasNext()) {
				int adyacente = it.next(); 
				
				if(visitados.get(adyacente).equals("blanco")) { 
					ArrayList<ArrayList<Integer>> subCaminosDesdeAdy = caminoLargo_visit(adyacente, vDestino); 
					
					for (ArrayList<Integer> subCamino : subCaminosDesdeAdy) {	// RECORRO TODOS LOS SUBCAMINOS DESDE EL V-ACTUAL Y ME AGREGO PRIMERO				
						subCamino.add(0, vActual); 
						solucion.add(subCamino); 
					}
				}
				
			}
			
			visitados.put(vActual, "blanco"); 	// PINTO DE BLANCO AL V-ACTUAL PARA PODER VOLVER A PASAR SOBRE ESTE VERTICE EN OTRO CAMINO
		}									
		return solucion;
	}
	
}
