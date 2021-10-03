package hz.xhxh.algo.graph.weightedGraph;

import hz.xhxh.algo.collection.queue.ArrayIndexMinPQ;
import hz.xhxh.algo.collection.queue.IndexMinPQ;
import hz.xhxh.algo.collection.queue.Queue;
import hz.xhxh.algo.collection.queue.SimpleQueue;

public class PrimMST implements MST{
    private final Edge[] edgeTo ;
    private final double[] distTo;
    private final boolean[] marked;
    private final IndexMinPQ<Double> pq;
    private double weight;

    public PrimMST(WeightedGraph G){
        this.edgeTo = new Edge[G.V()];
        this.distTo = new double[G.V()];
        this.marked = new boolean[G.V()];
        this.pq = new ArrayIndexMinPQ<>(G.V());
        for(int i=0; i<G.V(); i++){
            distTo[i] = Double.MAX_VALUE;
        }

        for(int v=0; v<G.V(); v++){
            if(!marked[v])visit(G,v);
        }
    }

    @Override
    public Iterable<Edge> edges() {
        Queue<Edge> mst = new SimpleQueue<>();
        for (Edge edge : edgeTo) {
            if (edge != null) {
                mst.enqueue(edge);
            }
        }
        return mst;
    }

    @Override
    public double weight() {
        return weight;
    }

    private void visit(WeightedGraph G, int v){
        distTo[v] = 0.0;
        pq.insert(v,distTo[v]);
        while(!pq.isEmpty()){
            scan(G,pq.delMin());
        }
    }

    private void scan(WeightedGraph G, int v){
        marked[v] = true;
        weight += distTo[v];
        int w ;
        for(Edge e : G.adj(v)){
            w = e.other(v);
            if(marked[w]) continue;
            if(e.weight() < distTo[w]){
                distTo[w] = e.weight();
                edgeTo[w] = e;
                if(pq.contains(w)) pq.change(w,distTo[w]);
                else               pq.insert(w,distTo[w]);
            }
        }
    }
}
