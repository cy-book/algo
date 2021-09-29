package hz.xhxh.algo.collection.queue;

import java.util.Arrays;
import java.util.Iterator;

public class SimpleMaxPQ<T extends Comparable<T>> implements MaxPriorityQueue<T> {
    /*
    * root == heap[1];
    * parent(n) = floor(n/2);
    * child_left(n) = n * 2;
    * child_right(n) = n * 2 + 1;
    * */

    private T[] heap;
    private int N;
    private final int root = 1;
    public SimpleMaxPQ(int capacity){
        heap = (T[])new Comparable[capacity + 1];
        N = 0;
    }

    @Override
    public void insert(T v) {
        if(heap.length == N+1) resize(N * 2 + 1);
        heap[++ N ] = v;
        swim(N);
    }

    @Override
    public T max() {
        if(0 == N) throw new IndexOutOfBoundsException("队列为空");
        return heap[root];
    }

    @Override
    public T delMax() {
        if(0 == N) throw new IndexOutOfBoundsException("队列为空");
        T v = max();
        heap[root] = heap[N];
        /*
        * 注意下面三行代码的顺序 sink 要保证数组[0..N]没有空值
        * heap[N] = null; 会导致空值
        * N -- 避免了空值
        * 但要确保heap[N]被删除后再调用sink();
        * */
        heap[N] = null;
        N --;
        sink(root);

        if(N > 0 && N < heap.length/4)resize(heap.length /2 + 1);

        return v;
    }

    @Override
    public boolean isEmpty() {
        return 0 == N;
    }

    @Override
    public int size() {
        return N;
    }

    /*
    * 当某种情况下节点k大于它的父节点时候，堆有序被打破
    * 需要将节点k向上移动
    * 只需要不停的交换它和它的父节点，直到满足它不再大于它的父节点
    * 因为 父节点大于这棵子树的所有节点，移动过后父节点位置适合。
    * */
    private void swim(int k){
        while (k > 1 && less(k/2,k)){
            exchange(k/2,k);
            k = k/2;
        }
    }

    /*
    * 当某种情况下，节点k小于它的子节点的时候，堆有序被打破
    * 需要将节点k向下移动
    * 用子节点中较大的那个替换当前节点
    * */
    private void sink(int k){
        while(k*2 <= N){
            int j = k*2;
            if(j < N && less(j,j+1)) j++;
            if(!less(k,j)) break;
            exchange(k,j);
            k = j;
        }
    }

    private int parent(int k){
        return k/2;
    }

    private int child_left(int k){
        return k * 2;
    }

    private int child_right(int k){
        return k * 2 + 1;
    }

    private boolean less(int i, int j){
       // System.out.printf("[%d, %d]%n",i,j);
        return heap[i].compareTo(heap[j]) < 0;
    }

    private void exchange(int i,int j){
        var tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    private void resize(int capacity){
        assert capacity > N;

        T[] copy = (T[]) new Comparable[capacity];
        System.arraycopy(heap,0,copy,0,N+1);
        heap = copy;
    }

    @Override
    public Iterator<T> iterator() {
        return Arrays.stream(heap).iterator();
    }
}
