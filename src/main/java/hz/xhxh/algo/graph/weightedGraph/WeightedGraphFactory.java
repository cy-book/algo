package hz.xhxh.algo.graph.weightedGraph;

import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

public class WeightedGraphFactory {
    /**Create a WeightedGraph from File.
     * The file in  $(resources) start with "/"
     * file has format like instance(InputStream )
     *
     * @param filename the weightedGraph format InputStream
     * @return WeightedGraph instance of create from the file
     * */
    public static WeightedGraph instance(String filename){
        var in = WeightedGraphFactory.class.getResourceAsStream(filename);
        return instance(in);
    }

    /**Create a WeightedGraph from InputStream.
     * The InputStream has data format which int value and
     * start two value is vertices number and edges number,
     * either integer is vertex to vertex to weighted express edge
     * they're size equals edges values.
     *
     * @param in the weightedGraph format InputStream
     *
     * @return WeightedGraph instance of create from the InputStream
     * */
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
