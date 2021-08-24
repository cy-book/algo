package hz.xhxh.algo.collection;

import hz.xhxh.algo.collection.queue.MaxPriorityQueue;
import hz.xhxh.algo.collection.queue.SimplePQ;
import org.junit.Test;

import java.util.stream.IntStream;

public class PriorityQueueTest {
    private static final int SIZE = 15;

    @Test
    public void testPriorityQueue(){
        MaxPriorityQueue<Integer> pq = new SimplePQ<>(SIZE);

        IntStream.range(20,100).limit(SIZE).boxed().forEach(pq::insert);

        System.out.println(pq.size() + "\n");

        while(!pq.isEmpty()){
            System.out.println(pq.delMax());
        }
    }
}
