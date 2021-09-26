package hz.xhxh.algo.graph.digraph;

import hz.xhxh.algo.collection.queue.Queue;
import hz.xhxh.algo.collection.queue.SimpleQueue;
import hz.xhxh.algo.collection.stack.SimpleStack;
import hz.xhxh.algo.collection.stack.Stack;

public class DepthFirstOrder {
    private final boolean[] marked;
    private final Stack<Integer> reversePost;
    private final Queue<Integer> pre;
    private final Queue<Integer> post;
    public DepthFirstOrder(Digraph G) {
        this.marked = new boolean[G.V()];
        this.reversePost = new SimpleStack<>();
        this.pre = new SimpleQueue<>();
        this.post = new SimpleQueue<>();

        for(int v = 0; v<G.V(); v++){
            if(!marked[v]) dfs(G,v);
        }
    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        pre.enqueue(v);
        for (int w : g.adj(v)) {
            if (!marked[w]) dfs(g, w);
        }
        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }
}
