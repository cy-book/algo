package hz.xhxh.algo.str.regex;

import hz.xhxh.algo.collection.bag.Bag;
import hz.xhxh.algo.collection.bag.SimpleBag;
import hz.xhxh.algo.collection.stack.SimpleStack;
import hz.xhxh.algo.collection.stack.Stack;
import hz.xhxh.algo.graph.digraph.AdjListDigraph;
import hz.xhxh.algo.graph.digraph.Digraph;
import hz.xhxh.algo.graph.digraph.DirectedDFS;

public class NFA {
    private final Digraph graph;
    private final char[] re;
    private final int m;

    public NFA(String regexp){
        this.re = regexp.toCharArray();
        this.m = regexp.length();
        Stack<Integer> ops = new SimpleStack<>();
        graph = new AdjListDigraph(m+1);

        for(int i=0; i<m ;i++){
            int lp =  i;
            if('(' == re[i] || '|' == re[i]){
                ops.push(i);
            }
            else if(')' == re[i]){
                int or = ops.pop();
                if('|' == re[or]){
                    lp = ops.pop();
                    graph.addEdge(or,i);
                    graph.addEdge(lp,or+1);
                }else if('(' == re[or]){
                    lp = or;
                }else assert false;
            }

            if(i < m-1 && '*' == re[i+1]){
//                graph.addEdge(i,i+1);
//                graph.addEdge(i+1,i);
                /*A closure (*) operator must occur either (i ) after a single character,
                when we add e-transitions to and from the character, or (ii ) after a
                right parenthesis, when we add e-transitions to and from the corresponding
                left parenthesis, the one at the top of the stack.*/
                graph.addEdge(lp,i+1);
                graph.addEdge(i+1,lp);
            }

            if('(' == re[i] || ')' == re[i] || '*' == re[i]){
                graph.addEdge(i,i+1);
            }
        }

        if(ops.size() != 0){
            throw new IllegalArgumentException("Invalid regular expression: " + regexp);
        }
    }

    public boolean recognizes(String txt){
        /*区分pc 和 match ：
        * 自动机通过'('来表示开始状态，这时开始初始化pc, 它表示下一步所有可达的状态
        * （初始化时的状态转由换通过正则表达式构造出来图来寻找下一个状态，因为第一个
        * 字符为'(')。接着就进入正式匹配环节。
        * 文本第一个字符如果匹配成功（和pc中的状态 s),那么下一步就可以到达s+1状态，
        * 这时候可以加入match了，它也表示下一步可匹配的状态集合，但是match是由正则表达式
        * 和文本字符匹配成功后产生的，不包括可以×继续×通过匹配空字符可以到达的状态。
        * 而graph表示的正是所有可以通过匹配空字符可到达的状态，就对match进行深度优先搜索，
        * 找到了下一步可达的状态全集。*/
        Bag<Integer> pc = new SimpleBag<>();
        DirectedDFS dfs = new DirectedDFS(graph,0);
        for(int i=0 ;i<graph.V(); i++){
            if(dfs.marked(i))pc.add(i);
        }
        for(int i=0; i<txt.length(); i++){
            if('|' == txt.charAt(i) || '*' == txt.charAt(i) || '(' == txt.charAt(i) || ')' == txt.charAt(i)){
                throw new IllegalArgumentException();
            }
            Bag<Integer> match = new SimpleBag<>();
            for(int s : pc){
                if(s == m) continue;
                if(re[s] == txt.charAt(i) || re[i] == '.') match.add(s + 1);
            }
            dfs = new DirectedDFS(graph,match);
            pc = new SimpleBag<>();
            for(int w=0 ; w<graph.V(); w++){
                if(dfs.marked(w))pc.add(w);
            }
            if(pc.size() == 0) return false;
        }
        for(int s : pc) if(s == m) return true;
        return false;
    }
}
