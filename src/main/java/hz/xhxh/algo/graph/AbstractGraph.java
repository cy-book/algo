package hz.xhxh.algo.graph;

public abstract  class AbstractGraph implements Graph {
    @Override
    public  int degree(int v) {
        int d = 0;
        for (int w : adj(v)) d++;
        return d;
    }

    @Override
    public  int maxDegree() {
        int max = 0;
        int d = 0;
        for (int v = 0; v < V(); v++, d = 0) {
            for (int w : adj(v)) d++;
            if (d > max) max = d;
        }
        return max;
    }

    @Override
    public  double avgDegree() {
        return 2.0 * E() / V();
    }

    @Override
    public  int numberOfSelfLoops() {
        int count = 0;
        for (int v = 0; v < V(); v++) {
            for(int w : adj(v)){
                if(w == v) count ++;
            }
        }
        return count/2;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder( V() + " vertices, " + E() + " edges\n");
        for(int v=0; v<V(); v++){
            s.append(v).append(": ");
            for(int w : this.adj(v)) s.append(w).append(" ");
            s.append("\n");
        }
        return "AbstractAdjListGraph{\n" + s.toString() + "}";
    }
}
