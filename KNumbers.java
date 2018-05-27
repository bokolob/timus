import java.io.*;
import java.util.*;

public class KNumbers
{

    public KNumbers(long K)  {
        this.K = K;
        cache = new HashMap<>();
    }

    public static void warn(String s) {
        System.out.println(s);
    }
    
    private long K;
    private HashMap<Long, Long> cache;
    
    public long calc(long n) {
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        else {
            long val = _calc(n);
            cache.put(n, val);
            return val;
        }
    }

    protected long _calc(long n) {
        if (n <= 0) {
            return 0;
        }
        else if (n == 1) {
            return K - 1;
        }
        else {
            return  calc1(n-2) * (K - 1) + calc1(n - 1) * K;
        }
    }

    protected long calc1(long n) {
        if (n <= 0) {
            return 0;
        }
        else if (n == 1) {
            return K - 1;
        }
        else {
            return calc(n-1) * (K - 1);
        }
    }

    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        long n = in.nextInt();
        long k = in.nextInt();

        KNumbers knumbers = new KNumbers(k);
        out.println( knumbers.calc(n) );
        out.flush();
    }
}

