package hz.xhxh.algo.collection;

import hz.xhxh.algo.collection.queue.MaxPriorityQueue;
import hz.xhxh.algo.collection.queue.SimplePQ;
import org.junit.Test;

import java.util.Random;

public class PriorityQueueTest {
    private static final int SIZE = 15;

    @Test
    public void testPriorityQueue(){
        MaxPriorityQueue<Integer> pq = new SimplePQ<>(SIZE);
        new Random().ints(20,100).limit(SIZE*2).boxed().forEach(pq::enqueue);

        System.out.println(pq.size() + "\n");


        while(!pq.isEmpty()){
            System.out.println(pq.delMax());
        }
    }
}
