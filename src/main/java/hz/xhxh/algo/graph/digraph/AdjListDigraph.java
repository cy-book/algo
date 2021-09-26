package hz.xhxh.algo.graph.digraph;

import hz.xhxh.algo.collection.bag.Bag;
import hz.xhxh.algo.collection.bag.SimpleBag;

public class AdjListDigraph extends AbstractDigraph {
    private int E;
    private final int V;
    private Bag<Integer>[] adj;

    public AdjListDigraph(int V) {
        this.V = V;
        this.E = 0;
        this.adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            this.adj[i] = new SimpleBag<>();
        }
    }

    @Override
    public int V() {
        return V;
    }

    @Override
    public int E() {
        return E;
    }

    /*向有向图中加入一条边 v -> w
     *
     * @param v 边的起始位置
     * @param w 边的终止位置
     * */
    @Override
    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    @Override
    public Digraph reverse() {
        Digraph digraph = new AdjListDigraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                digraph.addEdge(w, v);
            }
        }

        return digraph;
    }

    @Override
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}
