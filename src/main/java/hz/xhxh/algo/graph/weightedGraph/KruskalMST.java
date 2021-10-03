package hz.xhxh.algo.graph.weightedGraph;

import hz.xhxh.algo.collection.queue.ArrayMinPQ;
import hz.xhxh.algo.collection.queue.Queue;
import hz.xhxh.algo.collection.queue.SimpleQueue;
import hz.xhxh.algo.uf.TreeUF;

public class KruskalMST implements MST{
    private final Queue<Edge> mst;
//    private final boolean[] marked;
    /*because the graph maybe has component, so didn't use
    * marked[v] && marked[w] to judge vertices v and w is connected;
    * so we will use UF;
    * */
    private double weight;

    public KruskalMST(WeightedGraph G){
//        this.marked = new boolean[G.V()];
        this.mst = new SimpleQueue<>();
        var uf = new TreeUF(G.V());
        var pq = new ArrayMinPQ<Edge>();
        for(Edge e : G.edges())pq.insert(e);

        Edge e;
        int v, w;
        while (!pq.isEmpty() && mst.size() < G.V()-1){
            e = pq.delMin();
            v = e.either(); w = e.other(v);
            if(uf.connected(v,w))continue;
            uf.union(v,w);
            mst.enqueue(e);

            weight += e.weight();
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
}
