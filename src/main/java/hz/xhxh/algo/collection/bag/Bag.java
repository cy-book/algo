package hz.xhxh.algo.collection.bag;

public interface Bag<T> extends Iterable<T> {
    boolean add(T t);

    boolean isEmpty();

    int size();

    boolean remove (T t);

    boolean contains(Object o);

    Object[] toArray();
}
