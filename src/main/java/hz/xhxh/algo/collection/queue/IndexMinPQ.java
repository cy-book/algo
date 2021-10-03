package hz.xhxh.algo.collection.queue;

public interface IndexMinPQ<Item extends Comparable<Item>> extends Iterable<Integer> {
    /**
     * Insert item, associate it with k
     *
     * @param k    index an index
     * @param item the key associate with index {@code k}
     */
    void insert(int k, Item item);

    /**
     * change the item associate with k to the special value
     * @param k the index of the item to change
     * @param item change the item associate with index {@code k} to this item
     * */
    void change(int k, Item item);

    /**
     * Return a minimal item.
     * @return The minimal item of the priority queue
     * */
    Item min();

    /**
     * Return a minimal item's index
     *
     * @return The minimal item's index of the priority queue
     * */
    int minIndex();

    /**
     * Delete the minimal item from the priority queue, and
     * return it's index
     *
     * @return the minimal item's index
     * */
    int delMin();

    /**
     * Delete an item it's index is k
     *
     * @param k the item's index of will are deleted
     * */
    void delete(int  k);

    /**
    * Return the number of items for the priority queue.
    *
    * @return the number of items for the priority queue.
    * */
    int size();

    /**
     * Is {@code k} an index on the priority queue?
     * @param k k an index
     * @return {@code true} if {@code k} an index on the priority queue
     *          {@code false} otherwise
     * */
    boolean contains(int k);

    /**
     * Is the priority queue empty?
     * @return {@code true} if the priority queue has one or more item.
     *          {@code false} the priority has not item.*/
    boolean isEmpty();
}
