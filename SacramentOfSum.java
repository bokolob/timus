import java.util.*;
import java.io.*;

public class SacramentOfSum
{
    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        
        List<Integer> first = new ArrayList<>();
        List<Integer> second =  new ArrayList<>();

        int n = in.nextInt();
        while (n-- > 0) {
            first.add(in.nextInt());
        }

        n = in.nextInt();
        while (n-- > 0) {
            second.add(in.nextInt());
        }
        
        int i = 0;
        int j = 0;

        while (i < first.size() && j < second.size()) {
            int sum = first.get(i) + second.get(j);

            if (sum > 10000) {
                j++;
            }
            else if (sum < 10000) {
                i++;
            }
            else {
                System.out.println("YES");
                return;
            }
        }

        System.out.println("NO");
    }
}
