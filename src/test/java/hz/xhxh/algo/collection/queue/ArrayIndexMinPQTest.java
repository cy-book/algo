package hz.xhxh.algo.collection.queue;

import junit.framework.TestCase;

import static org.junit.Assert.*;

public class ArrayIndexMinPQTest extends TestCase {
    public void testArrayIndexMinPQ(){
        String[] strings = { "it", "was", "the", "best", "of", "times", "it", "was", "the", "worst" };

        IndexMinPQ<String> pq = new ArrayIndexMinPQ<>(strings.length);
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        // delete and print each key
        for (int i=0; i<strings.length ;i++){
            int v = pq.delMin();
            System.out.println(v + " " + strings[v] + "++++");
        }
        System.out.println();

        // reinsert the same strings
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        // print each key using the iterator
        for (int i : pq) {
            System.out.println(i + " " + strings[i]);
        }
        while (!pq.isEmpty()) {
            pq.delMin();
        }

    }

}