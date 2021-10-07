package hz.xhxh.algo.graph.WeightedDigraph;

import hz.xhxh.algo.collection.stack.SimpleStack;
import hz.xhxh.algo.collection.stack.Stack;

public class WeightedDirectedCycle {
    private final boolean [] marked;
    private final boolean [] onStack;
    private final DirectedEdge [] edgeTo;
    private Stack<DirectedEdge> cycle;

    public WeightedDirectedCycle(WeightedDigraph G){
        this.marked = new boolean[G.V()];
        this.onStack = new boolean[G.V()];
        this.edgeTo = new DirectedEdge[G.V()];

        for(int i=0 ;i <G.V(); i++){
            if(!marked[i] && null == cycle) dfs(G,i);
        }
    }


    public boolean hasCycle(){
        return null != cycle;
    }

    public Iterable<DirectedEdge> cycle(){
        return cycle;
    }

    private void dfs(WeightedDigraph G, int v){
        marked[v] = true;
        onStack[v] = true;
        for(var e : G.adj(v)){
            int w = e.to();
            if(!marked[w]){
                edgeTo[w] = e;
                dfs(G,w);
            }else if (onStack[w]){
                cycle = new SimpleStack<>();

                DirectedEdge f = e;
                while (f.from() != w) {
                    cycle.push(f);
                    f = edgeTo[f.from()];
                }
                cycle.push(f);

                return;
            }
        }
        onStack[v] = false;
    }



}
