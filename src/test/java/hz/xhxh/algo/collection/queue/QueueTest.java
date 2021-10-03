package hz.xhxh.algo.collection.queue;

import hz.xhxh.algo.collection.queue.SimpleQueue;
import org.junit.Test;

import java.util.Arrays;

public class QueueTest {
    @Test
    public void testQueue(){
        var queue = new SimpleQueue<Integer>();
        for(int i=0;i<20;i++){
            queue.enqueue(i);
        }
        System.out.format("queue size is %d%n",queue.size());

        while (!queue.isEmpty()){
            System.out.format("dequeue: %d, after size is %d%n",queue.dequeue(),queue.size());
        }
    }
}
