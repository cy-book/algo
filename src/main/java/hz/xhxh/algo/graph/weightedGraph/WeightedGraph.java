package hz.xhxh.algo.graph.weightedGraph;

public interface WeightedGraph {
    /**
     * @return number of this weightedGraph vertices
     * */
    int V();

    /**
     * @return number of this weightedGraph edge
     * */
    int E();

    /**add edge to this weightedGraph
     *
     * @param edge the edge
     * */
    void addEdge(Edge edge);

    /**
     * @param v the vertices v
     *
     * @return edges incident to v*/
    Iterable<Edge>  adj(int v);

    /**
     * @return all of this weightedGraph's edges */
    Iterable<Edge> edges();

}
