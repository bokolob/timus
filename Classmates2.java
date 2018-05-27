import java.util.stream.*;
import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Classmates2
{
    static Map<Integer, Integer> managers;
    static Map<Integer, Set<Integer>> table;

    protected static void changeRoot(int newRoot) {
        while (managers.containsKey(newRoot)) {
            int m = managers.get(newRoot);

            if (!table.containsKey(newRoot)) {
                table.put(newRoot, new HashSet<>());
            }

            table.get(m).remove(newRoot);
            table.get(newRoot).add(m);

            newRoot = m;
        }
    }

    protected static int calc(int root)
    {
        int  offt = 1;
        
        if (!table.containsKey(root)) 
            return 0;

        List<Integer> sortedSublings =
                                table.get(root).stream()
                                    .map( e -> calc(e) )
                                    .sorted()
                                    .collect(Collectors.toList());
        int result=0;

        for (int i = sortedSublings.size() - 1; i >= 0; i--) {
            int rc = sortedSublings.get(i) + offt;
            offt++;

            if (rc > result) {
                result = rc;
            }
        }
        
        return result;
    }

    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        
        int employees = in.nextInt();

        managers = new HashMap<>();
        int manager = 0;
        table = new HashMap<>();

        while(manager < employees) {
            int nextSubordinate = in.nextInt();

            if (nextSubordinate > 0) {
                if (!table.containsKey(manager)) {
                    table.put(manager, new HashSet<>());
                }
                
                table.get(manager).add(nextSubordinate-1);
                managers.put(nextSubordinate-1, manager);
            }
            else {
                manager++;
            }
        }
        
        int tanya = in.nextInt();
        tanya--;

        changeRoot(tanya);

        System.out.println(""+calc(tanya));

    }

}
