import java.util.*;
import java.io.*;
import java.text.DecimalFormat;
import java.math.RoundingMode;

public class AirPort 
{

    public static final float dt = 0.0001f;

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
        
        double minDist = Double.MAX_VALUE;
        int a = 0;
        int b = 0;

        for (int i = 0; i < n; i++) {
            position[i] = new Vector3f(in.nextFloat(),in.nextFloat(),in.nextFloat());
            speed[i] = new Vector3f(in.nextFloat(),in.nextFloat(),in.nextFloat());
        }
        

        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                double t = solve(distance, position[i], position[j], speed[i], speed[j]);

                if (t < minDist) {
                    minDist = t;
                    a = i+1;
                    b = j+1;
                }

            }
        }

        if (minDist == Double.MAX_VALUE) {
            System.out.println("OK");
        }
        else {
            DecimalFormat df = new DecimalFormat("#.###");
            df.setRoundingMode(RoundingMode.CEILING);
            System.out.println("ALARM!");
            System.out.println(df.format(minDist)+" "+a+" "+b);
        }

    }

    public static double solve(double dist, Vector3f pos1, Vector3f pos2, Vector3f speed1, Vector3f speed2) {
        
       /* double dSx = pos1.x - pos2.x;
        double dSy = pos1.y - pos2.y;
        double dSz = pos1.z - pos2.z;

        double dVx = speed1.x - speed2.x;
        double dVy = speed1.y - speed2.y;
        double dVz = speed1.z - speed2.z;
    
        double A = dSx*dSx + dSy*dSy + dSz*dSz - dist*dist;
        double B = 2.0 * ( dSx*dVx +dSy*dVy + dSz*dVz );
        double C = dVx*dVx + dVy*dVy + dVz*dVz;
        */

        Vector3f dS = pos1.minus(pos2);
        Vector3f dV = speed1.minus(speed2);

        double A = dS.dot(dS) - dist*dist;
        double B = 2.0 * dS.dot(dV);
        double C = dV.dot(dV);

       // System.out.println(A+" "+B+" "+C);

        //A + Bt + Ct^2 = dist^2
        
        double D = B * B/(4.0*C*C) - A/C; // /4.0C^2

        //System.out.println("D="+D);

        
        if (D >= -1e-7) {
            D= Math.max(0.0,D);

            double t1 = (-B/(2.0*C) + Math.sqrt(D));

            if (t1 <= 0.0) {
                return Double.MAX_VALUE; 
            }

            return t1;
        }

        return Double.MAX_VALUE;
    }

    public static class Vector3f
    {
        float x, y, z;

        public Vector3f(float x, float y, float z) {
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

}
