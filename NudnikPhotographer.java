
import java.util.*;
import java.io.*;

public class NudnikPhotographer {
    
    static Map<String, Integer> cache;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useLocale(Locale.US);
        PrintWriter out = new PrintWriter(System.out);
        
        int n = in.nextInt();
        
        cache = new HashMap<>();

        System.out.println("" + dfs(1, n, 1L, 1));
    }
    
    public static int dfs(int root, int len, long visited, int cnt) {
            if (cnt > len) {
                return 1;
            }
            
            String key = root + "_" + visited;
            
            if (cache.containsKey(key)) {
                System.out.println("hit "+key);
                return cache.get(key);
            }

            int result = 0;

            for (int i = 1; i <= 2; i++) {
                
                int child = root - i;
                

                if (child > 1 && (visited & (1L << child)) == 0) {
                    System.out.println("Going from "+root+" to "+child);
                    visited |= (1L << child);
                    result += dfs(child, len, visited, cnt + 1);
                    visited &= ~(1L << child);
                }

                child = root + i;

                if (child <= len && (visited & (1L << child)) == 0  ) {
                    System.out.println("Going from "+root+" to "+child);
                    visited |= (1L << child);
                    result += dfs(child, len, visited, cnt +1);
                    visited &= ~(1L << child);
                }

            }
            
            cache.put(key,result);

            return result;
    }

}
