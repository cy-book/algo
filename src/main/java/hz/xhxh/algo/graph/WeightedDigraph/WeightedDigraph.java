package hz.xhxh.algo.graph.WeightedDigraph;

public interface WeightedDigraph {
    int V();

    int E();

    void addEdge(DirectedEdge edge);

    Iterable<DirectedEdge> adj(int v);

    Iterable<DirectedEdge> edges();

}
