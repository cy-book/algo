package hz.xhxh.algo.graph.WeightedDigraph;

public class DirectedEdge implements Comparable<DirectedEdge>{
    private final double weight;
    private final int v;
    private final int w;

    public DirectedEdge(int from, int to, double weight){
        this.weight = weight;
        this.v = from;
        this.w = to;
    }

    public int from(){
        return v;
    }

    public int to(){
        return w;
    }

    public double weight(){
        return weight;
    }

    @Override
    public String toString(){
        return String.format("[%d -> %d, %.2f]",v,w,weight);
    }

    @Override
    public int compareTo(DirectedEdge edge){
        return Double.compare(this.weight,edge.weight);
    }
}