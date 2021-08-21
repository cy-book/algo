package hz.xhxh.algo.collection;

import hz.xhxh.algo.collection.st.LinkedST;
import hz.xhxh.algo.collection.st.SymbolTable;
import javafx.scene.text.Font;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;

public class LinkedSTTest {
    @Test
    public void testLinkedST() throws FileNotFoundException  {
        SymbolTable<String,Integer> st = new LinkedST<>();
        Scanner scanner = new Scanner(
                new BufferedInputStream(new FileInputStream(
                        new File(Paths.get("fonts.txt").toUri()))),
                StandardCharsets.UTF_8);
        for(int i=0; scanner.hasNext(); i++){
            st.put(scanner.nextLine(),i);
        }
        scanner.close();

        Font.getFamilies().forEach(s -> {
            if(st.contains(s)) st.delete(s);
            else {
                System.out.println("[st not contain]  " + s);
            }
        });

        for(String s :st.keys()){
            System.out.println(s);
        }
    }
}
