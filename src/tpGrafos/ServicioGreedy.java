package tpGrafos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


public class ServicioGreedy {

	private Grafo g;
	private Grafo mejorRed;
	private ArrayList<Integer> visitados;
	private int vInicial;
	public int metrica = 0;
	
	public ServicioGreedy(Grafo g, int vInicial) {
		this.g = g;
		this.vInicial = vInicial;
		mejorRed = new GrafoNoDirigido();
		visitados = new ArrayList<Integer>();
		
		Iterator<Integer> vertices = g.obtenerVertices();
		mejorRed.agregarVertice(vInicial);
		visitados.add(vInicial);
	}
	
	public Grafo greedy() {
		
		Iterator<Arco> itArcos = g.obtenerArcos(vInicial);
		ArrayList<Arco> arcosDisponibles = new ArrayList<>();
		while(itArcos.hasNext()) {
			arcosDisponibles.add(itArcos.next());
		}
		
		while( visitados.size() < g.cantidadVertices() ) {
			
			Arco arco = seleccionar(arcosDisponibles.iterator());
			
			int verticeOrigen = arco.getVerticeOrigen();
            int verticeDestino = arco.getVerticeDestino();

				visitados.add(verticeDestino);
				//mejorRed.agregarVertice(verticeOrigen);
				mejorRed.agregarVertice(verticeDestino);
				mejorRed.agregarArco(verticeOrigen, verticeDestino, arco.getEtiqueta());
				
				Iterator<Arco<Integer>> arcosDestino = g.obtenerArcos(verticeDestino);
				arcosDisponibles.clear();
                while (arcosDestino.hasNext()) {
                    arcosDisponibles.add(arcosDestino.next());
                }
			
		// ACA LO QUE HAGO ES IR AGARRANDO LOS ARCOS DE CADA VERTICE Y TOMO EL DE MENOR VALOR QUE VAYA A UN VERTICE DESTINO NO VISITADO
        // EN REALIDAD NO TENDRIA QUE AGARRAR LOS ARCOS DE CADA VERTICE SINO SIEMPRE TODOS LOS ARCOS Y AGARRAR EL DE MENOR VALOR QUE VAYA A UN VERTICE NO VISITADO
        // SELEECIONAR AGARRARIA EL DE MENOR VALOR
        // FACTIBILIDAD COMPROBARIA QUE EL ARCO VAYA A UN VERTICE NO VISITADO
                
		}
		return mejorRed;
	}
	
	
	public Arco seleccionar( Iterator<Arco> arcos ) {
		Arco mejorArco = null;
		
		while( arcos.hasNext() ) {
			Arco arco = (Arco) arcos.next();
			if( !visitados.contains(arco.getVerticeDestino()) ) {
				if( mejorArco == null || (int)arco.getEtiqueta() < (int)mejorArco.getEtiqueta() )  {
					mejorArco = arco;
				}
			}
		}
		return mejorArco;
	}
	
	public boolean redCompleta(Grafo red) {
		UnionFind uf = new UnionFind(red.cantidadVertices());
	    Iterator<Arco> arcos = red.obtenerArcos();

	    while (arcos.hasNext()) {
	        Arco arco = arcos.next();
	        int origen = arco.getVerticeOrigen();
	        int destino = arco.getVerticeDestino();
	        uf.unir(origen, destino);
	    }

	    int primerVertice = (int)red.obtenerVertices().next();
	    int componenteInicial = uf.encontrar(primerVertice);

	    Iterator<Integer> vertices = red.obtenerVertices();
	    while (vertices.hasNext()) {
	        int vertice = vertices.next();
	        if (uf.encontrar(vertice) != componenteInicial) {
	            return false;
	        }
	    }

	    return true;
	}
	
}
