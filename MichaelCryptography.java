import java.util.*;
import java.io.*;

public class MichaelCryptography {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useLocale(Locale.US);
        PrintWriter out = new PrintWriter(System.out);
        
        long n = in.nextLong();
        
        int result = 0;
        
        for (long i = 2; i*i <= n && result < 22; i++) {
            
           // System.out.println("i="+i);
            //i = 1000000007 n=1000000007
            while (n % i == 0) {
                //System.out.println("New div: "+i + " n="+n);
                result++;
                n /= i;
            }

        }
        
        if (n > 1) {
            result++;
        }

        if (result == 20) {
            System.out.println("Yes");
        }
        else {
            System.out.println("No");
        }

    }

}
