package hz.xhxh.algo.graph.udgraph;

import junit.framework.TestCase;

import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

public class BreadthFirstPathsTest extends TestCase {
    public void testBreadthFirstPaths() {
        Graph graph = getGraph();
        int s = 0;
        Paths paths = new BreadthFirstPaths(graph, s);
//        Paths paths = new DepthFirstPaths(graph,s);
        for (int i = 0; i < graph.V(); i++) {
            if (paths.hasPathTo(i)) {
                System.out.format("%d to %d: ", s, i);
                StringBuilder builder = new StringBuilder();
                for (int w : paths.pathTo(i)) {
                    builder.append(w).append(" -");
                }
                System.out.println(builder.deleteCharAt(builder.length() - 1));
            } else {
                System.out.println("not connected with " + s + " to " + i);
            }
        }
    }

    private Graph getGraph() {
        InputStream in = this.getClass().getResourceAsStream("/udgraph/tinyCG.txt");
        Scanner scanner = new Scanner(Objects.requireNonNull(in));
        Graph g = new AdjListGraph(scanner.nextInt());
        int E = scanner.nextInt();
        for (int i = 0; i < E; i++) {
            g.addEdge(scanner.nextInt(), scanner.nextInt());
        }
        System.out.println(g);
        return g;
    }

}