package hz.xhxh.algo.graph.udgraph;

import hz.xhxh.algo.collection.queue.Queue;
import hz.xhxh.algo.collection.queue.SimpleQueue;
import hz.xhxh.algo.collection.stack.SimpleStack;
import hz.xhxh.algo.collection.stack.Stack;

public class BreadthFirstPaths implements Paths{
    private int[] edgeTo;
    private boolean[] marked;
    private int s;

    public BreadthFirstPaths(Graph g, int s){
        this.s = s;
        this.edgeTo = new int[g.V()];
        this.marked = new boolean[g.V()];
        bfp(g,s);
    }

    private void bfp(Graph g, int v){
        Queue<Integer> queue = new SimpleQueue<>();
        queue.enqueue(v);
        marked[v] = true;
        int t;
        while(!queue.isEmpty()){
            t = queue.dequeue();
            for(int w : g.adj(t)){
                if(!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = t;
                    queue.enqueue(w);
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    @Override
    public Iterable<Integer> pathTo(int v) {
        if(!marked[v]) return null;
        Stack<Integer> stack = new SimpleStack<>();
        for(int x=v; x != edgeTo[x]; x = edgeTo[x]){
            stack.push(x);
        }
        stack.push(s);

        return stack;
    }
}
