 package tpGrafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ServicioBFS {

	private Grafo grafo;
	private HashMap< Integer, String > visitados;
	private ArrayList<Integer> fila;
	
	public ServicioBFS(Grafo grafo) {
		this.grafo = grafo;
		this.visitados = new HashMap<Integer, String>();
		this.fila = new ArrayList<>();
	}
	
	public List<Integer> bfsForest() {
		fila.clear();
		Iterator<Integer> vertices = grafo.obtenerVertices();
		ArrayList<Integer> camino = new ArrayList<>();
		
		while(vertices.hasNext()) {
			visitados.put(vertices.next(), "blanco");
		}
		
		for (Integer v : visitados.keySet()) {
			if( visitados.get(v) == "blanco" ) {
				camino.addAll(bfs_visit(v));
			}
		}
		
		return camino;
	}
	
	
	public List<Integer> bfs_visit(int v) {
		visitados.put(v, "negro");
		fila.add(v);
		ArrayList<Integer> caminoParcial = new ArrayList<Integer>();
		
		while( fila.size() > 0 ) {
			int x = fila.get(0);
			Iterator <Integer> adyacentesX = grafo.obtenerAdyacentes(x);
			
			while( adyacentesX.hasNext() ) {
				int y = adyacentesX.next();
				if( visitados.get(y) == "blanco" ) {
					visitados.put(y, "negro");
					fila.add(y);
				}
			}
		
			fila.remove(0);
			caminoParcial.add(x);
			
		}
		
		
		return caminoParcial;
	}
	
}
