package tpBacktracking;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import tpGrafos.Grafo;

public class ServicioCicloMayor {
	private Grafo grafo;
	private HashMap< Integer, String > visitados;
	private ArrayList<Integer> caminoParcial;
	private ArrayList<ArrayList<Integer>> ciclos;

	public ServicioCicloMayor(Grafo grafo) {
		this.grafo = grafo;
		this.visitados = new HashMap<Integer, String>();
		caminoParcial = new ArrayList<Integer>();
		ciclos = new ArrayList<ArrayList<Integer>>();
	}
	
	public List<Integer> obtenerCicloMayor() {
		Iterator<Integer> vertices = grafo.obtenerVertices();
		ArrayList<Integer> cicloMayor = new ArrayList<>();
		
		while(vertices.hasNext()) {
			visitados.put(vertices.next(), "blanco");
		}
		
		for (Integer v : visitados.keySet()) {
			if( visitados.get(v) == "blanco" ) {
				dfs_visit(v);
			}
		}
		

		for (ArrayList<Integer> ciclo: ciclos) {
			if(cicloMayor.size() == 0 || ciclo.size() > cicloMayor.size()) {
				 cicloMayor.clear();
				 cicloMayor.addAll(ciclo);
			}
		}
		
		System.out.println(ciclos);
		return cicloMayor;
	}
	
	
	public void dfs_visit(int v) {
		//ArrayList<Integer> solucionParcial = new ArrayList<>();
		visitados.put(v, "amarillo");
		caminoParcial.add(v);
		
		Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(v);
		while( adyacentes.hasNext() ) {
			Integer ady = adyacentes.next();
			if( visitados.get(ady) == "blanco" ) {
				dfs_visit(ady);
			}
			if( visitados.get(ady) == "amarillo" ) {
				ciclos.add(obtenerCiclo(caminoParcial, ady));
			}
		}
		
		visitados.put(v, "blanco");
		caminoParcial.remove(caminoParcial.size()-1);
	}
	
	public ArrayList<Integer> obtenerCiclo(ArrayList<Integer> caminoParcial, int ady) {
		ArrayList<Integer> ciclo = new ArrayList<Integer>();
		boolean entro = false;

		for (Integer v : caminoParcial) {
			if( !entro )  {
				if( v == ady ) entro = true;
			}
			if( entro ) {
				ciclo.add(v);
			}
		}
		return ciclo;
	}
	
}

