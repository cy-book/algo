package hz.xhxh.algo.graph.digraph;

public interface SCC {
    boolean stronglyConnected(int v, int w);

    int count();

    int id(int v);
}
