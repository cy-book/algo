package hz.xhxh.algo.collection.queue;

/**
 * The {@code MinPQ} interface represents a priority queue of generic keys.
 *
 * @author cy-book
 * */

public interface MinPQ<Key> extends Queue<Key> {
    void insert(Key k);

    Key min();

    Key delMin();

    @Override
    default void enqueue(Key k){
        insert(k);
    }

    @Override
    default Key dequeue(){
        return delMin();
    }

}
