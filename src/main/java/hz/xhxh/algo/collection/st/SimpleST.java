package hz.xhxh.algo.collection.st;

public class SimpleST<K,V> implements SymbolTable<K,V>{
    @Override
    public void put(K k, V v) {

    }

    @Override
    public V get(K k) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterable<K> keys() {
        return null;
    }

    public static void main(String...args){
        SymbolTable<String, String> st = new SimpleST<>();
        st.contains("1");
    }
}
