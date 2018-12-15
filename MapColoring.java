import java.util.*;
import java.io.*;
import java.text.DecimalFormat;
import java.math.RoundingMode;

public class MapColoring
{
    public static final boolean DEBUG = true;
    
    static enum Colors {RED, BLUE, UNKNOWN};

    static List<Integer>[] graph;
    static Colors[] colors;

    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in).useLocale(Locale.US);

        int cnt = in.nextInt();

        graph = new List[cnt];
        colors= new Colors[cnt];

        for (int i = 0; i < cnt; i++) {
            if (graph[i] == null) 
                graph[i] = new ArrayList<>();

            colors[i] = Colors.UNKNOWN;

            for(int n=in.nextInt(); n != 0;   n = in.nextInt()) {
//                System.out.println("Adding "+n+" to "+(i+1));
                graph[i].add(n-1);

                if (graph[n-1] == null) {
                    graph[n-1] = new ArrayList<>();
                }

                 graph[n-1].add(i);

            }
       }
       
       colors[0] = Colors.RED;

       if (bfs() == true) {
           for (int i = 0; i < cnt; i++) {
                if (colors[i] == Colors.RED) {
                    System.out.print("0");
                }
                else {
                    System.out.print("1");
                }
           }
            System.out.println("");
       }
       else {
            System.out.println("-1");
       }

    }
    
    public static boolean bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        Set<Integer> visited = new HashSet<>();

        while (queue.size() > 0) {
            int node = queue.poll();
            
            Colors color = colors[node];
            Colors nextColor = null;

            if (color == Colors.RED) { nextColor = Colors.BLUE; }
            else if (color == Colors.BLUE) {nextColor = Colors.RED ;}
            
            for (int i = 0; i < graph[node].size(); i++) {
                int neighbour = graph[node].get(i);
                
//                 System.out.println("From "+node+" "+colors[node]+" to "+neighbour+" "+colors[neighbour]);

                if (colors[neighbour] == Colors.UNKNOWN) {
 //                   System.out.println("Coloring "+neighbour+" to "+nextColor);
                    colors[neighbour] = nextColor;
                }
                else if(colors[neighbour] != nextColor) {
                    return false;
                }

                if (!visited.contains(neighbour)) {
   //                 System.out.println("enqueue "+neighbour);
                    queue.add(neighbour);
                }
                
            }

            visited.add(node);
        }

        return true;
    }

}

