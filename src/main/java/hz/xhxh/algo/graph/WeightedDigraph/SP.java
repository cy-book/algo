package hz.xhxh.algo.graph.WeightedDigraph;

/**
 * The{@code SP} interface represents a data type for solving the
 * single-source shortest paths problem in edge-weighted digraphs.
 */
public interface SP {
    /**
     * Returns the length of a shortest path from the source vertex
     * {@code s} to vertex.
     *
     * @param v the destination vertex
     * @return the length of a shortest path from the source vertex
     *      {@code s} to vertex {@code v} */
    double distTo(int v);

    /**
     * Returns true if there is a path from the source vertex {@code s}
     * to vertex {@code v}
     *
     * @param v the destination vertex
     * @return {@code true} if there is a path from the source vertex
     * {@code s} to vertex {@code v};
     *          {@code false} otherwise
     * */
    boolean hasPathTo(int v);

    /**
     * Returns a shortest path from the source vertex {@code s} to vertex
     * {@code v}
     *
     * @param v the destination vertex
     * @return a shortest path from the source vertex {@code s} to vertex
     * {@code v}
     * */
    Iterable<DirectedEdge> pathTo(int v);
}
