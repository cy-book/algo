package hz.xhxh.algo.graph.WeightedDigraph;

import hz.xhxh.algo.collection.queue.ArrayIndexMinPQ;
import hz.xhxh.algo.collection.queue.IndexMinPQ;
import hz.xhxh.algo.collection.stack.SimpleStack;
import hz.xhxh.algo.collection.stack.Stack;

/**
 * The {@code DijkstraSP} class is an implements of {@code} SP for solving
 * the single-source shortest path problem in edge-weighted digraphs where
 * the edge weights are not negative.
 */
public class DijkstraSP implements SP {
    private final double[] distTo;
    private final DirectedEdge[] edgeTo;
    private final IndexMinPQ<Double> pq;

    public DijkstraSP(WeightedDigraph G,int s) {
        for (var e : G.edges()) {
            if (e.weight() < 0) throw new IllegalArgumentException("edge " + e + " has negative weight");
        }
        this.distTo = new double[G.V()];
        this.edgeTo = new DirectedEdge[G.V()];


        for(int i=0 ; i< G.V(); i++){
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        pq = new ArrayIndexMinPQ<Double>(G.V());
        pq.insert(s,distTo[s]);

        while (!pq.isEmpty()){
            var v = pq.delMin();
            for(var e : G.adj(v)) relax(e);
        }
    }


    @Override
    public double distTo(int v) {
        return distTo[v];
    }

    @Override
    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    @Override
    public Iterable<DirectedEdge> pathTo(int v) {
        if(!hasPathTo(v)) return null;
        Stack<DirectedEdge> paths = new SimpleStack<>();
        for(int w=v; edgeTo[w] != null; w = edgeTo[w].from()){
            paths.push(edgeTo[w]);
        }

        return paths;
    }

    private void relax(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if(!pq.contains(w)) pq.insert(w,distTo[w]);
            else pq.change(w,distTo[w]);
        }
    }

}
