import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Stars
{
        private static int size;
        private static int[] tree;

        public static int query(int id) {
            int result = 0;
            while (id > 0) {
                result += tree[id];
                /*
                 * To query an index, we need all indices that were not included in
                 * previous intervals of form [i - 2 ^ r + 1, i]. That is, we need
                 * to find the next lowest number that does not have the lowest bit
                 * in i set. (id & -id) represents the lowest bit in id; by
                 * subtracting this we reach the next number we need to query.
                 */
                id -= (id & -id);
            }

            return result;
        }

        public static void update(int id) {
            while (id <= size) {
                tree[id]++;
                /*
                 * To update an index, we need to update all indices that subsume
                 * the current range [i - 2 ^ r + 1, i]. That is, we need to find
                 * the next highest number that has the lowest bit of i unset.
                 */
                id += (id & -id);
            }
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

    
    public static void main(String[] argv)
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        String s2 = getLine(br);
        int total = Integer.parseInt(s2);
        
        size = 32002;
        tree = new int[size + 1];
        
        int byLevels[] = new int[total];

        int cp = total;

        while(cp-- > 0) {
            String[] s = getLine(br).split(" ");
            int x = Integer.parseInt(s[0]); 
            x++;
            
            byLevels[query(x)]++;
            update(x);
        }

        for (int i = 0; i < total; i++) {
            System.out.println(byLevels[i]);
        }

    }

}
