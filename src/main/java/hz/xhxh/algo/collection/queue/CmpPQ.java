package hz.xhxh.algo.collection.queue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class CmpPQ<Key> {
    private int N;
    private final int root = 1;
    private final Comparator<Key> comparator;
    private Key[] heap;

    public CmpPQ(int capacity, Comparator<Key> comp){
        if(null == comp) throw new NullPointerException("param comparator is not  allow null");
        this.comparator = comp;
        this.heap = (Key[]) new Object[capacity+1];
        this.N = 0;
    }

    public void insert(Key k){
        if(heap.length == N+1) resize(N * 2 + 1);
        heap[++ N] = k;
        swim(N);
    }

    public Key delete(){
        if(0 == N) throw new IndexOutOfBoundsException("队列为空");
        Key v = first();
        heap[root] = heap[N];

        heap[N] = null;
        N --;
        sink();

        if(N > 0 && N < heap.length/4)resize(heap.length /2 + 1);

        return v;
    }

    public Key first(){
        if(0 == N) throw new IndexOutOfBoundsException("队列为空");
        return heap[root];
    }

    public boolean isEmpty(){
        return 0 == N;
    }

    public int size(){
        return N;
    }

    public Iterator<Key> iterator() {
        return Arrays.stream(heap).iterator();
    }

    private void swim(int k){
        while (k > 1 && cmp(k/2,k)){
            exchange(k/2,k);
            k = k/2;
        }
    }

    private void sink(){
        int k = 1;
        while(k*2 <= N){
            int j = k*2;
            if(j < N && cmp(j,j+1)) j++;
            if(!cmp(k,j)) break;
            exchange(k,j);
            k = j;
        }
    }

    private boolean cmp(int k1 , int k2){
        return comparator.compare(heap[k1], heap[k2]) > 0;
    }



    private void exchange(int i, int j){
        var tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    private void resize(int capacity){
        assert capacity > N;

        Key[] copy = (Key[]) new Object[capacity];
        System.arraycopy(heap,0,copy,0,N+1);
        heap = copy;
    }

}
