package hz.xhxh.algo.collection;

import hz.xhxh.algo.collection.st.ArrayST;
import org.junit.Test;

public class ArraySTTest {
    @Test
    public  void testArrayST() {
        char[] arr = "abcdefghijkl".toCharArray();
        var st = new ArrayST<String,Integer>();
        for(int i=0; i<arr.length; i++){
            var k = String.valueOf(arr[i]);
            st.put(k,i);
            if(i%2 == 0) st.delete(k);
        }

        for (char c : arr) {
            System.out.format("[%c] in st rank is [%d], st %s contain [%c]\n",
                    c, st.rank(String.valueOf(c)),st.contains(String.valueOf(c)),c);
        }

        for(String s : st.keys()){
            System.out.format("[%s] in st size is [%d]%n",s,st.rank(s));
        }


        while (st.size() >0){
            System.out.format("delete [%s]%n",st.max());
            st.deleteMax();
        }

        System.out.format("symbol table is empty: %s%n",st.isEmpty());


    }
}
