import java.util.*;
import java.io.*;

public class Orthography
{

    public static int fromPos = 0;

    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in).useLocale(Locale.US);
        PrintWriter out = new PrintWriter(System.out);
        
        char got[] = in.nextLine().toCharArray();

//        warn(new String(got));

        char decoded[] = new char[got.length];

        decode(decoded, got, 0, got.length-1);

        warn (new String(decoded));
    }

    public static void decode(char[] to, char[] from, int toStart, int toEnd) {
        if (toEnd < toStart) {
            return;
        }

        int m = toStart + (toEnd-toStart)/2;

        to[m] = from[fromPos++];
        decode(to, from, toStart, m-1);
        decode(to, from, m+1, toEnd);
    }

    public static final boolean DEBUG = true;
    public static void warn(String s) {
        if (DEBUG) {
            System.out.println(s);
        }
    }
}
