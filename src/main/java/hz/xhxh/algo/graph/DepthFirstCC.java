package hz.xhxh.algo.graph;

public class DepthFirstCC implements CC {
    private int[] id;
    private boolean[] marked;
    private int count;

    public DepthFirstCC(Graph g) {
        this.id = new int[g.V()];
        this.marked = new boolean[g.V()];
        this.count = 0;

        for (int i = 0; i < g.V(); i++) {
            if (!marked[i]) dfs(g, i, count++);
        }
    }

    private void dfs(Graph g, int s, int level) {
        marked[s] = true;
        id[s] = level;
        for (int w : g.adj(s)) {
            if (!marked[w]) dfs(g, w, level);
        }

    }

    @Override
    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public int id(int v) {
        return id[v];
    }
}
