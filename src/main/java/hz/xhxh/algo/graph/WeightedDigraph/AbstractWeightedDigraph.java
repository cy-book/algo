package hz.xhxh.algo.graph.WeightedDigraph;

import hz.xhxh.algo.graph.weightedGraph.Edge;

public abstract class AbstractWeightedDigraph implements WeightedDigraph{
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(String.format("WeightedDigraph :  V: %d, E: %d%n",V(),E()));
        for(int v=0; v<V(); v++){
            s.append(v).append(": ");
            for(var edge : adj(v)){
                s.append(edge).append(" ");
            }
            s.append("\n");
        }

        return s.toString();
    }
}
