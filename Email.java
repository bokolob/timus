import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Email
{
    public static final long modulo  =1000000000L + 7L;

    public static int[][] dp_length(char[] str_a, char[] str_b)
    {
        int[][] result = new int[ str_b.length  ][ str_a.length ];

        int prev = 1;
        boolean equal = false;

        for ( int i = 0; i < str_a.length; i++) {
            if (str_a[i] == str_b[0] && !equal) {
                result[0][i] = prev;
                equal = true;
            }
            else {
                result[0][i] = prev + 1;
            }

            prev = result[0][i];
        }
        
        equal = false;
        for (int i = 1; i < str_b.length; i++) {
            if (str_b[i] == str_a[0] && !equal) {
                result[i][0] = result[i-1][0];
                equal = true;
            }
            else {
                result[i][0] = result[i-1][0] + 1;
            }
        }

        for (int i = 1; i < str_b.length; i++) {
            for (int j = 1; j < str_a.length; j++) {
                if (str_b[i] == str_a[j]) {
                    result[i][j] = result[i-1][j-1]+1;
                }
                else {
                    if (result[i][j-1] < result[i-1][j]) {
                        result[i][j] = result[i][j-1] + 1;
                    }
                    else if (result[i-1][j] < result[i][j-1]) {
                        result[i][j] = result[i-1][j] + 1;
                    }
                    else {
                        result[i][j] = result[i-1][j] + 1;
                    }
                }
            }
        }

        return result;
    }

    public static long dp_count(char[] str_a, char[] str_b)
    {
        int[][] lengths = dp_length(str_a, str_b);
        long[][] result = new long[ str_b.length  ][ str_a.length ];

        for (int i = 0; i < str_a.length; i++) {
            if (str_a[i] == str_b[0] || i > 0 && result[0][i-1] == 1) {
                result[0][i] = 1;
            }
            else {
                result[0][i] = i + 2;
            }
        }

        for (int i = 1; i < str_b.length; i++) {
            if (str_b[i] == str_a[0] || i > 0 && result[i-1][0] == 1) {
                result[i][0] = 1;
            }
            else {
                result[i][0] = (result[i-1][0] + 1) % modulo;
            }
        }

        for (int i = 1; i < str_b.length; i++) {
            for (int j = 1; j < str_a.length; j++) {
                if (str_b[i] == str_a[j]) {
                    result[i][j] = result[i-1][j-1];
                }
                else {
                    if (lengths[i][j-1] < lengths[i-1][j]) {
                        result[i][j] = result[i][j-1];
                    }
                    else if (lengths[i-1][j] < lengths[i][j-1]) {
                        result[i][j] = result[i-1][j];
                    }
                    else {
                        if (result[i-1][j] == 1 && result[i][j-1] == 1 && result[i-1][j-1] == 1) {
                            result[i][j] = 1L;
                        }
                        else {
                            result[i][j] = (result[i-1][j] + result[i][j-1]) % modulo;
                        }
                    }
                }
            }
        }
        
        return result[ str_b.length - 1  ][ str_a.length -1];
    }

    

    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        
        char str_a[] = in.next().toCharArray();
        char str_b[] = in.next().toCharArray();

        System.out.println(""+dp_count(str_a, str_b));

    }

}
