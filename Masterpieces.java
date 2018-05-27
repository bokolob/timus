import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Masterpieces
{
    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int amount = in.nextInt();
        
        int counts[] = new int[amount];

        int max_count = -1;
        int max_offset = 0;

        for (int i = 0; i < amount; i++) {
            int inp = in.nextInt();
            int r = i - inp + 1;
            int v;

            if (r < 0) {
                r = amount + r;
            }

            v = counts[r];

            if (v > max_count) {
                max_count = v;
                max_offset = r;
            }
        }
        
        System.out.println(max_offset+1);
    }

}

