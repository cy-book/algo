package hz.xhxh.algo.collection.st;

import hz.xhxh.algo.collection.queue.SimpleQueue;

import java.util.NoSuchElementException;

public class ArrayST<K extends Comparable<K>, V> implements ComparableSymbolTable<K, V> {
    private static final int MIN_SIZE = 2;
    private K[] keys;
    private V[] values;
    int N;

    public ArrayST() {
        this(MIN_SIZE);
    }

    public ArrayST(int capacity){
        keys = (K[]) new Comparable[capacity];
        values = (V[]) new Object[capacity];
        N = 0;
    }

    @Override
    public void put(K k, V v) {
        if (null == k) throw new IllegalArgumentException("argument of key is null");

        if (null == v) {
            delete(k);
            return;
        }

        int i = rank(k);
        if (i < N && k.equals(keys[i])) {
            values[i] = v;
            return;
        }

        if (N == keys.length) resize(N * 2);

        for (int j = N - 1; j > i; j--) {
            values[j + 1] = values[j];
            keys[j + 1] = keys[j];
        }
        values[i] = v;
        keys[i] = k;
        N++;

    }

    @Override
    public V get(K k) {
        if (0 == N) return null;
        int i = rank(k);
        if (i < N && keys[i].equals(k)) return values[i];
        return null;
    }

    @Override
    public void delete(K k) {
        if (null == k) throw new IllegalArgumentException("要删除的键不能为空");
        if (0 == N) return;

        int i = rank(k);
        if (k.compareTo(keys[i]) != 0) return ;

        for (int j = i; j < N-1; j++) {
            values[j] = values[j + 1];
            keys[j] = keys[j + 1];
        }
        values[N-1] = null;
        keys[N-1] = null;
        N--;
        if (keys.length / 4 > N && N > MIN_SIZE) resize(N / 2);

    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public boolean contains(K k) {
        return k.compareTo(keys[rank(k)]) == 0;
    }

    @Override
    public boolean isEmpty() {
        return 0 == N;
    }

    @Override
    public K min() {
        if (0 == N) return null;
        return keys[0];
    }

    @Override
    public K max() {
        if (0 == N) return null;
        return keys[N - 1];
    }

    @Override
    public K floor(K key) {
        /*floor()得到的是小于或等于key的键
         * keys[rank()] 只有在key存在与表中时才 等价与 floor()
         * kye不存在表中时，rank()得到小于key的键的个数，所以keys[rank()]大于key
         *   此时如果小于key的键的个数为0, 则说明表中不存在符合条件的key可返回*/
        if (null == key) throw new IllegalArgumentException("键的值为空");
        int i = rank(key);
        if (i < N && key.compareTo(keys[i]) == 0) return keys[i];

        if (i == 0) return null;

        return keys[i - 1];
    }

    @Override
    public K ceiling(K key) {
        /*
         * ceiling() 与floor的求法类似，需注意表中是否有满足条件的key*/
        if (null == key) throw new IllegalArgumentException("键的值为空");

        int i = rank(key);
        if (i == N) return null;
        return keys[i];
    }

    @Override
    public int rank(K key) {
        /*
         * lo = 0, hi = N-1
         * while(lo<=hi)...
         * 两点需要注意
         * 一、hi = N-1
         * 上边界为最大值的索引，一种情况下，lo会不断的逼进hi,直到和它相等
         * 这时候需要判断的就是key和最大值的大小，所以索引不能超过N-1
         *
         * 二、while的判断语句为 lo <= hi
         * 首先列出几种到达lo==hi的情况
         *  （1） 因为mid = lo + (hi - lo)/2,所以 |lo-mid| <= |hi-mid|
         *       如果hi==lo上一步 为 lo+ 1 = hi 可以判断 key > [lo], 与[hi]的关系不知
         *           只能通过这一步 lo == mid == hi == val 时的情况判读 mid
         *
         *       回到本类要解决的问题，rank（）找到小于key的键的个数，对于不在数组里的key,这一步决定了key的位置
         *       等于val 或者小于val 或者 大于val。 如果提前结束，会造成key的值为mid 是不准确的
         *
         *  (2)     hi == lo 上一步 为 lo + 1 => mid +1 => hi
         *       此时 key 可能等于(hi|lo),所以一定要判断
         *
         * */
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }

        return lo;
    }

    @Override
    public K select(int k) {
        if (k < 0 || k >= N) throw new IndexOutOfBoundsException("k的大小超出类符号表中元素个数的范围");
        return keys[k];
    }

    @Override
    public void deleteMin() {
        if (0 == N) throw new NoSuchElementException("符号表中无元素");
        delete(keys[0]);
    }

    @Override
    public void deleteMax() {
        if (0 == N) throw new NoSuchElementException("符号表中无元素");
        delete(keys[N - 1]);
    }

    @Override
    public Iterable<K> keys() {
        var queue = new SimpleQueue<K>();
        for (int i = 0; i < N; i++) {
            queue.enqueue(keys[i]);
        }
        return queue;
    }

    @Override
    public Iterable<K> keys(K lo, K hi) {
        if (lo == null || hi == null) throw new IllegalArgumentException("符号表中不存在空键");
        var queue = new SimpleQueue<K>();

        if (lo.compareTo(hi) > 0) return queue;

        int l = rank(lo), h = rank(hi);
        for (int i = l; i < h; i++) {
            queue.enqueue(keys[i]);
        }
        if (hi.compareTo(keys[h]) == 0) queue.enqueue(keys[h]);

        return queue;
    }

    private void resize(int capacity) {
        K[] ks = (K[]) new Comparable[capacity];
        V[] vs = (V[]) new Object[capacity];

        System.arraycopy(keys, 0, ks, 0, N);
        System.arraycopy(values, 0, vs, 0, N);

        keys = ks;
        values = vs;
    }

}


