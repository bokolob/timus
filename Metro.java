import java.io.*;
import java.util.*;

public class Metro
{

    public static final double DIST = Math.sqrt(20000);

    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt(); // columns
        int m = in.nextInt(); // rows

        int diags = in.nextInt();

        double dp[][] = new double[m+1][n+1];
        
        while(diags-- > 0) {
            int x = in.nextInt();
            int y = in.nextInt();
            
            dp[y-1][x-1] = -1;
        }

        for (int i = n; i >= 0; i--) {
            dp[m][i]=100 * (n - i);
        }

        for (int i = m; i >= 0; i--) {
            dp[i][n]=100 * (m - i);
        }
        
        //printTable(dp);

        for (int i = m-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {

                double nDist = Math.min ( dp[i+1][j]+100, dp[i][j+1]+100 );

                if (dp[i][j] == -1) {
                    dp[i][j] = Math.min( nDist, dp[i+1][j+1]+DIST);
                }
                else {
                    dp[i][j] = nDist;
                }
            }
        }
        
        System.out.println((long) (dp[0][0]+0.5));
    }

    public static void   printTable(double[][] table) {
        System.out.println("------------------");
        for (int i = table.length - 1; i>=0; i--) {
            System.out.println(Arrays.toString(table[i]));
        }
    }
}
