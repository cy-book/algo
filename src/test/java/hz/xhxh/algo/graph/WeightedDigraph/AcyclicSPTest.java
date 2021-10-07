package hz.xhxh.algo.graph.WeightedDigraph;

import junit.framework.TestCase;

import static org.junit.Assert.*;

public class AcyclicSPTest extends TestCase {
    public void testAcyclicSP(){
        var G = WeightedDigraphFactory.instance("/weightedDigraph/tinyEWDAG.txt");
        int s = 5;
        var sp = new AcyclicSP(G,s);
        for (int t = 0; t < G.V(); t++) {
            if (sp.hasPathTo(t)) {
                System.out.printf("%d to %d (%.2f)  ", s, t, sp.distTo(t));
                for (DirectedEdge e : sp.pathTo(t)) {
                    System.out.print(e + "   ");
                }
                System.out.println();
            }
            else {
                System.out.printf("%d to %d         no path\n", s, t);
            }
        }
    }

}