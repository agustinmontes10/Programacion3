package tpGrafos;

import java.util.Iterator;

public class ServicioBacktracking {

	private Grafo g;
	private Grafo mejorRed;
	private Grafo redActual = new GrafoNoDirigido<>();
	public int metrica = 0;
	
	public ServicioBacktracking(Grafo g) {
		this.g = g;
		this.mejorRed = new GrafoNoDirigido<>();
	
	}
	
	public Grafo back() {
		Iterator<Integer> vertices = g.obtenerVertices();
		while(vertices.hasNext()) {
			int v = vertices.next();
			redActual.agregarVertice(v);
		}
 		
		backtracking( redActual );
		return mejorRed;
	}
	
	public void backtracking( Grafo redActual) {
		metrica++;
		if( redCompleta( redActual ) ) {
			if( cantMetros(mejorRed) == 0 || cantMetros(redActual) < cantMetros(mejorRed) ) {
				mejorRed = redActual.clonar();
			}
		}
		else {
			Iterator <Arco> arcosSiguientes = g.obtenerArcos();
			
			while( arcosSiguientes.hasNext() ) {
				Arco arcoSig = arcosSiguientes.next();
				int origen = arcoSig.getVerticeOrigen();
				int destino = arcoSig.getVerticeDestino();
				int valor = (int)arcoSig.getEtiqueta();
				
					if( !redActual.existeArco(origen, destino) ) {
						
						redActual.agregarArco(origen, destino, valor);
						if(cantMetros(mejorRed) == 0 || cantMetros(redActual) < cantMetros(mejorRed)) {

							backtracking( redActual );
	
						}
						
						redActual.borrarArco(origen, destino);
				}
			}
			
		}
	}
	
	public boolean redCompleta(Grafo redActual) {
	    UnionFind uf = new UnionFind(redActual.cantidadVertices());
	    Iterator<Arco> arcos = redActual.obtenerArcos();

	    while (arcos.hasNext()) {
	        Arco arco = arcos.next();
	        int origen = arco.getVerticeOrigen();
	        int destino = arco.getVerticeDestino();
	        uf.unir(origen, destino);
	    }

	    int primerVertice = (int)redActual.obtenerVertices().next();
	    int componenteInicial = uf.encontrar(primerVertice);

	    Iterator<Integer> vertices = redActual.obtenerVertices();
	    while (vertices.hasNext()) {
	        int vertice = vertices.next();
	        if (uf.encontrar(vertice) != componenteInicial) {
	            return false;
	        }
	    }

	    return true;
	}
	
	public int cantMetros( Grafo g ) {
		int total = 0;
		Iterator<Arco> arcos = g.obtenerArcos();
		
		while( arcos.hasNext() ) {
			total += (int)arcos.next().getEtiqueta();
			
		}
		
		return total;
	}
	
}
