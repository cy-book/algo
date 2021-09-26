package hz.xhxh.algo.graph.udgraph;

public abstract class AbstractGraph implements Graph {
    @Override
    public int degree(int v) {
        int d = 0;
        for (int w : adj(v)) d++;
        return d;
    }

    @Override
    public int maxDegree() {
        int max = 0;
        int d = 0;
        for (int v = 0; v < V(); v++, d = 0) {
            for (int w : adj(v)) d++;
            if (d > max) max = d;
        }
        return max;
    }

    @Override
    public double avgDegree() {
        return 2.0 * E() / V();
    }

    @Override
    public int numberOfSelfLoops() {
        int count = 0;
        for (int v = 0; v < V(); v++) {
            for (int w : adj(v)) {
                if (w == v) {
                    System.out.println("edge: " + w + "->" + v);
                    count++;
                }
            }
        }
        //每条边会被统计两次，意思是
        // 加入一条自环等于向这个顶点加入了两个相邻的点(它自身)
        return count / 2;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(V() + " vertices, " + E() + " edges\n");
        for (int v = 0; v < V(); v++) {
            s.append(v).append(": ");
            for (int w : this.adj(v)) s.append(w).append(" ");
            s.append("\n");
        }
        return "Graph{\n" + s.toString() + "}";
    }
}
