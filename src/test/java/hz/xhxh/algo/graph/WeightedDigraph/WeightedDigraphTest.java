package hz.xhxh.algo.graph.WeightedDigraph;

import junit.framework.TestCase;

import static org.junit.Assert.*;

public class WeightedDigraphTest extends TestCase {
    public void testWeightedDigraph(){
        var G = WeightedDigraphFactory.instance("/weightedDigraph/tinyEWD.txt");
        System.out.println(G);
    }

}