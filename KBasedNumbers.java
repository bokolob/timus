import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class KBasedNumbers
{
    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        
        int n = in.nextInt();
        int k = in.nextInt();
        
        BigInteger table[][] = new BigInteger[n][2];
        
        table[0][0] = BigInteger.ZERO;
        table[0][1] = BigInteger.valueOf(k - 1);
        
        for (int i = 1; i < n; i++) {
            table[i][1] = table[0][1].multiply(table[i-1][1].add(table[i-1][0]));
            table[i][0] = table[i-1][1];
        }
        
        System.out.println(""+(table[n-1][0].add(table[n-1][1])));
    }

}
