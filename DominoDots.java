import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class DominoDots
{
    public static void main(String[] argv) {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        long n = Integer.parseInt(getLine(br)) + 1;
        long result = 0;
        
        long tmp_sum = n*(n-1)/2;

        for (int i = 0; i <= n; i++) {
            result += i * (n-i) + tmp_sum;
            tmp_sum -= i;
        }

        System.out.println(result+" ");
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
