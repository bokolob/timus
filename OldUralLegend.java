import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class OldUralLegend
{

    public static void main(String[] argv) {

        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        char input[] = in.next().toCharArray();
        int cumulativeSum[] = new int[input.length];

        cumulativeSum[0] = input[0] - '0';

        Map<Integer, List<Integer>> char2pos = new HashMap<>();

        char2pos.put(cumulativeSum[0], new ArrayList<>());
        char2pos.get(cumulativeSum[0]).add(0);

        for (int i = 1; i < input.length; i++) {
            int val = input[i] - '0';
            cumulativeSum[i] = cumulativeSum[i-1]+val;

            if (!char2pos.containsKey(val)) {
                char2pos.put(val, new ArrayList<>());
            }

            char2pos.get(val).add(i);
        }

        int length = 1;
        int dl = 10;

        for (int i = 1; i < Integer.MAX_VALUE; i++) {

            if (i % dl == 0) {
                length++;
                dl *= 10;
            }

            boolean found = false;

            int firstDigit = i / (dl/10);

//            for (int j = 0; j <  input.length - length + 1; j++) {
            if ( char2pos.containsKey(firstDigit)) {
                  for (int j : char2pos.get(firstDigit)) {
                    if (j >= input.length - length + 1)
                        continue;

                    int contains = cumulativeSum[j+length-1];
                    
                    if (j > 0)
                        contains -= cumulativeSum[j-1];
                    
                    if (contains <= i) {
                        found = compare(i, input, j, length );
                //        System.out.println("check "+i+" = "+found+" pos="+j+" l="+length);

                        if (found) 
                            break;
                    }
                }
            }

            if (!found) {
                System.out.println(""+i);
                return;
            }

        }
        
        System.out.println("-1");
    }
    
    public static boolean compare(int k, char[] src, int pos, int len) {
        
        while(len > 0) {
            int v = k % 10;
            k /= 10;

            if (v != src[pos+len-1] - '0')
                return false;

            len--;
        }
        
        return true;
    }
}
