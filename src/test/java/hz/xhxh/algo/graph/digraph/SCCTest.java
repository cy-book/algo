package hz.xhxh.algo.graph.digraph;

import hz.xhxh.algo.collection.queue.Queue;
import hz.xhxh.algo.collection.queue.SimpleQueue;
import junit.framework.TestCase;

import static org.junit.Assert.*;

public class SCCTest extends TestCase {
    private String filename = "/digraph/tinyDG.txt";
    private String filename2 = "/digraph/mediumDG.txt";

    public void testSCC(){
        var G = DigraphFactory.instance(filename2);
        var scc = new KosarajuSCC(G);

        int m = scc.count();
        System.out.println("strong components: " + m);

        Queue<Integer>[] components = (Queue<Integer>[])new Queue[m];
        for(int i=0; i< m; i++){
            components[i] = new SimpleQueue<>();
        }

        for(int v = 0; v< G.V(); v++){
            components[scc.id(v)].enqueue(v);
        }

        for(int i = 0 ; i< m; i++){
            System.out.printf("components %2d: ",i);
            for(int w : components[i]){
                System.out.printf("%d ",w);
            }
            System.out.println();
        }
    }

}