package hz.xhxh.algo.graph.WeightedDigraph;

import junit.framework.TestCase;

import static org.junit.Assert.*;

public class BellmanFordSPTest extends TestCase {
    public void testBellmanFord(){
        var G = WeightedDigraphFactory.instance("/weightedDigraph/tinyEWD.txt");
        int s = 0;
        var sp = new BellmanFordSP(G,s);
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
    public void test2(){
        int V = 7;
        int E = 8;
        int[][] edges = {
                {0,1},
                {1,2},
                {2,3},
                {4,5},
                {5,0},
                {4,1},
                {3,6},
                {6,1}
        };
        var G = new AdjListWeightedDigraph(V);
        for(int i=0; i<E; i++){
            G.addEdge(new DirectedEdge(edges[i][0],edges[i][1],-0.1));
        }
        int s = 0;
        BellmanFordSP sp = new BellmanFordSP(G,s);
        System.out.println(sp.hasNegativeCycle());
        System.out.println(sp.negativeCycle());
        for(int v=0; v<G.V(); v++){
            if(sp.hasPathTo(v))System.out.printf("has path : %d -> %d%n",s, v);
        }
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