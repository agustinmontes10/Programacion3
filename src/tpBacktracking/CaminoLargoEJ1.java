package tpBacktracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import tpGrafos.Grafo;

public class CaminoLargoEJ1 {

	private Grafo<?> grafo;
	private HashMap<Integer, String> visitados;
	private ArrayList<Integer> caminoLargo;
	
	public CaminoLargoEJ1(Grafo<?> grafo) {
		this.grafo = grafo;
		visitados = new HashMap<>();
		this.caminoLargo = new ArrayList<>();
	}
	
	public ArrayList<Integer> encontrarCamino(int vEntrada, int vSalida) {
		Iterator<Integer> it = this.grafo.obtenerVertices();
		ArrayList<Integer> caminoInicial = new ArrayList<>();
		caminoInicial.add(1);
		
		while(it.hasNext()) { 
			int verticeId = it.next();
			visitados.put(verticeId, "blanco");
		}
		
		back(vEntrada, vSalida, caminoInicial);
		
		return caminoLargo;
	}
	
	private void back(int vActual, int vSalida, ArrayList<Integer> caminoActual) {
		
		if( vActual == vSalida && caminoActual.size() > caminoLargo.size() ) {
			this.caminoLargo.clear();
			this.caminoLargo.addAll(caminoActual);
		}
		
		else {
			visitados.put(vActual, "amarillo");
			Iterator<Integer> it = this.grafo.obtenerAdyacentes(vActual);
			
			while(it.hasNext()) { 
				int ady = it.next();
				
				if(visitados.get(ady).equals("blanco")) {
					
					caminoActual.add(ady);
					back(ady, vSalida, caminoActual);
					caminoActual.remove( caminoActual.size()-1 );
					
				}
				
			}
			visitados.put(vActual, "blanco");
		}
		
	}
	
}