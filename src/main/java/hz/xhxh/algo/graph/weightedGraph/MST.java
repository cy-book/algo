package hz.xhxh.algo.graph.weightedGraph;

/**
 * The {@code MST} interface represents a data type for computing a
 * <em> minimum spanning tree </em> in an edge-weighted-graph.
 * If the graph is not connected, it computes a <em>minimum spanning forest
 * </em>, which is the union of minimum spanning trees in each connected
 * component.
 *
 * @author cy-book
 * */
public interface MST {
    /**
     * iterable for MST edges*/
    Iterable<Edge> edges();

    /**
     * weight of MST*/
    double weight();

}
