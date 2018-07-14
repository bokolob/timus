import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class SenseOfBeauty
{
    public static void main(String[] argv) {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(getLine(br)) + 1;
        String A = getLine(br);
        String B = getLine(br);

        Set<String> cache = new HashSet<>();
        
        Stack<Integer> solution = new Stack<>();

        int rc = solutionRecursive(A,B,0,0,0,0,solution,cache);
        
        if (rc == -1) {
            System.out.println("Impossible");
        }
        else {
            for (int k : solution) {
                 System.out.print(k);
            }
             System.out.println("");
        }
    }
    
  static int solutionRecursive(String A, String B, int posA, int posB, int r1, int r2, Stack<Integer> result, Set<String> cache  ) {
      
        if (cache.contains(posA+" "+posB)) {
            return -1;
        }

        cache.add(posA+" "+posB);

        if (Math.abs(r1-r2) > 1) {
            return -1; 
        }
        else if (posA == A.length() && posB == B.length()) {
            return 0;
        }
        
        if (posA <  A.length()) {
            int dr1 = 0;
            int dr2 = 0;

            if ( A.charAt(posA) == '0' ) {
                dr1 = 1;
            }
            else {
                dr2 = 1;
            }
            
            result.push(1);
            int rc = solutionRecursive(A, B, posA + 1, posB, r1 + dr1, r2 + dr2, result, cache);


            if (rc == -1) {
                result.pop();
            }
            else {
                return rc;
            }
        }

        if (posB <  B.length()) {
            int dr1 = 0;
            int dr2 = 0;

            if ( B.charAt(posB) == '0' ) {
                dr1 = 1;
            }
            else {
                dr2 = 1;
            }
            
            result.push(2);
            int rc = solutionRecursive(A, B, posA, posB + 1, r1 + dr1, r2 + dr2, result, cache);

            if (rc == -1) {
                result.pop();
            }
            else {
                return rc;
            }
        }

        return -1;
    }

    public static String getLine(BufferedReader br) {
        String s = null;

        try {
            s = br.readLine();
        }
        catch (Exception e) {
        }

        return s;
    }
}
