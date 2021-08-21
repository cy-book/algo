package hz.xhxh.algo.collection.st;

public interface SymbolTable<Key, Value> {
    void put(Key k, Value v);

    Value get(Key k);

    Value delete(Key k);

    int size();

    boolean contains(Key k);

    boolean isEmpty();

    Iterable<Key> keys();
}
