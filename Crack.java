import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Crack
{
    public static long maxPath(List<Integer> words) {
        if (words.size() == 0) {
            return 0L;
        }
        else if (words.size() == 1) {
            return words.get(0);
        }
        else if (words.size() == 2) {
            return max( words.get(0), words.get(1));
        }

        long[] cache = new long[ words.size() ];

        cache[words.size() - 1] = words.get(words.size() -1); 
        cache[words.size() - 2] = words.get(words.size() -2);
        
        long max = max( cache[words.size() - 1], cache[words.size() - 2] );

        for (int j = words.size() - 3; j >=0; j--) {
            long x = words.get(j) + cache[j + 2];
            long y = words.get(j);

            if (j + 3 < words.size()) {
                y += cache[j+3];
            }

            cache[j] = max(x,y);
            
            if (cache[j] > max) {
                max = cache[j];
            }
        }

        return max;
    }

    private static long max(long a, long b) {
        return a > b ? a : b;
    }

    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in).useDelimiter("[^a-zA-Z]+");
        PrintWriter out = new PrintWriter(System.out);
        
        List<Integer> words = new ArrayList<>();

        while (in.hasNext()) {
            String s = in.next();
            words.add(s.length());
        }

        System.out.println(""+maxPath(words));
    }
}
    
