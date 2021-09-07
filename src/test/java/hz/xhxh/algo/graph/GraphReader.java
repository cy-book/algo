package hz.xhxh.algo.graph;

import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

public class GraphReader {
    public static Graph reader(String name){
        InputStream in = GraphReader.class.getResourceAsStream(name);
        Scanner scanner = new Scanner(Objects.requireNonNull(in));
        Graph g = new AdjListGraph(scanner.nextInt());
        int E = scanner.nextInt();
        for(int i=0; i<E; i++){
            g.addEdge(scanner.nextInt(),scanner.nextInt());
        }

        return g;
    }

    public static Graph defaultGraph(){
        return reader("/graph/tinyG.txt");
    }
}
