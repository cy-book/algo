package hz.xhxh.algo.collection.queue;

/**
*   堆的定义： 一棵二叉树，它的每个节点都大于等于它的两个子节点，这样的树被称为有序堆
*   相应的，在堆有序的二叉树中，每个节点都小于它的父节点（有的话）
*
*   节点： 有三个指针，分别指向两个子节点和父节点
* */
public interface MaxPQ<T> extends Queue<T> {
    void insert(T v);

    T max();

    T delMax();

    @Override
    default void enqueue(T v){
        insert(v);
    }

    @Override
    default T dequeue(){
       return delMax();
    }
}
