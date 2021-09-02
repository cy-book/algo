package hz.xhxh.algo.graph;

import hz.xhxh.algo.collection.queue.SimpleQueue;
import hz.xhxh.algo.collection.stack.SimpleStack;
import hz.xhxh.algo.collection.stack.Stack;

public class DepthFirstPaths implements Paths{
    private final int s ; //起点
    private final boolean[] marked; //标记可达的点
    private final int[] edgeTo;   // 给出到达一个点的上一个位置

    public DepthFirstPaths(Graph g, int s){
        this.s = s;
        this.marked = new boolean[g.V()];
        this.edgeTo = new int[g.V()];
        dfp(g,s);
    }

    private void dfp(Graph g,int v){
        marked[v] = true;

        for(int w : g.adj(v)){
            if(!marked[w]) {
                edgeTo[w] = v;
                dfp(g, w);
            }
        }
    }

    @Override
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    @Override
    public Iterable<Integer> pathTo(int v) {
        if(!marked[v]) return new SimpleQueue<>();
        Stack<Integer> stack = new SimpleStack<>();
        for(int x=v; edgeTo[x] != x; x = edgeTo[x]){
            stack.push(x);
        }
        stack.push(s);

        return stack;
    }
}
