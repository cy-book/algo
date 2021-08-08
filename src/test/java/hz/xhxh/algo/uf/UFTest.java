package hz.xhxh.algo.uf;

import org.junit.Test;

import java.util.Random;

public class UFTest {
    private static final int SIZE = 1000;
    private static final int RELATE_SIZE = 1000;
    private static final Random random = new Random(System.currentTimeMillis());

    @Test
    public void testUF(){
        UF uf = new SimpleUF(SIZE);
        //int[][] allUnion = new int[RELATE_SIZE][2];
        for(int i=0; i<RELATE_SIZE; i++){
            int p = getRandomInt();
            int q = getRandomIntExpect(p);
            System.out.println(p + " " + q);
            uf.union(p,q);
            //allUnion[i][0] = p;
            //allUnion[i][1] = q;
        }
        System.out.println(uf.count() + " components");

    }

    private int getRandomInt(){
        return Math.abs(random.nextInt()) % SIZE;
    }

    private int getRandomIntExpect(int n){
        int r = getRandomInt();
        while (r == n ){
            r = getRandomInt();
        }
        return  r;
    }
}
