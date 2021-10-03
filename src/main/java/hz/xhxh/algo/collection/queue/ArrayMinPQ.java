package hz.xhxh.algo.collection.queue;

import java.util.Iterator;

public class ArrayMinPQ<Key extends Comparable<Key>> implements MinPQ<Key>{
    private final CmpPQ<Key> cmpPQ;
    private static final int INIT_SIZE = 4;

    public ArrayMinPQ(){
        this(INIT_SIZE);
    }

    public ArrayMinPQ(int capacity){
        this.cmpPQ = new CmpPQ<>(capacity, Comparable::compareTo);
    }

    @Override
    public void insert(Key k) {
        cmpPQ.insert(k);
    }

    @Override
    public Key min() {
       return cmpPQ.first();
    }

    @Override
    public Key delMin() {
        return cmpPQ.delete();
    }

    @Override
    public boolean isEmpty() {
        return cmpPQ.isEmpty();
    }

    @Override
    public int size() {
        return cmpPQ.size();
    }

    @Override
    public Iterator<Key> iterator() {
        return cmpPQ.iterator();
    }
}
