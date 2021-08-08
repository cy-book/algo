package hz.xhxh.algo.uf;

public class TreeUF implements UF{
    private final int[] parent;
    private final int[] rank;
    private int count;

    public TreeUF(int n){
        this.count = n;
        this.parent = new int[n];
        this.rank = new int[n];

        for(int i=0; i<n; i++){
            parent[i] = i;
            rank[i] = 0;
        }
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if(pRoot == qRoot) return;

        if(rank[pRoot] < rank[qRoot]) parent[pRoot] = qRoot;
        else if (rank[pRoot] > rank[qRoot]) parent[qRoot] = pRoot;
        else {
            parent[pRoot] = qRoot;
            rank[q] ++;
        }

        count --;
    }

    @Override
    public int find(int p) {
        int tmp = p;
        while(parent[tmp] != tmp){
            tmp = parent[tmp];
        }
        return tmp;
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int count() {
        return count;
    }
}
