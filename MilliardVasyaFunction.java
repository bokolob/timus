
import java.util.*;
import java.io.*;

public class MilliardVasyaFunction {
        
    //1000000000
    static final int  CELLS  = 10;
    static final int  POINTS = 9;

    static Map<String,Long> cache;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useLocale(Locale.US);
        PrintWriter out = new PrintWriter(System.out);

        int s = in.nextInt();
        
        cache = new HashMap<>();
        
        if (s == 1) {//можем поставить 1 в старший разряд. Но если число больше 1, то в первый разряд мы ставить ничего не можем, будет больше миллиарда
            System.out.println(""+ (1L + calc(CELLS-1,s)));
        }
        else {
            System.out.println(""+calc(CELLS-1,s));
        }
    }

    public static long calc(int cells, int points) {
        
        String key = cells + "/" + points;

        if (cache.containsKey(key)) {
           return cache.get(key);
        }

        if (cells == 0 && points > 0) {
            return 0;
        }

        if (points == 0) {
            return 1;
        }

        if (cells == 1) {
            if (points > POINTS) {
                return 0;
            }
            else {
                return 1;
            }
        }

        long result = 0;
        for (int i = 0; i <= Math.min(POINTS, points); i++) {
            result += calc(cells-1, points - i);
        }

        cache.put(key, result);
        return result;
    }

}
