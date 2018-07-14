
import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Order
{
    public static void main(String[] argv) {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        long n = Integer.parseInt(getLine(br));
    
        long current = 0;
        int cnt = 0;

        while (n-- > 0) {
            long c = Long.parseLong(getLine(br)); 
            
            if (cnt == 0) {
                current = c;
                cnt = 1;
            }
            else if (current == c) {
                cnt++;
            }
            else {
                cnt--;
            }

        }

        System.out.println(current+" ");
    }

    public static String getLine(BufferedReader br) {
        String s = null;

        try {
            s = br.readLine();
        }
        catch (Exception e) {
        }

        return s;
    }
}
