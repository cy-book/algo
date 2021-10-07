package hz.xhxh.algo.graph.weightedGraph;

public abstract class AbstractWeightedGraph implements WeightedGraph {
    /**Returns a string representation of the edge-weighted graph
     * This method takes time proportional to E + V
     *
     * @return the number of vertices V , followed by the number of edges E
     *         followed by the V adjacency lists of edges*/
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(String.format("WeightedGraph :  V: %d, E: %d%n",V(),E()));
        for(int v=0; v<V(); v++){
            s.append(v).append(": ");
            for(Edge edge : adj(v)){
                s.append(edge).append(" ");
            }
            s.append("\n");
        }

        return s.toString();
    }

}
