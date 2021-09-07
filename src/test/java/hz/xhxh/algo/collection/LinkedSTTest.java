package hz.xhxh.algo.collection;

import hz.xhxh.algo.collection.st.LinkedST;
import hz.xhxh.algo.collection.st.ST;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

public class LinkedSTTest {
    @Test
    public void testLinkedST() throws FileNotFoundException  {
        ST<String,Integer> st = new LinkedST<>();
        Scanner scanner = new Scanner(
                new BufferedInputStream(Objects.requireNonNull(this.getClass().getResourceAsStream("/fonts.txt"))),
                StandardCharsets.UTF_8);
        for(int i=0; scanner.hasNext(); i++){
            st.put(scanner.nextLine(),i);
        }
        scanner.close();


        for(String s :st.keys()){
            System.out.println(s);
        }
    }
}
