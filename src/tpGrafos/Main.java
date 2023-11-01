package tpGrafos;

import java.util.Iterator;

public class Main {

	public static void main(String[] args) {
		
		
		
	
		String path = "src/tpGrafos/dataset1.txt";
		CSVReader reader = new CSVReader(path);
		Grafo g = reader.read(new GrafoNoDirigido<>());
		Timer t1 = new Timer();
		
		ServicioGreedy servicioGreedy = new ServicioGreedy(g, 1);
		t1.start();
		Grafo mejorRed = servicioGreedy.greedy();
	
	
		//ServicioBacktracking servicioBack = new ServicioBacktracking(g);
		//Grafo mejorRed = servicioBack.back();
		
		System.out.println("tiempo en ms: " + t1.stop());
		Iterator<Arco> arcos = mejorRed.obtenerArcos();
		System.out.println("-----");
		

		int suma = 0;
		while( arcos.hasNext() ) {
			Arco arco = arcos.next();
			System.out.println( arco );
			suma += (int)arco.getEtiqueta();
		}
		
		System.out.println("cant metros: " + suma/2);
	
	
		
		
		
		
	/*	TRABAJO PRACTICO 1
	 * 
	 * 	Grafo g = new GrafoDirigido();
		
		g.agregarVertice(1);
		g.agregarVertice(4);
		g.agregarVertice(7);
		g.agregarVertice(3);
		g.agregarVertice(9);
		g.agregarVertice(10);
		g.agregarVertice(5);
		g.agregarVertice(8);
		
		g.agregarArco(1, 4, null);							//	(1) ------> (7) ------> (9)
 		g.agregarArco(1, 7, null);							//   |	        ^ \			 |
		g.agregarArco(4, 3, null);							//	 |	       /    \		 |
		g.agregarArco(4, 7, null);							//   |	    /          \     |
		g.agregarArco(10, 3, null);							//   v	/               -->  v
		g.agregarArco(7, 9, null);							//	(4) ------> (3) ------> (10)
		g.agregarArco(7, 10, null);							//		<------	 |	<------
		g.agregarArco(3, 10, null);							//               |
		g.agregarArco(9, 10, null);							//				 v
		g.agregarArco(3, 5, null);							//              (5) ------> (8)
		g.agregarArco(5, 8, null);
		g.agregarArco(3, 4, null);	*/	// [[1, 4, 3, 10], [1, 4, 7, 9, 10], [1, 4, 7, 10], [1, 7, 9, 10], [1, 7, 10]]
		
		
		//ServicioDFS dfs = new ServicioDFS(g);
		//System.out.println( dfs.dfsForest() );
	
		//ServicioBFS bfs = new ServicioBFS(g);
		//System.out.println( bfs.bfsForest() );
		
		//ServicioCaminos caminos = new ServicioCaminos(g, 1, 10, 3);
		//System.out.println( caminos.encontrarCaminos() );
		
		//ServicioCaminoLargo caminoLargo = new ServicioCaminoLargo(g);
		//System.out.println( caminoLargo.encontrarCaminoLargo(1, 8) );
		
		//ServicioCamino camino = new ServicioCamino(g);
		//System.out.println( camino.encontrarCamino(9, 7) );
		

	}

}
