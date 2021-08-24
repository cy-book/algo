package hz.xhxh.algo.collection;

import hz.xhxh.algo.collection.st.BST;
import org.junit.Test;

public class BSTTest {
    @Test
    public  void testBST() {
        String[] arr = initKey("dbfaceg".toCharArray());

        var st = new BST<String,Integer>();
        for(int i=0; i<arr.length; i++){
            var k = arr[i];
            st.put(k,i);
            System.out.format("put [%s,%s] in st rank [%d]%n",k,i,st.rank(k));
        }

        for (String c : arr) {
            System.out.format("[%s] in st rank is [%d], st %s contain [%s]\n",
                    c, st.rank(c),st.contains(c),c);
        }

        for(String s : st.keys()){
            System.out.format("[%s] in st size is [%d]%n",s,st.rank(s));
        }


        while (st.size() >0){
            System .out.format("delete [%s]%n",st.max());
            st.deleteMax();
        }

//        st.midPrint();
//
//        for(int i=0; i<arr.length;i++){
//            String s = arr[arr.length-1 -i];
//            st.delete(s);
//            System.out.format("delete [%s]%n",s);
//
//            System.out.format("[%s] in st rank is [%d], st %s contain [%s]\n",
//                    s, st.rank(s),st.contains(s),s);
//            st.midPrint();
//            for(int h=0;h<arr.length;h++){
//                System.out.printf("st.contain(%s): %s\n",arr[h],st.contains(arr[h]));
//            }
//
//        }


        System.out.format("symbol table is empty: %s%n",st.isEmpty());


    }

    public static String[] initKey(char[] keys){
        String[] strs = new String[keys.length];
        for(int i=0; i<keys.length;i++){
            strs[i] = String.valueOf(keys[i]);
        }
        return strs;
    }

    public static String reverse(String str){
        char[] arr = str.toCharArray();
        int lo = 0, hi = arr.length-1;
        while(lo < hi){
            arr[lo] ^= arr[hi];
            arr[hi] ^= arr[lo];
            arr[lo] ^= arr[hi];
            lo ++;
            hi --;
        }
        return String.valueOf(arr);
    }
}
