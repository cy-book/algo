package hz.xhxh.algo.graph.digraph;

public abstract class AbstractDigraph implements Digraph {
    @Override
    public int degree(int v) {
        int count = 0;
        for (int w : adj(v)) {
            count++;
        }
        return count;
    }

    @Override
    public int maxDegree() {
        int max = 0, count = 0;
        for (int v = 0; v < V(); v++, count = 0) {
            for (int w : adj(v)) {
                count++;
            }
            if (max < count) max = count;
        }
        return max;
    }

    @Override
    public double avgDegree() {
        return 1.0 * E() / V();
    }

    @Override
    public int numberOfSelfLoops() {
        int count = 0;
        for (int v = 0; v < V(); v++) {
            for (int w : adj(v)) {
                if (w == v) count++;
            }
        }

        /*与无向图不同的是，有向图在添加边的时候只是一条有向边*/
        return count;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(V() + " vertices, " + E() + " edges\n");
        for (int v = 0; v < V(); v++) {
            s.append(v).append(": ");
            for (int w : this.adj(v)) s.append(w).append(" ");
            s.append("\n");
        }
        return "DirectedGraph{\n" + s.toString() + "}";
    }
}
