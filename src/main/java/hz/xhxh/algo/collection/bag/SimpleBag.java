package hz.xhxh.algo.collection.bag;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class SimpleBag<T> implements Bag<T> {
    private T[] bag;
    private int n;
    private Random random = new Random(47);

    public SimpleBag() {
        bag = (T[]) new Object[2];
        n = 0;
    }

    public SimpleBag(int capacity) {
        bag = (T[]) new Object[capacity];
        n = 0;
    }

    @Override
    public synchronized boolean add(T t) {
        if (n >= bag.length) resize(bag.length * 2);
        bag[n++] = t;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return 0 == n;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public synchronized boolean remove(T t) {
        if (isEmpty()) {
            throw new NoSuchElementException("bag is empty");
        }

        for (int i = 0; i < n; i++) {
            if (bag[i].equals(t)) {
                bag[i] = bag[n - 1];
                bag[n - 1] = null;
                n--;

                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        for (T t : bag) {
            if (t.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        var copy = new Object[n];
        System.arraycopy(bag, 0, copy, 0, n);
        return copy;
    }

    private void resize(int capacity) {
        assert capacity > n;

        var copy = (T[]) new Object[capacity];
        System.arraycopy(bag, 0, copy, 0, n);
        bag = copy;
    }

    @Override
    public Iterator<T> iterator() {
        return Arrays.stream(bag).limit(n).iterator();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < n; i++) {
            builder.append(bag[i]).append(", ");
        }
        //当背包为空时，builder长度为1
        if (builder.length() > 1) builder.delete(builder.length() - 2, builder.length());
        builder.append("]");
        return builder.toString();
    }

    public void shuffle() {
        T temp = null;
        for (int i = 0; i < n; i++) {
            int r = i + random.nextInt(n - i);
            temp = bag[i];
            bag[i] = bag[r];
            bag[r] = temp;
        }
    }

}
