package hz.xhxh.algo.graph.weightedGraph;

import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

public class WeightedGraphFactory {
    public static WeightedGraph instance(String filename){
        var in = WeightedGraphFactory.class.getResourceAsStream(filename);
        return instance(in);
    }

    public static WeightedGraph instance(InputStream in){
        var scan = new Scanner(Objects.requireNonNull(in));
        int V = scan.nextInt();
        int E = scan.nextInt();
        WeightedGraph G = new AdjListWeightedGraph(V);

        int v, w;
        double weight;
        for(int i=0; i<E; i++){
            v = scan.nextInt();
            w = scan.nextInt();
            weight = scan.nextDouble();
            G.addEdge(new Edge(v,w,weight));
        }
        return G;
    }
}
