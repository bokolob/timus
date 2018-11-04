import java.util.*;
import java.io.*;
import java.text.DecimalFormat;
import java.math.RoundingMode;

public class Elevator
{

    public static final double EPS = 1e-8;

    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in).useLocale(Locale.US);
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int k = in.nextInt();

        double u = in.nextDouble();
        double v = in.nextDouble();

        double time = v * (k+n-2) + 15 + 5;
        double timeToGetOnFirstFloor = (k - 1) * v;
        int a = n;

        warn("With no move it will take "+time+" elevator  take "+timeToGetOnFirstFloor+" to get down");

        for (int i = 1; i < n; i++) {
            double petyaTime = i * u;
            double timeToGetUp = v * (n-i-1.0);

            double fullTime;// = petyaTime + elevatorDelay + timeToGetUp+5.0;

            if (n-1==1) {
                warn ("1");
                fullTime = petyaTime;
            }
            else if (petyaTime - timeToGetOnFirstFloor < 1e-9) {
                warn ("2");
                fullTime =  timeToGetOnFirstFloor + 15.0 + timeToGetUp + 5.0;
            }
            else if (petyaTime - timeToGetOnFirstFloor - 15.0 < 1e-9) {
                double elevatorDelay = 15.0-(petyaTime - timeToGetOnFirstFloor);
                warn ("3 "+elevatorDelay);
                fullTime =  petyaTime + elevatorDelay + timeToGetUp + 5.0;
            }
            else {
                warn ("4");
                fullTime = petyaTime + timeToGetUp + 5.0;
            }

            warn("Petya is on "+(n-i)+" floor. He spend "+petyaTime+"s i+ timeToGetUp="+timeToGetUp+ " full="+fullTime);
            
            if (fullTime + 1e-9 < time) {
                time = fullTime;
                a = n - i;
            }
        }
        
        System.out.println(a+"");
    }

    public static final boolean DEBUG = true;
    public static void warn(String s) {
        if (DEBUG) {
            System.out.println(s);
        }
    }

}

