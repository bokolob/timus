import java.io.*;
import java.util.*;

public class Stairs1017
{
    public static long cache[][];
    
    public static long cnt(int total, int prevLength) 
    {
        if (total == 0) {
            return 1L;
        }
        
        long count = 0;

        for (int i = prevLength+1; i <= total; i++) {
            count += stairsCount( total - i, i);
        }

        return count;
    }

    public static long stairsCount(int total, int minLength) {

        if (cache[total][minLength] == 0L) {
            cache[total][minLength] = cnt(total, minLength);
        }

        return cache[total][minLength];
    }

    public static void warn(String s) {
        System.out.println(s);
    }

    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();

        cache = new long[n+1][n+1];
        
        long count = 0;

        for (int i = 1; i < n; i++) {
            count += stairsCount(n - i, i);
        }

        out.println( "" + count );
        out.flush();
    }
}

