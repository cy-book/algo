package hz.xhxh.algo.collection.stack;

public interface Stack<T> extends Iterable<T>{
    T pop();

    T peek();

    void push(T item);

    boolean isEmpty();

    int size();

}
