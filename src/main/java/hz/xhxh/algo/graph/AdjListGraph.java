package hz.xhxh.algo.graph;

import hz.xhxh.algo.collection.bag.Bag;
import hz.xhxh.algo.collection.bag.SimpleBag;

public class AdjListGraph extends AbstractGraph{
    private final int V;
    private int E;
    private final Bag<Integer>[] adj;

    public AdjListGraph(int V){
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];
        for(int v = 0; v < V; v++){
            adj[v] = new SimpleBag<>();
        }
    }

    @Override
    public int E() {
        return this.E;
    }

    @Override
    public int V(){
        return this.V;
    }

    @Override
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E ++;
    }

    @Override
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

}
