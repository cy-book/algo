package hz.xhxh.algo.collection.st;

public interface SymbolTable<Key, Value> {
    void put(Key k, Value v);

    Value get(Key k);

    default void delete(Key k){
        put(k,null);
    };

    int size();

    default boolean contains(Key k){
        return null != get(k);
    }

    default boolean isEmpty(){
        return 0 == size();
    }

    Iterable<Key> keys();
}
