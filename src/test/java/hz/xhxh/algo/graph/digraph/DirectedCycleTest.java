package hz.xhxh.algo.graph.digraph;

import junit.framework.TestCase;

import static org.junit.Assert.*;

public class DirectedCycleTest extends TestCase {
    private final String filename1 = "/digraph/tinyDG.txt";
    private final String filename2 = "/digraph/tinyDAG.txt";

    public void testDirectedCycle(){
        var G1 = DigraphFactory.instance(filename1);
        var G2 = DigraphFactory.instance(filename2);

        var cycle1 = new DirectedCycle(G1);
        var cycle2 = new DirectedCycle(G2);

        if(cycle1.hasCycle()){
            System.out.format("%s has cycle:%n",filename1);
            cycle1.cycle().forEach(v -> System.out.printf("%d " , v));
            System.out.println();
        }

        if(cycle2.hasCycle()){
            System.out.format("%s has cycle:%n",filename2);
            cycle2.cycle().forEach(v -> System.out.printf("%d " , v));
            System.out.println();
        }

    }

}