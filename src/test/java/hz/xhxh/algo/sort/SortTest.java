package hz.xhxh.algo.sort;

import org.junit.Test;

import java.net.Inet4Address;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class SortTest {
    private static final int SIZE = 20;
    @Test
    public void testSort(){
        Integer[] arr1 = new Integer[SIZE];
        Integer[] arr2 = new Integer[SIZE];
        Random r = new Random(System.currentTimeMillis());
        for(int i=0; i<SIZE; i++){
            arr1[i] = r.nextInt() % 100;
            arr2[i] = SIZE - i;
        }
        var sort = new HeapSort<Integer>();
        var arr = arr1.clone();
        System.out.println("using " + sort.getClass().getSimpleName());
        sort.sort(arr);
        System.out.println(sort.isSorted(arr) ?
                "array is sorted"
                :"array not sorted" );
        System.out.println(Arrays.toString(arr));
    }

}
