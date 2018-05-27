import java.util.stream.*;
import java.util.*;
import java.io.*;
//import java.math.BigInteger;
//import java.math.RoundingMode;
//import java.text.*;

public class CargoAgency
{
    public static int table[][];
    public static int n;

    public static int[][] matrixMultiply(int m1[][], int m2[][]) {
        int result[][] = new int[n][n];

        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    if (m1[i][k] != Integer.MAX_VALUE && m2[k][j] != Integer.MAX_VALUE) {
                        result[i][j] = Math.min( result[i][j], m1[i][k]+m2[k][j]);
                    }
                }
            }
        }
        
        return result;
    }   

    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        
        n = in.nextInt();

        table = new int[n][n];
    
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    table[i][j]=0;
                }
                else {
                    table[i][j]=Integer.MAX_VALUE;
                }
            }
        }

        int nc = n;

        while (nc-- > 1) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            int cost = in.nextInt();

            table[x][y] = cost;
            table[y][x] = table[x][y];
        }

        printMatrix(table);
        
        int[][] res = matrixMultiply(table, table);
        table = matrixMultiply(res,table);
        
        res = matrixMultiply(table, table);

        printMatrix(table);

        double sum = 0;

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++) {
                if (i != j) 
                    sum += table[i][j];
            }
        }

        //System.out.println(""+sum);


       /* DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.CEILING);

        System.out.println(df.format( sum/(n*(n-1)))  );
*/
        System.out.printf("%.4f\n", sum/(n*(n-1)));
    
    }

	static void printMatrix(int[][] grid) {
		for(int r=0; r<grid.length; r++) {
		   for(int c=0; c<grid[r].length; c++)
			   System.out.print((grid[r][c] == Integer.MAX_VALUE?"∞":grid[r][c]) + " ");
		   System.out.println();
		}
		   System.out.println();
	}

}
