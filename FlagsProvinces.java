import java.util.Arrays;
import java.io.*;
import java.util.*;

public class FlagsProvinces {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useLocale(Locale.US);
        int colors = in.nextInt();

        /*
         * total connections amount = C(m,2) where m is count of lines
         * C(m,2)= n - amount of different colors
         *
         * so m!/(1*(m-2)!) = n
         *
         * We can find m from here easily
         *
         */

        int rows = (int)((1.0 + Math.sqrt(1.0 + 8.0 * colors))/2.0);
        int result[][] = new int[rows][rows - 1];
        
        int curColor=1;

        for (int i = 1; i < rows; i++) {
            int offset = i-1;
            int curPos = 0;

            for (int j = 0; j < i ; j++) {
                result[j][offset] = curColor;
                result[i][curPos] = curColor;

                curPos++;
                curColor++;
            }
        }
        
        System.out.println(rows);

        for (int i = 0; i < rows; i++) {
             System.out.print((rows-1)+" ");

            for (int j = 0; j < rows-1 ; j++) {
                System.out.print(result[i][j] + (j == rows-2 ?"" :  " "));
            }

            System.out.println("");
        }

    }


    public static boolean DEBUG=true;

    public static void warn(String s) {
        if (DEBUG) {
            System.out.println(s);
        }
    }
}
