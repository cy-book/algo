package hz.xhxh.algo.graph.WeightedDigraph;

import hz.xhxh.algo.collection.bag.Bag;
import hz.xhxh.algo.collection.bag.SimpleBag;
import junit.framework.TestCase;

import java.util.Objects;
import java.util.Scanner;

import static org.junit.Assert.*;

public class DirectedEdgeTest extends TestCase {
    public void testDirectedEdge(){
        Scanner scan = new Scanner(Objects.requireNonNull(this.getClass().getResourceAsStream("/weightedDigraph/tinyEWD.txt")));

        scan.nextInt();
        scan.nextInt();

        Bag<DirectedEdge> bag = new SimpleBag<>();
        while(scan.hasNext()){
            int v = scan.nextInt();
            int w = scan.nextInt();
            double weight = scan.nextDouble();
            bag.add(new DirectedEdge(v,w,weight));
        }

        for(var e : bag){
            System.out.println(e);
        }
    }

}