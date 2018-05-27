import java.util.*;
import java.io.*;

public class Dode
{
    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        
        int n = in.nextInt();
        
        System.out.println(3*n - 4);

        for (int i = 0; i < 3 ; i++) {
            for (int j = i % 2; j < n -1; j++) {
                System.out.print(j+1);
                System.out.print(" ");
            }
        }
        
        System.out.println("\n");
    }
}
