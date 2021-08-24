package hz.xhxh.algo.collection;

import hz.xhxh.algo.collection.bag.Bag;
import hz.xhxh.algo.collection.bag.LinkedBag;
import hz.xhxh.algo.collection.bag.SimpleBag;
import org.junit.Test;

import java.util.Arrays;


public class BagTest {

    @Test
    public void testBag() {
        bagRemove();
    }

    private void bagRemoveUseIterator() {
        var bag = getBag();
        System.out.println(bag);
        // bag.removeIf(i -> Math.sqrt(i) % 2 == 0);
        System.out.println(bag);
    }

    private void bagRemove() {
        var bag = getBag();
        System.out.printf("bag contains 0:%b%n", bag.contains(0));
        System.out.println(bag);
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                bag.remove(i * i);
            }
        }
        System.out.println(bag);
        for (var i : bag) {
            System.out.printf(i + ", ");
        }

    }

    private void bagContains() {
        var bag = getBag();
        //var array = bag.toArray();
        // System.out.println(Arrays.toString(array));
        System.out.println(bag);
        //  for(Object i: array){
        //    if(!bag.contains(i)){
        //        System.out.printf("bag not contains %s%n",i.toString());
        //    }
        //}
    }

    private Bag<Integer> getBag() {
        Bag<Integer> bag = new SimpleBag<Integer>();
        for (int i = 0; i < 20; i++) bag.add(i * i);
        return bag;
    }


}
