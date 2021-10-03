package hz.xhxh.algo.collection.queue;

import hz.xhxh.algo.collection.queue.MaxPQ;
import hz.xhxh.algo.collection.queue.ArrayMaxPQ;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

public class PriorityQueueTest {
    private static final int SIZE = 15;

    @Test
    public void testPriorityQueue(){
        MinPQ<Integer> pq = new ArrayMinPQ<>(SIZE);
        var list = new ArrayList<Integer>();
        new Random().ints(20,1000).limit(SIZE*2).boxed().forEach(list::add);
        list.forEach(pq::enqueue);

        System.out.println(pq.size() + "\n");


        while(!pq.isEmpty()){
            System.out.println(pq.min());
            list.remove(pq.delMin());
        }

        System.out.println(pq.isEmpty());
    }
}
