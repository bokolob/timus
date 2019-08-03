import java.util.*;
import java.io.*;

public class Plato {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useLocale(Locale.US);
        PrintWriter out = new PrintWriter(System.out);
        
        int n = in.nextInt();
        int[] d = new int[n];
        long total = 0;

        for (int i  = 0; i < n; i++) { 
            d[i] = in.nextInt();
            total += d[i];
        }
        
        Arrays.sort(d);
        
        long result = 0; 

        for (int j = 0; j < n; j++) {
            result += total*d[j];
            total -= d[j];
            result += total*d[j];
        }

        System.out.println(""+result);

    }

}
