import java.util.*;
import java.io.*;

public class DevilSequence
{
    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in).useLocale(Locale.US);
        PrintWriter out = new PrintWriter(System.out);
        
        int n = in.nextInt();
        int len = (int)((Math.log(2) * (double)n) / Math.log(10));
        warn (len+"");
    }

    public static final boolean DEBUG = true;
    public static void warn(String s) {
        if (DEBUG) {
            System.out.println(s);
        }
    }
}
