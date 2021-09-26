package hz.xhxh.algo.graph.udgraph;

/*
* CC == connected components
* 用来处理图，找出图中所有的连通分量
* */
public interface CC {
    /*
    * 判断图中两个点 v , w 是否连通
    * @param v 顶点v
    * @param w 顶点w
    *
    * @return 如果w和v连通，返回ture,否则返回false
    * */
    boolean connected(int v, int w);

    /*
    * 计算图中所有连通分量的个数
    *
    * @return 一幅图中的连通分量个数
    * */
    int count();

    /*
    * 求出一个顶点在这个图中所在的连通分量的标识符
    * @param v 顶点v
    *
    * @return 顶点v所在的连通分量标识符
    * */
    int id(int v);
}
