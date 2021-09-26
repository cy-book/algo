package hz.xhxh.algo.graph.digraph;

import org.junit.Test;


public class DepthFirstOrderTest {
    private final DepthFirstOrder order ;
    public DepthFirstOrderTest(){
        var G = DigraphFactory.instance("/digraph/tinyDAG.txt");
        order = new DepthFirstOrder(G)  ;
    }

    @Test
    public void testDepthFirstOrder(){
        pre();
        post();
        reversePost();
    }


    public void pre() {
        System.out.println("pre: ");
        order.pre().
                forEach(v -> System.out.print(v + " "));
        System.out.println();

    }

    public void post() {
        System.out.println("post:");
        order.post().
                forEach(v -> System.out.print(v + " "));
        System.out.println();
    }

    public void reversePost() {
        System.out.println("reversePost: ") ;
        order.reversePost().
                forEach(v -> System.out.print(v + " "));
        System.out.println();
    }
}