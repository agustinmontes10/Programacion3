package tpGrafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class GrafoDirigido<T> implements Grafo<T> {

	private HashMap< Integer, ArrayList<Arco<T>> > vertices;
	private int cantArcos;
	
	public GrafoDirigido() {
		vertices = new HashMap< Integer, ArrayList<Arco<T>> >();
		cantArcos = 0;
	}
	
	/*	O(1) DEBIDO QUE PARA AGREGAR EN LA TABLA DE HASH ES O(1)
	 *  */
	@Override
	public void agregarVertice(int verticeId) {
		
		if( !contieneVertice(verticeId) ) 
			vertices.put( verticeId, new ArrayList<Arco<T>>() );
		
	}
	
	public GrafoDirigido<T> clonar() {
	    GrafoDirigido<T> grafoClonado = new GrafoDirigido<>();

	    // Clonar los vértices
	    for (Integer vertice : vertices.keySet()) {
	        grafoClonado.agregarVertice(vertice);
	    }

	    // Clonar los arcos
	    for (Integer vertice : vertices.keySet()) {
	        List<Arco<T>> arcos = vertices.get(vertice);
	        for (Arco<T> arco : arcos) {
	            grafoClonado.agregarArco(vertice, arco.getVerticeDestino(), arco.getEtiqueta());
	        }
	    }

	    return grafoClonado;
	}

	/* O(N*N) YA QUE DEBO RECORRER LOS VERTICES O(N) Y BORRAR ARCOS O(N) */
	@Override
	public void borrarVertice(int verticeId) {
		
		if( this.contieneVertice(verticeId) ) {
			Iterator<Integer> it = this.obtenerVertices();
			while( it.hasNext() ) {
				int verticeActual = it.next();
				if( verticeActual != verticeId ) {	//VOY BORRANDO TOODOS LOS ARCOS DE VERTICE ACTUAL HACIA VERTICE ID EN CASO DE QUE LOS TENGA
					this.borrarArco(verticeActual, verticeId);
				}
				else{	//si es el vértice a borrar, obtengo cuántos adyascentes tiene para restarlos a cantidadArcos
					int cantidadAdyacentes = this.vertices.get(verticeId).size();	//O(1)
					cantArcos = cantArcos - cantidadAdyacentes;
				}
			}
			this.vertices.remove(verticeId);
		}
	}

	
	/* O(N) DEBIDO A QUE EXISTEARCO() ES O(N) */
	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		
		if( this.contieneVertice(verticeId1) && this.contieneVertice(verticeId2) && !this.existeArco(verticeId1, verticeId2) ) {
			this.vertices.get(verticeId1).add( new Arco<T>(verticeId1, verticeId2, etiqueta)  );
			cantArcos++;
		}
		else {
			System.out.println("No se pudo agregar el arco");
		}

	}
	
	
	/* O(N) YA QUE DEBO RECORRER EL ARRAY DE ARCOS PARA BORRARLO */
	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		
		if( this.existeArco(verticeId1, verticeId2) ) {
			Arco <T> arco = new Arco<T>( verticeId1, verticeId2, null );
			this.vertices.get(verticeId1).remove(arco);
			cantArcos--;
		}

	}

	/* O(1) DEBIDO A LA TABLA DE HASH */
	@Override
	public boolean contieneVertice(int verticeId) {
		return vertices.containsKey(verticeId);
	}

	
	/* O(N) YA QUE RECORRE TODOS LOS ARCOS EN EL PEOR CASO */
	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		Arco <T> arco = new Arco<T>( verticeId1, verticeId2, null);
		return vertices.get(verticeId1).contains(arco);
	}

	/* O(N) YA QUE RECORRE TODOS LOS ARCOS EN EL PEOR CASO */
	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		Iterator<Arco<T>> itArcos = this.obtenerArcos(verticeId1);
		Arco<T> arcoBuscado = new Arco<T>(verticeId1, verticeId2, null);
		
		while( itArcos.hasNext() ) {
			Arco<T> arcoActual = itArcos.next();
			if( arcoActual.equals(arcoBuscado) ) {
				return arcoActual;
			}
		}
		return null;
	}

	
	/* O(1) */
	@Override
	public int cantidadVertices() {
		return vertices.keySet().size();
	}

	
	/* O(1) */
	@Override
	public int cantidadArcos() {
		return cantArcos;
	}

	
	/*	O(1) YA QUE HACE UN SOLO LLAMADO */
	@Override
	public Iterator<Integer> obtenerVertices() {
		return vertices.keySet().iterator();
	}


	/*O(N)
	 * YA QUE DEPENDE DE LA CANTIDAD DE ADYACENTES QUE TENGA EL VERTICE
	 * */
	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		Iterator<Arco<T>> itAdyacentes = this.vertices.get(verticeId).iterator();
		
		return new Iterator<Integer>() {
			@Override
	        public boolean hasNext() {
	            return itAdyacentes.hasNext();
	        }

	        @Override
	        public Integer next() {
	            return itAdyacentes.next().getVerticeDestino();
	        }
		};
	}

	
	/* O(N*N)
	 * DEBIDO A QUE RECORRE TODOS LOS VERTICES Y POR CADA VERTICE AGARRA SUS ARCOS Y  LOS AGREGA A ARCOS TOTALES
	 * 
	 *  */
	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		ArrayList<Arco<T>> arcosTotales = new ArrayList<Arco<T>>();
		
		for (Integer vertice : vertices.keySet()) {
			arcosTotales.addAll( vertices.get(vertice) );
		}
		
		return arcosTotales.iterator();
	}

	/*	O(1)  YA QUE HACE UN SOLO LLAMADO */
	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		return this.vertices.get(verticeId).iterator();
	}

}
