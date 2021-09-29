package hz.xhxh.algo.graph.weightedGraph;

import junit.framework.TestCase;

import static org.junit.Assert.*;

public class WeightedGraphTest extends TestCase {
    public void testWeightedGraph(){
        String file = "/weightedGraph/tinyEWG.txt";
        var G = WeightedGraphFactory.instance(file);

        System.out.println(G);
    }

}