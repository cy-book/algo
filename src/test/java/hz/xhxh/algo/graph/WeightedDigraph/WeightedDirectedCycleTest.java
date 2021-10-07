package hz.xhxh.algo.graph.WeightedDigraph;

import junit.framework.TestCase;

import static org.junit.Assert.*;

public class WeightedDirectedCycleTest extends TestCase {
    public void testWeightedDirectedCycle(){
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
            G.addEdge(new DirectedEdge(edges[i][0],edges[i][1],0.0));
        }
        System.out.println(G);
        var cycle = new WeightedDirectedCycle(G);
        System.out.println(cycle.hasCycle());
        System.out.println(cycle.cycle());
    }
}