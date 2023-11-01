package tpGrafos;

public class UnionFind {
    private int[] parent;
    private int[] rank;

    public UnionFind(int size) {
        parent = new int[size+1];
        rank = new int[size+1];
        //System.out.println("length de parent: " + parent.length);
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int encontrar(int x) {
    	//System.out.println("x: " + x);
    	//System.out.println("parent[x]: " + parent[x]);
        if (x != parent[x]) {
            parent[x] = encontrar(parent[x]); // Compresión de ruta
        }
        return parent[x];
    }

    public void unir(int x, int y) {
        int raizX = encontrar(x);
        int raizY = encontrar(y);

        if (raizX != raizY) {
            if (rank[raizX] < rank[raizY]) {
                parent[raizX] = raizY;
            } else if (rank[raizX] > rank[raizY]) {
                parent[raizY] = raizX;
            } else {
                parent[raizY] = raizX;
                rank[raizX]++;
            }
        }
    }
    
  
    
}
