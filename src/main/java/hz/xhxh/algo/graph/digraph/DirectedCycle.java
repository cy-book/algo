package hz.xhxh.algo.graph.digraph;

import hz.xhxh.algo.collection.stack.SimpleStack;
import hz.xhxh.algo.collection.stack.Stack;

public class DirectedCycle {
    private final boolean[] marked ;
    private final int [] edgeTo;
    private final boolean[] onStack;
    private Stack<Integer> cycle;

    public DirectedCycle(Digraph G){
        this.marked = new boolean[G.V()];
        this.onStack = new boolean[G.V()];
        this.edgeTo = new int[G.V()];
        for(int i=G.V()-1; i>=0;i--){
            if(!marked[i] && null == cycle) {
//                edgeTo[i] = i;
                dfs(G,i);
            }
        }
    }

    private void dfs(Digraph g, int v){
        onStack[v] = true;
        marked[v] = true;
        for(int w : g.adj(v)){
            if(null != cycle) return;

            else if(!marked[w]){
                edgeTo[w] = v;
                dfs(g,w);
            }

            else if(onStack[w]){
                cycle = new SimpleStack<>();
                for(int x = v; x!= w ; x = edgeTo[x]){
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
//                System.out.format("%10s|%10s%n","edge","edgeTo");
//                for(int i = 0; i< g.V(); i++){
//                    System.out.format("%10d|%10d%n",i,edgeTo[i]);
//                }
            }
        }
        onStack[v]  = false;
    }

    public boolean hasCycle(){
        return null != cycle;
    }

    public Iterable<Integer> cycle(){
        return cycle;
    }


}
