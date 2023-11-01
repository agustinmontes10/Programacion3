package tpBacktracking;

import java.util.ArrayList;

import tpGrafos.Grafo;
import tpGrafos.GrafoDirigido;

public class Main {
	
	public static void main(String[] args) {
		
		//TableroMagicoEJ7 tablero = new TableroMagicoEJ7(3, 15, 9);
		//tablero.back(0, 0);
		
		ArrayList<Integer> conjunto = new ArrayList<>();
		conjunto.add(5);
		conjunto.add(-10);
		conjunto.add(3);
		conjunto.add(7);
		conjunto.add(9);
		conjunto.add(2);
		conjunto.add(1);
		conjunto.add(19);
		// [6, 9, 4, 3, 1, 10]
		SubConjuntosEJ3 subConjuntos = new SubConjuntosEJ3( conjunto, 9 );
		System.out.println( subConjuntos.buscarSubConjuntos() );
		
		/*Grafo g = new GrafoDirigido();
		g.agregarVertice(1);
		g.agregarVertice(2);
		g.agregarVertice(3);
		g.agregarVertice(4);
		g.agregarVertice(5);
		g.agregarVertice(6);
		g.agregarVertice(7);
		g.agregarArco(1, 2, null);
		g.agregarArco(1, 3, null);
		g.agregarArco(3, 2, null);
		g.agregarArco(2, 6, null);
		g.agregarArco(3, 4, null);
		g.agregarArco(3, 5, null);
		g.agregarArco(3, 6, null);
		g.agregarArco(6, 5, null);
		g.agregarArco(5, 7, null);
		CaminoLargoEJ1 caminoLargo = new CaminoLargoEJ1(g);
		System.out.println( caminoLargo.encontrarCamino(1, 7) );*/
		
		
	}

}
