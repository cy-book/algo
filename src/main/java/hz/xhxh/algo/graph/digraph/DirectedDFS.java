package hz.xhxh.algo.graph.digraph;

/*Reachability of directed graph*/
public class DirectedDFS {
    private final boolean[] marked;

    /*
     * @param digraph the graph
     * @param v the designated vertices
     * */
    public DirectedDFS(Digraph digraph, int v) {
        this.marked = new boolean[digraph.V()];
        dfs(digraph, v);
    }

    /*
     * Computer the vertices in digraph that are
     * connected to any of the source vertices
     * @param digraph the graph
     * @param source the source vertices
     * */
    public DirectedDFS(Digraph digraph, Iterable<Integer> source) {
        this.marked = new boolean[digraph.V()];
        for (int v : source) {
            if (!marked[v]) dfs(digraph, v);
        }
    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) dfs(g, w);
        }
    }

    /*
     * @param w the vertex
     * @return if v is reachable from w then return true,
     *   else return false
     * */
    public boolean marked(int w) {
        return marked[w];
    }
}
