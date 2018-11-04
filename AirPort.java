import java.util.*;
import java.io.*;
import java.text.DecimalFormat;
import java.math.RoundingMode;

public class AirPort 
{

    public static final double EPS=1e-7;
    public static final boolean DEBUG = true;

    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in).useLocale(Locale.US);
        PrintWriter out = new PrintWriter(System.out);

        /*
         * 2 1.0
         * 1.0 1.0 -1.0 0.0 0.0 10.0
         * 0.0 0.0 4.0 2.0 0.0 0.0
         */

        int n = in.nextInt();
        double distance = in.nextDouble();
        
        Vector3f position[] = new Vector3f[n];
        Vector3f speed[] = new Vector3f[n];
        
        double minDist = Double.POSITIVE_INFINITY;
        int a = 0;
        int b = 0;

        for (int i = 0; i < n; i++) {
            position[i] = new Vector3f(in.nextFloat(),in.nextFloat(),in.nextFloat());
            speed[i] = new Vector3f(in.nextFloat(),in.nextFloat(),in.nextFloat());
        }
        

        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                double t = solve(distance, position[i], position[j], speed[i], speed[j]);

                if (t > 0.0 && t < minDist) {
                    minDist = t;
                    a = i+1;
                    b = j+1;
                }

            }
        }

        if (minDist == Double.POSITIVE_INFINITY) {
            System.out.println("OK");
        }
        else {
            DecimalFormat df = new DecimalFormat("0.000");
            df.setRoundingMode(RoundingMode.HALF_UP);
            System.out.println("ALARM!");
            System.out.println(df.format(minDist)+" "+a+" "+b);

/*            System.out.println("ALARM!");
            System.out.println(minDist+" "+a+" "+b); */

        }

    }


    public static double solve(double dist, Vector3f pos1, Vector3f pos2, Vector3f speed1, Vector3f speed2) {

        warn("s1="+speed1+" s2="+speed2);

        Vector3f dS = pos1.minus(pos2);
        Vector3f dV = speed1.minus(speed2);
        
        double f = dV.normalize();

        double A = dS.dot(dS) - dist*dist;
        double B = dS.dot(dV);

        warn("solve: "+A+" "+B+" ds="+dS+" dV="+dV);

        //A + Bt + Ct^2 = dist^2
        
        double D = B*B - A;

        warn("D="+D);
        
        if (D >= -EPS) {
            D= Math.max(0.0,D);

            double t1 = (-B + Math.sqrt(D));
            double t2 = (-B - Math.sqrt(D));

            warn ("t1="+t1+" t2="+t2+" f="+f);

            double result;

            if (t1 > 0.0 && t2 > 0.0 ) {
                 result = Math.min(t1,t2);
            }
            else if (t1 > 0.0) {
                result = t1;
            }
            else if (t2 > 0.0) {
                result = t2;
            }
            else {
                result = Double.POSITIVE_INFINITY;
            }

            if (result < 0.0) {
                result = 0.0;
            }

            return result / f;
        }

        return Double.POSITIVE_INFINITY;
    }

    public static class Vector3f
    {
        double x, y, z;

        public Vector3f(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public Vector3f add(Vector3f dv) {
            return new Vector3f(x + dv.x,
                            y + dv.y,
                            z + dv.z);
        }

        public Vector3f minus(Vector3f dv) {
            return new Vector3f(x - dv.x,
                            y - dv.y,
                            z - dv.z);
        }
        
        public double magnitude() {
            return Math.sqrt( x*x +y*y + z*z );
        }

        public double normalize() {
            double magnitude = magnitude();
            x /= magnitude;
            y /= magnitude;
            z /= magnitude;

            return magnitude;
        }

        public double distance(Vector3f point) {
            double dx = x - point.x;
            double dy = y - point.y;
            double dz = z - point.z;

            return Math.sqrt( dx*dx+ dy*dy+dz*dz );
        }

        public double dot(Vector3f v) {
            return x*v.x+y*v.y+z*v.z;
        }

        public String toString() {
            return "<"+x+","+y+","+z+">";
        }

    }

    public static void warn(String s) {
        if (DEBUG) {
            System.out.println(s);
        }
    }

}
