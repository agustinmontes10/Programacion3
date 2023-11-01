package tpBacktracking;

import java.util.ArrayList;
import java.util.Iterator;

public class SubConjuntosEJ3 {
	
	public ArrayList<Integer> subConjuntoActual;
	public ArrayList<ArrayList<Integer>> subConjuntos;
	public ArrayList<Integer> conjunto;
	public ArrayList<Integer> agregados;
	public int m;
	public int c = 0;
	
	public SubConjuntosEJ3 ( ArrayList<Integer> conjunto, int m ) {
		subConjuntos = new ArrayList<ArrayList<Integer>>();
		subConjuntoActual = new ArrayList<>();
		agregados = new ArrayList<>();
		this.conjunto = conjunto;
		this.m = m;
	}
	
	public ArrayList<ArrayList<Integer>> buscarSubConjuntos() {
		back();
		return subConjuntos;
	}
	
	public void back() {
		c++;
		if( m == sumaActual() ) {
			System.out.println("m es igual a sumaActual");
			subConjuntos.add(new ArrayList<Integer>(subConjuntoActual));
			agregados.addAll(subConjuntoActual);
		}
		else {
			for( int i = 0; i < conjunto.size(); i++ ) {
				int valActual = conjunto.get(i);
				if( !agregados.contains(valActual) ) {
					subConjuntoActual.add(valActual);
					if( sumaActual() <= m ) {
						back();
					}
					subConjuntoActual.remove( subConjuntoActual.size()-1 );		// ES LO MISMO QUE REMOVER VAL-ACTUAL
				}
				
			}
			
			
		}
	}
	
	public int sumaActual() {
		int suma = 0;
		for (Integer num : subConjuntoActual) {
			suma += num;
		}
		return suma;
	}
	
}
