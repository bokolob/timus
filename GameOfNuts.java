import java.util.*;
import java.io.*;

public class GameOfNuts
{
    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        
        int n = in.nextInt();
        int sum = 0;
        
        while(n-- > 0) {
            sum += count(in.nextInt());
        }

        if (sum % 2 == 1) {
            System.out.println("Daenerys");
        }
        else {
            System.out.println("Stannis");
        }
    }

    public static int count(int n) {
        
        //System.out.println(n);

        if ( n < 3 || (n - 3) % 2 > 0) {
            return 0;
        }

        return 1 + count(n-2);
    }

}
