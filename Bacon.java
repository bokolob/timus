import java.util.*;
import java.io.*;

// Решается с помощью z-функции 

public class Bacon
{
    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        
        String input = in.next();
        char[] str = new StringBuilder(input).reverse().toString().toCharArray();
        
        int count = 0;
       // System.out.println("    "+Arrays.toString(str));

        int[] zf = new int[str.length];
        for (int offset = 0; offset < input.length(); offset++) {
            int zmax = zFunction(str, offset, zf);
            
           // System.out.println("o="+offset+" "+Arrays.toString(zf));

            count += ( str.length - offset - zmax );
        }

        System.out.println(count);
    }

    public static int zFunction(char[] src, int offset, int dst[]) {
        int max = 0;
        
        int l = offset;
        int r = offset;

        dst[0] = 0;

        for (int i = 1+offset; i < src.length; i++) {
            int dstOffset =  i - offset;
            dst[dstOffset] = 0;

            if (i <= r) {
                dst[dstOffset] = Math.min(dst[i-l], r-i+1);
            }
            
            while(i+dst[dstOffset] < src.length && src[offset + dst[dstOffset]] == src[i+dst[dstOffset]])
                dst[dstOffset]++;
            
            if (i+dst[dstOffset]-1 > r) {
                l = i;
                r = i + dst[dstOffset] -1 ;
            }

            if (dst[dstOffset] > max)
                max = dst[dstOffset];
        }

        return max;
    }
}

