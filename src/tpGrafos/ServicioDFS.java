package tpGrafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ServicioDFS {

	private Grafo grafo;
	private HashMap< Integer, String > visitados;

	public ServicioDFS(Grafo grafo) {
		this.grafo = grafo;
		this.visitados = new HashMap<Integer, String>();
	}
	
	public List<Integer> dfsForest() {
		Iterator<Integer> vertices = grafo.obtenerVertices();
		ArrayList<Integer> solucion = new ArrayList<>();
		
		while(vertices.hasNext()) {
			visitados.put(vertices.next(), "blanco");
		}
		
		for (Integer v : visitados.keySet()) {
			if( visitados.get(v) == "blanco" ) {
				solucion.addAll(dfs_visit(v));
			}
		}
		
		return solucion;
	}
	
	
	public List<Integer> dfs_visit(int v) {
		ArrayList<Integer> solucionParcial = new ArrayList<>();
		visitados.put(v, "amarillo");
		solucionParcial.add(v);
		
		Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(v);
		while( adyacentes.hasNext() ) {
			Integer ady = adyacentes.next();
			if( visitados.get(ady) == "blanco" ) {
				solucionParcial.addAll(dfs_visit(ady));
			}
		}
		
		visitados.put(v, "negro");
		
		return solucionParcial;
	}
	
}
