package hz.xhxh.algo.graph.digraph;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class DirectedDFSTest extends TestCase {
    public final int vertex = 2;
    public final String filename = "/digraph/tinyDG.txt";
    public void testDirectedDFSInSingleVertex(){
        var digraph = DigraphFactory.instance(filename);

        var dfs = new DirectedDFS(digraph,vertex);
        System.out.println("graph create from " + filename + "\n"
            + "designed vertices: " + vertex);
        IntStream.range(0,digraph.V()).filter(dfs::marked).forEach(v->System.out.printf("%d ",v));
    }

    public void testDirectedDFSInSource(){
        var digraph = DigraphFactory.instance(filename);
        var source = Arrays.asList(1,2,6);
        var dfs = new DirectedDFS(digraph, source);
        System.out.println("graph create from " + filename + "\n"
                + "source: " + source);
        IntStream.range(0,digraph.V()).filter(dfs::marked).forEach(v->System.out.printf("%d ",v));
    }

}