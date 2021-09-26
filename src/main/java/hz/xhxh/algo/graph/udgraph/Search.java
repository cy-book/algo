package hz.xhxh.algo.graph.udgraph;

/*
* 对图进行搜索，构造函数应为Search(Graph g,int v);
* 其中g为要搜索的图，v为一个特定点，接下来的方法都以
* v为基准.
* */
public interface Search {
    /*
    * 判断给定点t是否相对与v可达
    *
    * @param t 要达到的点t
    *
    * @return 如果v 可达到t ，返回true, 否则返回false
    * */
    boolean marked(int t);

    /*
    * 计算相对于v所有可达的点的个数
    *
    * @return 返回图中相对于点v可达点的个数
    * */
    int count();
}
