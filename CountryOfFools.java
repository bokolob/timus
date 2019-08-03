import java.util.*;
import java.io.*;

public class CountryOfFools
{
    static int items[];

    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in).useLocale(Locale.US);
        PrintWriter out = new PrintWriter(System.out);

        Comparator<Integer> idComparator = new Comparator<Integer>(){
            @Override
            public int compare(Integer c1, Integer c2) {
                return items[c2] - items[c1];
            }
        };
        
        int n = in.nextInt();
        Queue<Integer> itemsPriorityQueue = new PriorityQueue<>(n, idComparator);

        items = new int[n];

        int left = 0;
        for (int i = 0; i < n; i++) {
            items[i] = in.nextInt();
            left += items[i];
            itemsPriorityQueue.add(i);
        }

        int pending = -1;

        while(left > 0) {
            int maxItem;

            if (itemsPriorityQueue.size() > 0) {
                maxItem = itemsPriorityQueue.poll();
            }
            else {
                maxItem = pending;
                pending = -1;
            }

            items[maxItem]--;
            left--;

            if (pending >= 0) {
                itemsPriorityQueue.add(pending);
                pending = -1;
            }

            if (items[maxItem] > 0) {
                pending=maxItem;
            }

            System.out.print((maxItem+1)+" ");
        }

        System.out.println("");

/* 
        int pos = 0;

        while (left > 0) {
            if (items[pos] > 0) {
                System.out.print((pos+1)+" ");
                items[pos]--;
                left--;

                //warn("left="+left);
            }

            pos = (pos+1) % n;
        }
        System.out.println("");
*/
    }

    public static final boolean DEBUG = true;
    public static void warn(String s) {
        if (DEBUG) {
            System.out.println(s);
        }
    }


}
