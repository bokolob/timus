import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Teamwork
{
    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();

        int prev = in.nextInt();
        int cnt = 1;

        n--;

//      1 1 2 3 3 3 10 10

        while (n-- > 0) {
            int c = in.nextInt();
            
            if (c == prev) {
                cnt++;
            }
            else { // prev != -1 && c != prev
                System.out.print(cnt+" "+prev+" ");
                prev = c;
                cnt = 1;
            }
        }

        System.out.println(cnt+" "+prev);
    }
}
