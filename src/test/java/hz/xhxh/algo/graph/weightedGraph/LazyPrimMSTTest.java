package hz.xhxh.algo.graph.weightedGraph;

import junit.framework.TestCase;

import static org.junit.Assert.*;

public class LazyPrimMSTTest extends TestCase {
    public void testLazyPrimMST(){
        var G = WeightedGraphFactory.instance("/weightedGraph/tinyEWG.txt");
        LazyPrimMST mst = new LazyPrimMST(G);
        for(Edge e : mst.edges()){
            System.out.println(e);
        }
        System.out.printf("%.5f\n", mst.weight());
    }

}