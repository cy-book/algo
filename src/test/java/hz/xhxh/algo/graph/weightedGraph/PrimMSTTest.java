package hz.xhxh.algo.graph.weightedGraph;

import junit.framework.TestCase;

import static org.junit.Assert.*;

public class PrimMSTTest extends TestCase {
    public void testPrimMST(){
        var G = WeightedGraphFactory.instance("/weightedGraph/tinyEWG.txt");
        MST mst = new PrimMST(G);
        for(Edge e : mst.edges()){
            System.out.println(e);
        }
        System.out.printf("%.5f\n", mst.weight());

    }

}