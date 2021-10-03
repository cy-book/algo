package hz.xhxh.algo.collection.queue;

import org.junit.Test;

import java.util.Random;

public class CmpPQTest {
    public static final int SIZE = 16;

    @Test
    public void testCmpPQ(){
        var pq = new CmpPQ<Integer>(SIZE, Integer::compareTo);
        new Random().ints(20,100).limit(SIZE-1).boxed().forEach(pq::insert);

        System.out.println(pq.size() + "\n");


        while(!pq.isEmpty()){
            System.out.println(pq.delete());
        }
    }
}
