package hz.xhxh.algo.graph.WeightedDigraph;

import hz.xhxh.algo.collection.bag.Bag;
import hz.xhxh.algo.collection.bag.SimpleBag;

public class AdjListWeightedDigraph extends AbstractWeightedDigraph{
    private final int V;
    private int E;
    private final Bag<DirectedEdge>[] edges;

    public AdjListWeightedDigraph(int V){
        this.V = V;
        this.E = 0;
        this.edges =(Bag<DirectedEdge>[]) new Bag[V];
        for(int i=0; i<V; i++){
            edges[i] = new SimpleBag<>();
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

    @Override
    public void addEdge(DirectedEdge edge) {
        edges[edge.from()].add(edge);
        this.E++;
    }

    @Override
    public Iterable<DirectedEdge> adj(int v) {
        return edges[v];
    }

    @Override
    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> list = new SimpleBag<>();
        for(int i=0; i<V; i++){
            for(var e  : edges[i]){
                list.add(e);
            }
        }
        return list;
    }
}
