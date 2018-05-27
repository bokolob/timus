import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class ShipRoutes
{
    private static int n;
    private static Map<String, BigInteger> cache;

    private static BigInteger bfs(int params[], int prev)
    {
        int a = (prev + 1) % 3;
        int b = (prev + 2) % 3;

        int val_a = params[a];
        int val_b = params[b];
        int val_c = params[prev];
        
        String key = ""+val_a+" "+val_b+" "+val_c+" "+prev;

        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        BigInteger result = BigInteger.ZERO;

        if (params[a]==0 && params[b] == 0) {
            if (prev != 0 && params[prev] == 0) {
//              warn "HIT! ".join(", ",0, @$path,0);
                return BigInteger.ONE;
            }
            else {
                return BigInteger.ZERO;
            }
        }
        
        if (params[a] > 0) {
            params[a]--;
            result = result.add(bfs(params, a));
            params[a]++;
        }
        
        if (params[b] > 0) {
            params[b]--;
            result = result.add(bfs(params, b));
            params[b]++;
        }
        
        cache.put(key, result);
        return result;
    }

    private static BigInteger factorial(int n)
    {
        BigInteger result = BigInteger.ONE;

        while(n>0) {
            result = result.multiply(BigInteger.valueOf(n));
            n--;
        }

        return result;
    }

        
    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        
        n = in.nextInt();
        cache = new HashMap<>();

        BigInteger f = factorial(n-1);
        BigInteger g = f.multiply(BigInteger.valueOf(n));

        System.out.println(""+bfs(new int[]{n-1,n,n},0).multiply(f).multiply(g).multiply(g).divide( BigInteger.valueOf(2) ));
    }
}
