import java.util.*;
import java.io.*;

public class DevilSequence
{
    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in).useLocale(Locale.US);
        PrintWriter out = new PrintWriter(System.out);
        
        double n = in.nextInt();

        int len = (int)((Math.log(2)*(n-1))/Math.log(10.0) + Math.log(3.0)/Math.log(10.0));
        warn (len+"");
    }

    public static final boolean DEBUG = true;
    public static void warn(String s) {
        if (DEBUG) {
            System.out.println(s);
        }
    }
}
