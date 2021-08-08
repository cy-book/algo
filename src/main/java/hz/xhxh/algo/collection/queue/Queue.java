package hz.xhxh.algo.collection.queue;

public interface Queue<T> {
    void enqueue(T item);

    T dequeue();

    boolean isEmpty();

    int size();
}
