package hz.xhxh.algo.graph.digraph;

import junit.framework.TestCase;

import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

public class AdjListDigraphTest extends TestCase {
    public void testAdjListDigraph(){
       var g = DigraphFactory.instance("/digraph/tinyDAG.txt");
        int V = g.V();
        System.out.println(g.V() + " vertices, " + g.E() + " edges");
        System.out.println(g);

        for (int i = 0; i < V; i++) {
            System.out.println(i + "{ " + g.adj(i) + " : degree " + g.degree(i) + "}");
        }

        System.out.println("maxDegree is " + g.maxDegree());


        System.out.println("selfLoop : " + g.numberOfSelfLoops());
    }
}