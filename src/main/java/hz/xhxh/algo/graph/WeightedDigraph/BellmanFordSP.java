package hz.xhxh.algo.graph.WeightedDigraph;

import hz.xhxh.algo.collection.queue.Queue;
import hz.xhxh.algo.collection.queue.SimpleQueue;
import hz.xhxh.algo.collection.stack.SimpleStack;
import hz.xhxh.algo.collection.stack.Stack;

public class BellmanFordSP implements SP{
    private final double [] distTo;
    private final DirectedEdge[] edgeTo;
    private final Queue<Integer> queue;
    private final boolean [] onQueue;
    private int cost;
    private Iterable<DirectedEdge> cycle;

    public BellmanFordSP(WeightedDigraph G, int s){
        this.distTo = new double[G.V()];
        this.edgeTo = new DirectedEdge[G.V()];
        this.queue = new SimpleQueue<>();
        this.onQueue = new boolean[G.V()];

        for(int i=0; i< G.V(); i++){
            distTo[i] = Double.POSITIVE_INFINITY;
        }

        distTo[s] = 0.0;
        queue.enqueue(s);
        onQueue[s] = true;

        while(! queue.isEmpty() && !hasNegativeCycle()){
            int v = queue.dequeue();
            onQueue[v] = false;
            relax(G,v);
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
        if(hasNegativeCycle())
            throw new IllegalArgumentException("Negative cost cycle exists");
        if(!hasPathTo(v)) return null;
        Stack<DirectedEdge> paths = new SimpleStack<>();
        for(int w=v; edgeTo[w] != null; w = edgeTo[w].from()){
            paths.push(edgeTo[w]);
        }

        return paths;
    }

    public boolean hasNegativeCycle(){
        return null != cycle;
    }

    public Iterable<DirectedEdge> negativeCycle(){
        return cycle;
    }

    private void relax(WeightedDigraph G, int v){
        for(var e : G.adj(v)){
            int w = e.to();
            if(distTo[w] > distTo[v] + e.weight()){
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if(!onQueue[w]){
                    onQueue[w] = true;
                    queue.enqueue(w);
                }
            }

            if(cost ++ % G.V() == 0){
                findNegativeCycle();
                if(hasNegativeCycle()) return;
            }
        }
    }

    private void findNegativeCycle(){
        WeightedDigraph spt = new AdjListWeightedDigraph(edgeTo.length);
        for(var edge : edgeTo){
            if(edge != null) spt.addEdge(edge);
        }

        var finder = new WeightedDirectedCycle(spt);
        this.cycle = finder.cycle();
    }


}

