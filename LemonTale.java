import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class LemonTale
{
    
    protected static BigInteger[] kbanacci(int n, int k) {
        BigInteger[] cache = new BigInteger[k+1];
        cache[0] = new BigInteger("1");
        cache[1] = new BigInteger("1");

        if (k > 1) {
            cache[2] = new BigInteger("2");

            if (k > 2) {
                BigInteger two = new BigInteger("2");
            
                for (int i = 3; i <= k; i++) {
                    cache[i] = cache[i-1].multiply(two);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            BigInteger x = cache[k].subtract(cache[0]).add(cache[k]);
            
            for (int j = 1; j <= k; j++) {
                cache[j - 1] = cache[j];
            }

            cache[k] = x;
        }
        
        return cache;
    }

    public static void main(String argv[]) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int k = in.nextInt();

        if (k == 0) {
            System.out.println("1");
            return;
        }

        BigInteger[] s = kbanacci(n+1, k+1);
        
        System.out.println(s[0].toString());
    }


}
