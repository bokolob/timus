import java.util.*;
import java.io.*;

public class Palindromes3
{
    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        
        char alphabet[] = new char[] {'a','b','c'};
        
        char firstPairs[][] =  new char[][] {
            {'a', 'b'},
            {'a', 'c'},
            {'b', 'a'},
            {'b', 'c'},
            {'c', 'a'},
            {'c', 'b'}
        };


        int n = in.nextInt();

        if (n * 6 >100000) {
            System.out.println("TOO LONG");
            return;
        }
        
        if (n==1) {
            for(int i = 0;i <3;i++) 
                System.out.println(alphabet[i]);
            return;
        }

        char buf[] = new char[n];

        for (int j = 0; j < firstPairs.length; j++) {
            for (int i = 0; i < n; i++) {
                if (i == 0) {
                    buf[i] = firstPairs[j][0];
                }
                else if (i == 1) {
                    buf[i] = firstPairs[j][1];
                }
                else {
                    char prev = buf[i-2];
                    int p = 0;
                    
                    while (alphabet[ p % 3 ] == prev || alphabet[p%3] == buf[i-1] ) {
                        p++;
                    }
                    
                    buf[i] = alphabet[p % 3];
                }
            }
            System.out.println(new String(buf));
        }

    }
}
