import java.util.*;
import java.io.*;

public class CircleOfWinter
{
    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in).useLocale(Locale.US);
        PrintWriter out = new PrintWriter(System.out);
        
        int n = in.nextInt();
        
/*        int xl = Integer.MAX_VALUE;
        int xr = Integer.MIN_VALUE;
        
        int yt = Integer.MIN_VALUE;
        int yb = Integer.MAX_VALUE;
*/

        double mDist = Double.MIN_VALUE;
        int fx;
        int fy;

        while (n-- > 0) {
            int x = in.nextInt();
            int y = in.nextInt();
            
        /*    if (x < xl) 
                xl=x;
            
            if (x > xr)
                xr=x;
            
            if (y < yb) 
                yb=y;

            if (y > yt)
                yt=y;
                */

            double d=  Math.sqrt(x*x + y*y);

            if (d > mDist) {
                mDist=d;
                fx=x;
                fy=y;
            }
        }

/*        double xc = (xl+xr)/2.0;
        double yc = (yt+yb)/2.0;
        
        double dx = xc - xl;
        double dy = yc - yb;

        double r = Math.sqrt(dx*dx + dy*dy);
        */

        warn ("0.0 0.0 "+mDist);
    }

    public static final boolean DEBUG = true;
    public static void warn(String s) {
        if (DEBUG) {
            System.out.println(s);
        }
    }
}
