package tpBacktracking;

public class TableroMagicoEJ7 {

	int n;
	int k;
	int s;
	int[][] matriz;
	int c = 0;
	
	public TableroMagicoEJ7(int n, int k, int s) {
		this.n = n;
		this.k = k;
		this.s = s;
		matriz = new int[n][n];
	}
	
	
	public void back(int fActual, int cActual) {
		
		if( estaCompleto() && sumaCyF() ) {
			System.out.println("entro");
			imprimirMatriz();
			return;
		}
		else {
				
			while( tieneSiguiente(fActual, cActual) ) {
				// ACA IRIA LO QUE HACE SI NO CORTA
			}
		}
		
	}
	
	public boolean tieneSiguiente(int f, int c) {
		if( (c+1) < n) {
			return true;
		}
		if( (c+1) >= n ) {
			if( f+1 < n ) {
				return true;
			}
			if( f+1 >= n ) {
				return false;
			}
		}
		return false;
	}
	
	public boolean estaCompleto() {
		for(int f = 0; f < matriz.length; f++) {
			for(int c = 0; c < matriz.length; c++) {
				if( matriz[f][c] == 0 ) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean sumaCyF() {
		
		for(int f = 0; f < matriz.length; f++) {
			int sumaC = 0;
			for(int c = 0; c < matriz.length; c++) {
				sumaC += matriz[f][c];
			}
			if( sumaC != s ) {
				return false;
			}
		}
		
		for(int c = 0; c < matriz.length; c++) {
			int sumaF = 0;
			for(int f = 0; f < matriz.length; f++) {
				sumaF += matriz[f][c];
			}
			if( sumaF != s ) {
				return false;
			}
		}
		
		return true;
	}
	
	
	public int[][] imprimirMatriz() {
		return matriz;
	}
	
	
}
