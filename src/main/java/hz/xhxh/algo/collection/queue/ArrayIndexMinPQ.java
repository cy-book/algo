package hz.xhxh.algo.collection.queue;

import java.util.Iterator;

public class ArrayIndexMinPQ<Item extends Comparable<Item>> implements IndexMinPQ<Item> {
    private final int[] pq;
    private final int[] qp;
    private final int MAXIMUM;
    private final Item[] items;
    private int n;


    public ArrayIndexMinPQ(int maximum){
        this.pq = new int[maximum+1];
        this.qp = new int[maximum+1];
        this.MAXIMUM = maximum;
        this.items = (Item[]) new Comparable[MAXIMUM + 1];
        this.n = 0;

        for(int i=0; i<=maximum ;i++){
            qp[i] = -1;
        }
    }

    @Override
    public void insert(int k, Item item) {
        validateIndex(k);

//        if(qp[k] != -1) throw new IllegalArgumentException("index " + k + " is exists");
        if(qp[k] != -1) change(k,item);
        else {
            pq[ ++n ] = k;
            qp[k] = n;
            items[k] = item;
            swim(n);
            //System.out.format("insert: [%d(%d), %s], num: %d%n",k,qp[k],item,n);
        }
    }

    @Override
    public void change(int k, Item item) {
        validateIndex(k);
        if(-1 == qp[k]) return;

        var oldItem = items[k];
        items[k] = item;
        int cmp = item.compareTo(oldItem) ;
        if(cmp > 0) sink(qp[k]);
        else if(cmp < 0) swim(qp[k]);
//        System.out.format("change: [%d,%s  ->  %s]%n",k,oldItem,item);
    }

    @Override
    public Item min() {
        return items[pq[1]];
    }

    @Override
    public int minIndex() {
        return pq[1];
    }

    @Override
    public int delMin() {
        int index = pq[1];
        exchange(1,n--);
        sink(1);
        var it = items[index];
        items[index] = null;
        qp[index] = -1;
        pq[n+1] = -1;
//        System.out.format("delete: [%d(min): %s], num: %d%n",index,it,n);
        return index;
    }

    @Override
    public void delete(int k) {
        validateIndex(k);
        if(-1 == qp[k]) return;

        int i = qp[k];

        exchange(i,n--);
        swim(i);
        sink(i);

        items[k] = null;
        qp[k] = -1;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean contains(int k) {
        validateIndex(k);
        return -1 != qp[k] ;
    }

    @Override
    public boolean isEmpty() {
        return 0 == n;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new HeapIterator();
    }

    private void swim(int k){
        while(k > 1 && less(k,k/2)){
            exchange(k,k/2);
            k = k/2;
        }
    }

    private void sink(int k){
        int j;
        while (k*2 <= n ){
            j = k*2;
            if(j<n && less(j+1 ,j)) j++;
            if(less(k,j)) break;
            exchange(k,j);
            k = j;
        }
    }



    private boolean less(int k1, int k2){
        return items[pq[k1]].compareTo(items[pq[k2]]) < 0;
    }

    private void exchange(int k1, int k2){
        var tmp = pq[k1];
        pq[k1] = pq[k2];
        pq[k2] = tmp;
        qp[pq[k2]] = k2;
        qp[pq[k1]] = k1;
    }

    private void validateIndex(int k){
        if(k < 0) throw new IllegalArgumentException("index is negative: " + k);
        if(k >= MAXIMUM) throw new IllegalArgumentException("index >= capacity: " + k);
    }

    private class HeapIterator implements Iterator<Integer>{
        private final IndexMinPQ<Item> copy;

        public HeapIterator(){
            this.copy = new ArrayIndexMinPQ<>(n);
            for(int i=1; i<=n; i++){
                copy.insert(pq[i],items[pq[i]]);
            }
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public Integer next() {
            return copy.delMin();
        }
    }

}
