package hz.xhxh.algo.graph.weightedGraph;

import hz.xhxh.algo.collection.queue.ArrayMinPQ;
import hz.xhxh.algo.collection.queue.MinPQ;
import hz.xhxh.algo.collection.queue.Queue;
import hz.xhxh.algo.collection.queue.SimpleQueue;

/**
 * The {@code LazyPrimMST} class is an implements of MST.
 * It accept a WeightedGraph which edge weights can be positive, zero, or
 * negative and need not be distinct.
 */
public class LazyPrimMST implements MST {
    private final boolean[] marked;
    private final MinPQ<Edge> pq;
    private final Queue<Edge> mst;
    private double weight;

    public LazyPrimMST(WeightedGraph G) {
        this.marked = new boolean[G.V()];
        this.pq = new ArrayMinPQ<>();
        this.mst = new SimpleQueue<>();
        this.weight = 0;

        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                visit(G, v);
            }
        }
    }

    @Override
    public Iterable<Edge> edges() {
        return mst;
    }

    @Override
    public double weight() {
        return weight;
    }

    private void visit(WeightedGraph g, int v) {
        scan(g, v);
        Edge e;
        int v1, v2;
        while (!pq.isEmpty()) {
            e = pq.delMin();
            v1 = e.either();
            v2 = e.other(v1);
            if (marked[v1] && marked[v2]) continue;
            mst.enqueue(e);
            weight += e.weight();
            if (!marked[v1]) scan(g, v1);
            if (!marked[v2]) scan(g, v2);
        }
    }

    private void scan(WeightedGraph graph, int v) {
        marked[v] = true;
        for (Edge edge : graph.adj(v)) {
            pq.insert(edge);
        }
    }
}
