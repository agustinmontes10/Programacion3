package tpGrafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ServicioCamino {

	private Grafo<?> grafo;
	private HashMap<Integer, String> visitados;
	
	public ServicioCamino(Grafo<?> grafo) {
		this.grafo = grafo;
		visitados = new HashMap<>();
	}
	
	public ArrayList<Integer> encontrarCamino(int vOrigen, int vDestino) {
		Iterator<Integer> it = this.grafo.obtenerVertices();
		
		while(it.hasNext()) { 
			int verticeId = it.next();
			visitados.put(verticeId, "blanco");
		}
		
		ArrayList<Integer> camino = camino_visit(vOrigen, vDestino);
		return camino;
	}
	
	private ArrayList<Integer> camino_visit(int vActual, int vDestino) {
		
		if(vActual == vDestino) {
			ArrayList<Integer> out = new ArrayList<>();
			out.add(vActual);
			return out;
		}
		
		visitados.put(vActual, "amarillo");
		Iterator<Integer> it = this.grafo.obtenerAdyacentes(vActual);
		
		while(it.hasNext()) { 
			int adyacente = it.next();
			
			if(visitados.get(adyacente).equals("blanco")) {
				ArrayList<Integer> camino = new ArrayList<>(); 
				camino.add(vActual);  
				
				ArrayList<Integer> caminoParcial = camino_visit(adyacente, vDestino);
				
				//	if( caminoParcial.size() > 0 && caminoParcial.get(caminoParcial.size()-1) == vDestino )	ESTO ES POSIBLE A QUE SOLO VA A SER > 0 SI LLEGA AL DESTINO
				if( caminoParcial.size() > 0 ) {
					camino.addAll(caminoParcial);
					return camino;
				}
			}
			
		}
		visitados.put(vActual, "negro");
		return new ArrayList<>();
	}
	
}
