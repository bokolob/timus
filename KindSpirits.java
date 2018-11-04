import java.util.Arrays;
import java.io.*;
import java.util.*;

public class KindSpirits
{
    public static class Node
    {
        int level;
        int[] prevs;
        int[] fees;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useLocale(Locale.US);
        
        int l = in.nextInt();

        Node[][] levels = new Node[l][];
        
        for (int i = 0; i < l; i++) {
            int levelSize = in.nextInt();
            levels[i] = new Node[levelSize];

            for (int j = 0; j < levelSize; j++) {
                
            }

        }

    }
    
    public static boolean DEBUG=false;

    public static void warn(String s) {
        if (DEBUG) {
            System.out.println(s);
        }
    }
}
