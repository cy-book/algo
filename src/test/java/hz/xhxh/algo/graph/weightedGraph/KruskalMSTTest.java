package hz.xhxh.algo.graph.weightedGraph;

import junit.framework.TestCase;

import static org.junit.Assert.*;

public class KruskalMSTTest extends TestCase {
    public void testKruskalMST(){
        var G = WeightedGraphFactory.instance("/weightedGraph/tinyEWG.txt");
        MST mst = new KruskalMST(G);
        for(Edge e : mst.edges()){
            System.out.println(e);
        }
        System.out.printf("%.5f\n", mst.weight());
    }

}

/*
[0-7, 0.16]
[2-3, 0.17]
[1-7, 0.19]
[0-2, 0.26]
[5-7, 0.28]
[1-3, 0.29]
[1-5, 0.32]
1.67000*/

/*[0-7, 0.16]
[1-7, 0.19]
[0-2, 0.26]
[2-3, 0.17]
[5-7, 0.28]
[4-5, 0.35]
[6-2, 0.40]
1.81000*/
