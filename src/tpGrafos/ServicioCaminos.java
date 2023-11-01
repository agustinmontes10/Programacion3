package tpGrafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ServicioCaminos {

	private Grafo<?> grafo;
	private int origen;
	private int destino;
	private int lim;
	private HashMap< Integer, String > visitados;
	
	// Servicio caminos
	public ServicioCaminos(Grafo<?> grafo, int origen, int destino, int lim) {
		this.grafo = grafo;
		this.origen = origen;
		this.destino = destino;
		this.lim = lim;
		this.visitados = new HashMap<Integer, String>();
	}

	public List<List<Integer>> encontrarCaminos() {
		Iterator<Integer> vertices = grafo.obtenerVertices();
		ArrayList<Integer> solucion = new ArrayList<>();
		int limAcumulado = 0;
		
		while(vertices.hasNext()) {
			visitados.put(vertices.next(), "blanco");
		}
		
		List<List<Integer>> camino = caminos_visit(origen, destino, limAcumulado);
		
		return camino;
	}
	
	
	public List<List<Integer>> caminos_visit(int v, int destino, int limAcumulado) {
		List<List<Integer>> solucion = new ArrayList<>();
		
		if( v == destino ) {
			List<Integer> me = new ArrayList<>();
			me.add(v);
			solucion.add(me);
		}
		
		else {
			visitados.put(v, "amarillo");
			limAcumulado++;
			Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(v);
			
			while( adyacentes.hasNext() ) {
				int ady = adyacentes.next();
				if( visitados.get(ady) == "blanco" && limAcumulado <= lim) { // aca agregar el limite
					List<List<Integer>> subCaminosDesdeAdy = caminos_visit(ady, destino, limAcumulado); 
					
					
					for (List<Integer> subCamino : subCaminosDesdeAdy) {	
						subCamino.add(0, v); 
						solucion.add(subCamino); 
					}
				}
			}
			visitados.put(v, "blanco");
		}
		
		
		
		return solucion;
	}
	
}
