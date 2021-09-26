package hz.xhxh.algo.graph.digraph;

public class KosarajuSCC implements SCC {
    private final boolean[] marked;
    private final int[] id;
    private int count;

    public KosarajuSCC(Digraph G) {
        this.marked = new boolean[G.V()];
        this.id = new int[G.V()];
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());

        for(int v : order.reversePost()){
            if(!marked[v]) dfs(G,v,count++);
        }

    }

    private void dfs(Digraph g, int v,int level){
        marked[v] = true;
        id[v] = level;
        for(int w : g.adj(v)){
            if(!marked[w]) dfs(g,w,level);
        }
    }

    @Override
    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public int id(int v) {
        return id[v];
    }
}
