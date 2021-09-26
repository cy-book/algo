package hz.xhxh.algo.graph.udgraph;

public interface Graph {
    /*获取图中顶点的个数
    *
    * @return 返回图中的顶点个数
    * */
    int V();

    /*
    * 获取图中边的个数
    *
    * @return 返回图中的边的个数
    * */
    int E();

    /*
    * 向图中添加一条边
    *
    * @param v 边的一个顶点
    * @param m 边的另一个顶点
    * */
    void addEdge(int v, int w);

    /*
    * 获得顶点v 的所有邻接顶点的遍历
    *
    * @param v 要获取遍历顶点的一个顶点
    *
    * @return 返回顶点v的邻接顶点的遍历
    * */
    Iterable<Integer> adj(int v);

    /*
    * 计算顶点v的度数，即与v相邻的顶点的个数
    *
    * @param v 顶点v
    *
    * @return 返回顶点v的度数
    * */
    int degree(int v);

    /*
    * 计算图中度数最大的顶点的度数
    *
    * @return 返回图中度数最大的顶点的度数
    * */
    int maxDegree();

    /*
    * 计算图中所有顶点度数的平均值
    *
    * @return 返回图中所有顶点的平均值
    * */
    double avgDegree();

    /*
    * 计算图中自环的个数
    *
    * @return 返回图中自环的个数
    * */
    int numberOfSelfLoops();

}
