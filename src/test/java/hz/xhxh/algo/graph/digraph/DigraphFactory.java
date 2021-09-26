package hz.xhxh.algo.graph.digraph;

import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

public class DigraphFactory {
    public static Digraph instance(String filename){
        InputStream in = DigraphFactory.class.getResourceAsStream(filename);
        Scanner scanner = new Scanner(Objects.requireNonNull(in));
        int V = scanner.nextInt();
        int E = scanner.nextInt();
        Digraph g = new AdjListDigraph(V);
        for (int i = 0; i < E; i++) {
            g.addEdge(scanner.nextInt(), scanner.nextInt());
        }

        return g;
    }
}
