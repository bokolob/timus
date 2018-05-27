import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class ProductofDigits
{

    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        
        long n = in.nextLong();
        boolean f = true;
        Stack<Integer> stack = new Stack<>();
        
        if (n == 1) {
            System.out.println(n);
            return;
        }
        else if (n == 0) {
            System.out.println("10");
            return;
        }

        while(f) {
            f = false;
            for(int i = 9; i > 1; i--) {
                if (n % i==0) {
                    stack.push(i);
                    n = (long)(n /(long) i);
//                  System.out.println("n="+n);
                    f=true;
                    break;
                }
            }
        }
        
        if (n > 1) {
            System.out.println("-1");
            return;
        }
        else {
            while(stack.size()>0) {
                System.out.print(stack.pop());
            }
        }

        System.out.println("");
    }

}
