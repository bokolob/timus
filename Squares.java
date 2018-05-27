import java.io.*;
import java.util.*;

public class Squares
{
    public static void warn(String s) {
        System.out.println(s);
    }

    public static int _calc(int n) {
       
        int root = (int)Math.sqrt(n);

        if (n == root * root) {
            return 1;
        }

        int true_square = root * root;
        int min = Integer.MAX_VALUE;

        for (int i = 1; i*i <= true_square; i++) {
            int tmp = 1 + calc(n - i*i);

            if (tmp < min) {
                min = tmp;
            }
        }
        
        return min;
    }
    
    static int[] cache;

    public static int calc(int n) {

        if (n == 0)
            return 0;

        if (cache[n-1] == 0) {
            cache[n-1] = _calc(n);
        }

        return cache[n-1];
    }

    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();

        cache = new int[n];

        for (int i = 1; i < n; i++) {
            calc(i);
            //warn ("calc("+i+")="+calc(i));
        }

        out.println( "" + calc(n) );
        out.flush();
    }
}

