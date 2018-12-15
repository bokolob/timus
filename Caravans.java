import java.util.*;
import java.io.*;
import java.text.DecimalFormat;
import java.math.RoundingMode;

public class Caravans 
{
    public static final boolean DEBUG = true;
    
    static List<Integer>[] graph;
    
    static int n, m;

    public static void main(String[] argv) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = getLine(br);
        int pos = input.indexOf(" ");

        n = Integer.parseInt(input.substring(0,pos));
        m = Integer.parseInt(input.substring(pos+1));

        graph = new List[n+1];

        while (m-- > 0) {
            input = getLine(br);
            int p = input.indexOf(" ");
            int from = Integer.parseInt(input.substring(0,p));
            int to = Integer.parseInt(input.substring(p+1));

            addEdge(from, to);
        }
        
        input = getLine(br);

        int p1 = input.indexOf(" ");
        int p2 = input.indexOf(" ", p1+1);

        int s = Integer.parseInt(input.substring(0,p1));
        int f = Integer.parseInt(input.substring(p1+1,p2));
        int r = Integer.parseInt(input.substring(p2+1));
        
        Record[] patches = dijkstra(s,f);
        Record[] fromDan = dijkstra(r,-1);
        
        System.out.println(enumerateAllPathes(fromDan, patches, f));
    }
    
    static int enumerateAllPathes(Record[] fromDan, Record[] patches, int to) {
        Stack<Integer> stack = new Stack<>();
        stack.push(to);
        
        int globalMax = Integer.MIN_VALUE;
        int localMin = Integer.MAX_VALUE;

        while (stack.size() > 0) {
            int node = stack.pop();
            Record r = patches[node];
            
            if (r.from.size() == 0) {
                globalMax = Math.max(globalMax, localMin);
                //System.out.println(node+" / "+localMin);
                localMin = Integer.MAX_VALUE;
            }
            else {
                int distanceFromDan = fromDan[node].distance;
                localMin = Math.min(localMin, distanceFromDan);
                //System.out.print(node+"("+distanceFromDan+") ");

                for(Integer n : r.from) {
                    stack.push(n);
                }
            }
        }

        return globalMax;
    }

    static Record[] dijkstra(int source, int dest) {
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        queue.add(null);

        Record[] table = new Record[n+1];
        
        Record src = new Record(source);
        src.distance = 0;
        src.marked=true;
        table[source] = src;

        boolean found = false;

        while (queue.size()>0) {
            Integer node = queue.poll();
            
            if (node == null) {
                if (found)
                    break;
                else 
                    continue;
            }

            for (Integer neighbour: graph[node]) {
                
                if (table[neighbour] ==  null) {
                    table[neighbour] = new Record(neighbour);
                }

                Record neighbourRecord = table[neighbour];

                int currentDistance = neighbourRecord.distance;
                int newDistance = table[node].distance + 1;

                if (currentDistance > newDistance) {
                    neighbourRecord.distance = newDistance;
                    neighbourRecord.from.clear();
                    neighbourRecord.from.add(node);
                }
                else if (currentDistance == newDistance) {
                    neighbourRecord.from.add(node);
                }

                if (neighbour == dest)
                    found = true;

                if (!table[neighbour].marked) {
                    queue.add(neighbour);
                    table[neighbour].marked = true;
                }
            }

            queue.add(null);
        }
        
        return table;
    }
    
    static class Record
    {
        int node;
        Set<Integer> from;
        int distance;
        boolean marked;

        public Record(int n) {
            distance = Integer.MAX_VALUE;
            node = n;
            from = new HashSet<>();
            marked = false;
        }
    }

    static void addEdge(int a, int b) {
        if (graph[a]==null)
            graph[a] = new ArrayList<>();

        if (graph[b]==null)
            graph[b] = new ArrayList<>();

        graph[a].add(b);
        graph[b].add(a);
    }

    public static void warn(String s) {
        if (DEBUG) {
            System.out.println(s);
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

}

