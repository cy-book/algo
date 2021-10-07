package hz.xhxh.algo.graph.WeightedDigraph;

import hz.xhxh.algo.collection.stack.SimpleStack;
import hz.xhxh.algo.collection.stack.Stack;
import hz.xhxh.algo.graph.digraph.DepthFirstOrder;

public class AcyclicSP implements SP{
    private final double[] distTo;
    private final DirectedEdge[] edgeTo;

    public AcyclicSP(WeightedDigraph G, int s){
        this.distTo = new double[G.V()];
        this.edgeTo = new DirectedEdge[G.V()];

        for(int i=0; i< G.V(); i++){
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        for(int v :new DepthFirstOrder(G).reversePost()){
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
        if(!hasPathTo(v)) return null;
        Stack<DirectedEdge> paths = new SimpleStack<>();
        for(int w=v; edgeTo[w] != null; w = edgeTo[w].from()){
            paths.push(edgeTo[w]);
        }

        return paths;
    }

    private void relax(WeightedDigraph G, int v){
        for(DirectedEdge e : G.adj(v)){
            int w = e.to();
            if(distTo[w] > distTo[v] + e.weight()){
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
            }
        }
    }
}
