package hz.xhxh.algo.graph.weightedGraph;

import hz.xhxh.algo.collection.bag.Bag;
import hz.xhxh.algo.collection.bag.SimpleBag;

public class AdjListWeightedGraph extends AbstractWeightedGraph{
    private final int V;
    private int E;
    private Bag<Edge>[] edges;

    /**Initialize an empty edge-weighted graph with {@code V} vertices and 0 edges
     *
     * @param V the number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     * */
    public AdjListWeightedGraph(int V){
        if(V <0) throw new IllegalArgumentException("Number of vertices must be not negative");
        this.V = V;
        this.E = 0;
        this.edges = (Bag<Edge>[]) new Bag[V];
        for(int i=0; i<V; i++){
            this.edges[i] = new SimpleBag<>();
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

    /**add the undirected edge {@code edge} to this edges-weighted graph
     *
     * @param edge the edge
     * @throws IllegalArgumentException unless both endpoints are between {@code 0} and {@code v-1}
     * */
    @Override
    public void addEdge(Edge edge) {
        int v = edge.either();
        int w = edge.other(v);
        validateVertex(v);
        validateVertex(w);
        this.edges[v].add(edge);
        this.edges[w].add(edge);
        this.E ++ ;
    }

    @Override
    public Iterable<Edge> adj(int v) {
        validateVertex(v);
        return edges[v];
    }

    @Override
    public Iterable<Edge> edges() {
        Bag<Edge> list = new SimpleBag<>(E);
        for(int v = 0; v < V; v++){
            int selfLoop = 0;
            for(Edge e : adj(v)){
                if(e.other(v) < v) list.add(e);
                else if(e.other(v) == v){
                    if(selfLoop %2 == 0)list.add(e);
                    selfLoop ++;
                }
            }
        }

        return list;
    }

    /**Validate the v if it beyond V or less 0
     *
     * @throws IllegalArgumentException unless {@code 0 <= v < V}*/
    private void validateVertex(int v){
        if(v < 0 || v >= V()) throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (v-1));
    }

}
