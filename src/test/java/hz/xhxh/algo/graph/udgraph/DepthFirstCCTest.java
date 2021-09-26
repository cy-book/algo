package hz.xhxh.algo.graph.udgraph;

import hz.xhxh.algo.collection.queue.Queue;
import hz.xhxh.algo.collection.queue.SimpleQueue;
import junit.framework.TestCase;

public class DepthFirstCCTest extends TestCase {
    public void testDepthFirstCC() {
        Graph g = GraphReader.defaultGraph();
        CC cc = new DepthFirstCC(g);

        int m = cc.count();
        printf("components number: %d%n", m);

        Queue<Integer>[] queues = (Queue<Integer>[]) new Queue[m];
        for (int i = 0; i < m; i++) {
            queues[i] = new SimpleQueue<>();
        }
        for (int i = 0; i < g.V(); i++) {
            queues[cc.id(i)].enqueue(i);
        }

        for (int i = 0; i < m; i++) {
            printf("%d :[", i);
            for (int v : queues[i]) {
                printf("%d ", v);
            }
            printf("]%n");
        }
    }

    public static void printf(String format, Object... values) {
        System.out.printf(format, values);
    }
}