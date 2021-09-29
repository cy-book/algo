package hz.xhxh.algo.graph.digraph;

import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

public class DigraphFactory {
    public static Digraph instance(String filename){
        InputStream in = DigraphFactory.class.getResourceAsStream(filename);
        return instance(in);
    }

    public static Digraph instance(InputStream in){
        var scan = new Scanner(Objects.requireNonNull(in));

        int V = scan.nextInt();
        int E = scan.nextInt();
        Digraph g = new AdjListDigraph(V);

        int v ,w;
        for (int i = 0; i < E; i++) {
            v = scan.nextInt();
            w = scan.nextInt();
            g.addEdge(v,w);
        }

        return g;
    }
}
