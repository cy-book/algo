package hz.xhxh.algo.graph.udgraph;

import junit.framework.TestCase;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Scanner;

public class AdjListGraphTest extends TestCase {
    public void test() throws FileNotFoundException, URISyntaxException {
        InputStream in = this.getClass().getResourceAsStream("/udgraph/tinyG.txt");
        Scanner scanner = new Scanner(Objects.requireNonNull(in));
        Graph g = new AdjListGraph(scanner.nextInt());
        int E = scanner.nextInt();
        for (int i = 0; i < E; i++) {
            g.addEdge(scanner.nextInt(), scanner.nextInt());
        }

        System.out.println(g.V() + " vertices, " + g.E() + " edges");
        System.out.println(g);

        for (int i = 0; i < E; i++) {
            System.out.println(i + "{ " + g.adj(i) + " : degree " + g.degree(i) + "}");
        }

        System.out.println("maxDegree is " + g.maxDegree());


        System.out.println("selfLoop : " + g.numberOfSelfLoops());

    }

}