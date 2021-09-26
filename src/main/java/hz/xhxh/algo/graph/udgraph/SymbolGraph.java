package hz.xhxh.algo.graph.udgraph;

/*
* 符号图用来将图的顶点与字符串关联起来
* 用于更加普遍的场景中
* */
public interface SymbolGraph {
    /*
    * 返回所给键在图中映射的顶点的索引的值
    *
    * @param key 图中顶点的字符串类型的值
    *
    * @return 这个顶点的索引值
    * */
    int index(String key);

    /*
    * 判断给定的字符串值的键在图中是否有对应的顶点
    *
    * @param key 一个字符串类型的值
    *
    * @return 如果给定键在符号表中，则返回true
    * */
    boolean contains(String key);

    /*返回给定图中顶点索引对应的字符值
    *
    * @param v 顶点索引
    *
    * @return 索引v对应的值
    * */
    String name(int v);

    /*向图中添加一条边 v-w
    *
    * @param v 顶点v
    *
    * @param w 顶点w
    * */
    void addEdge(String v, String w);

    /*获得符号图对应的图(顶点为整型)
    *
    * @return 符号图对应的图
    * */
    Graph G();
}
