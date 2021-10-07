package hz.xhxh.algo.graph.WeightedDigraph;


import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

public class WeightedDigraphFactory {
    public static WeightedDigraph instance(InputStream in){
        var scan = new Scanner(Objects.requireNonNull(in));
        int V = scan.nextInt();
        int E = scan.nextInt();
        WeightedDigraph G = new AdjListWeightedDigraph(V);

        int v, w;
        double weight;
        for(int i=0; i<E; i++){
            v = scan.nextInt();
            w = scan.nextInt();
            weight = scan.nextDouble();
            G.addEdge(new DirectedEdge(v,w,weight));
        }
        return G;
    }

    public static WeightedDigraph instance(String filename){
        return instance(WeightedDigraphFactory.class.getResourceAsStream(filename));
    }
}
