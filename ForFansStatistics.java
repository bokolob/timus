import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class ForFansStatistics 
{
    public static Map<Long, List<Integer>> storage = new HashMap<>();

    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        
        int i = 0;
        int n = in.nextInt();

        while(n-- > 0) {
            i++;
            long value = in.nextLong();
            
            if (!storage.containsKey(value)) {
                storage.put(value, new ArrayList<>());
            }
            
            storage.get(value).add(i);
        }

        int q = in.nextInt();
        
        //System.out.println("");
        while (q-- > 0) {
            int l = in.nextInt();
            int r = in.nextInt();
            long value = in.nextLong();

            //System.out.println("q="+q);

            if (!storage.containsKey(value)   ) {
                System.out.print("0");
            }
            else {
                List<Integer> list = storage.get(value);
                long t = upperBound(list, 0, list.size() -1,  r);
        //        System.out.println("t="+t);
                if (t >= l && t <= r) {
                    System.out.print("1");
                }
                else {
                    System.out.print("0");
                }
            }
        }

        System.out.println("");
    }

    public static int upperBound(List<Integer> list, int from, int to, int value) {
        
        //System.out.println(Arrays.toString( list.subList(from, to+1).toArray() ));

        if (to < from) {
            return -1;
        }
        else if (to == from) {
            return list.get(to) > value ? -1 : list.get(to);
        }
        else if (to == from + 1) {
            if (list.get(from) > value) {
                return -1;
            }
            else if (list.get(to) > value) {
                return list.get(from);
            }
            else {
                return list.get(to);
            }
        }

        int m = from + (to - from)/2;
        int v = list.get(m);

        if (v > value) {
            return upperBound(list, from, m-1, value);
        }
        else {
            return upperBound(list, m, to, value);
        }
    }

}
