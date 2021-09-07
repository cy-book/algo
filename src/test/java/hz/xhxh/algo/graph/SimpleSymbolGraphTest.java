package hz.xhxh.algo.graph;

import hz.xhxh.algo.collection.st.ArrayST;
import hz.xhxh.algo.collection.st.ST;
import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class SimpleSymbolGraphTest extends TestCase {
    public void testSimpleSymbolGraph(){
        Scanner in = new Scanner(
                Objects.requireNonNull(
                        this.getClass().getResourceAsStream(
                                "/graph/routes.txt"))
        );
        ST<String,Integer> st = new ArrayST<>();
        while(in.hasNext()){
            String[] s = in.nextLine().split(" ");
            System.out.println(Arrays.toString(s));
            for (String value : s) {
                if (!st.contains(value)) st.put(value, st.size());
            }
        }
        SymbolGraph graph = new SimpleSymbolGraph(st.keys());
        in.close();
        in = new Scanner(
                Objects.requireNonNull(
                        this.getClass().getResourceAsStream(
                                "/graph/routes.txt"))
        );
        while(in.hasNext()){
            String[] s = in.nextLine().split(" ");
            graph.addEdge(s[0],s[1]);
        }
        System.out.println(graph.G());

        in.close();
        Iterable<String> i = Arrays.asList("JFK", "MCO", "LAX", "HHE");
        for(String source : i)
            if(graph.contains(source)){
                int s = graph.index(source);
                System.out.println(source);
                for(int w : graph.G().adj(s)){
                    System.out.println("    " + graph.name(w));
                }
            }else {
                System.out.println("input not contain '" + source + "'");
            }
        }

    }
