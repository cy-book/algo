package hz.xhxh.algo.graph.weightedGraph;

public class Edge implements Comparable<Edge>{
    private final int v;
    private final int w;
    private final double weight;

    public Edge(int v, int w, double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**@return either of this edge's vertices
     * */
    public int either(){
        return v;
    }

    /**
     * @param vertex the vertex
     *
     * @return the other vertex*/
    public int other(int vertex){
        if(v == vertex) return w;
        else if(w == vertex) return v;
        else throw new IllegalArgumentException("Illegal Endpoint");
    }

    /**weight of this edge
     * @return this edge's weight
     * */
    public double weight(){
        return weight;
    }

    /**compare this edge to e
     * @return return Double.compare(this.weight,edge.weight());
     * */
    @Override
    public int compareTo(Edge edge) {
        return Double.compare(this.weight,edge.weight);
    }

    @Override
    public String toString(){
        return  String.format("[%d-%d, %.2f]",v,w,weight);
    }
}
