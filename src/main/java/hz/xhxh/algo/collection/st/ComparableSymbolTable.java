package hz.xhxh.algo.collection.st;

public interface ComparableSymbolTable<K extends Comparable<K>, V> extends SymbolTable<K ,V>{
    /*
    * 向符号表中插入一个键值对 {k,v},key不能时null ，如果符号表中没有这个k，则正常插入，
    * 否则，使用v替换调 原来符号表中与k对应的值
    * 如果所插入的值为null，则插入失败或者从符号表中删除原有键值对
    *
    * @param k 要插入的map的键
    * @param v 要插入的map的值
    *
    * @throw IllegalArgumentException 当k 为null时抛出异常
    * */
    void put(K k, V v);

    V get(K k);

    V delete(K k);

    int size();

    boolean contains(K k);

    boolean isEmpty();

    K min();

    K max();

    /*
    * 获得符号表中小于或等于给定key的最大的键
    *
    * @param key 要比较的键
    *
    * @return 小于或等于给定键的符号表中最大的键
    * */
    K floor(K key);

    /*
    * 获得符号表中大于或等于给定key的最小键
    *
    * @param key 要比较的键
    *
    * @return 大于或等于给定键的符号表中的最小键
    * */
    K ceiling(K key);

    /*
    * 给出符号表中小于key的键的数量
    *
    * @param key 要计算位置的键
    * @return 符号表中小于key的键的数量
    * */
    int rank(K key);

    K select(int k);

    void deleteMin();

    void deleteMax();

    Iterable<K> keys();

    Iterable<K> keys(K lo, K hi);
}
