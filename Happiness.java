import java.util.stream.*;
import java.util.*;
import java.io.*;
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.text.*;

public class Happiness
{

    protected static class Entry
    {
        Set<Integer> vertices;
        long cost;
        int prev;
    }

    //table[i][j] - это максимальаня цена пути из i ребер, заканчивающегося вершиной j
    static Entry[][] table;
    static int arrivingCost[];
    static int degree[];
    public static Map<Integer, Map<Integer, Integer>> incidentMatrix;
    static int n;
    
    static long bfs_cache[][];
    static long bfs_routes[][];

    public static int bfs_search(int leaf, int prev) {
        long cost = arrivingCost[leaf];
        int next;

        if (bfs_cache[leaf][prev] != -1) {
            return bfs_cache[leaf][prev];
        }

        for(int nextNeighbour: incidentMatrix.get(leaf).keySet()) {
            if (prev >= 0 && nextNeighbour == prev)
                continue;
            
            long r = bfs_search(nextNeighbour, leaf) + incidentMatrix.get(leaf).get(nextNeighbour);

            if (r > cost) {
                cost = r;
                next = nextNeighbour;
            }
        }
        
        bfs_cache[leaf][prev] = cost;

        return cost;
    }
    
    public static int bfs_calc() {
        long max_cost = -1;
        int max_i;

        for(int i = 0; i < n; i++) {
            if (degree[i] > 1)
                continue;

            long c = bfs_search(i, -1);
            
            if (c > max_cost) {
                max_cost = c;
                max_i = i;
            }
        }
        
        return max_i;
    }

    public static int calc() {
        
        boolean updated = true;
        int max_i=-1;
        long maxCost = -1;

        for (int i = 0; i < n ; i++) {
            if (table[0][i].cost > maxCost) {
                maxCost = table[0][i].cost;
                max_i = 0;
            }
        }

        for (int i = 1; i < n && updated; i++) {
            updated = false;
            int tmp[] = new int[n];

            for (int j = 0; j < n; j++) {
                Entry newEntry = new Entry();
                table[i][j] = newEntry;

                int m  = -1;
                newEntry.cost = -1;

                //table[i][j] = элемент из table[i-1] в который не входит j и у котого максимальная цена
                for (Integer vertex: incidentMatrix.get(j).keySet()) {
                    if ( table[i-1][vertex].vertices == null  )
                        continue;

                    long newCost = table[i-1][vertex].cost + incidentMatrix.get(j).get(vertex) +arrivingCost[j];

                    if (!table[i-1][vertex].vertices.contains(j) && newCost > newEntry.cost) {
                        newEntry.cost = newCost; 
                        newEntry.prev = vertex;

                        m = vertex;
                        updated = true;

                        if (newCost > maxCost) {
                            maxCost = newCost;
                            max_i = i;
                        }
                    }
                }
                
                if (m != -1) {
                    tmp[j] = m;
                    newEntry.vertices = new HashSet<>(table[i-1][m].vertices);
                    newEntry.vertices.add(j);

                }
                else {
                    tmp[j] = -1;
                }
            }

/*            for (int k = 0; k < n ; k++) {
                if ( table[i][k] != null && table[i][k].vertices != null  ) {
                    table[i][k].vertices.add(tmp[k]);
                }
            }*/
            
            if (!updated)
                break;
        }

        return max_i;
    }

    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        
        n = in.nextInt();
        int connected = in.nextInt();
        
        arrivingCost = new int[n];
        incidentMatrix = new HashMap<>();
        table = new Entry[n][n];

        for (int i = 0; i < n ;i++) {
            arrivingCost[i] = in.nextInt();
            incidentMatrix.put(i, new HashMap<>());
        }

        for (int i = 0; i < connected; i++) {
            int x = in.nextInt()-1;
            int y = in.nextInt()-1;
            int cost = in.nextInt();

            incidentMatrix.get(x).put(y,cost);
            incidentMatrix.get(y).put(x,cost);
        }
        
        for (int i = 0; i < n ; i++) {
            table[0][i] = new Entry();
            table[0][i].cost = arrivingCost[i];
            table[0][i].vertices = new HashSet<>();
            table[0][i].vertices.add(i);
            table[0][i].prev = -1;
        }

        int p = calc();
                
        int max=-1;
        long o = 0;

        for (int i = 0; i < n ; i++) {
            if (table[p][i] != null) {
                if (table[p][i].cost > o) {
                    o = table[p][i].cost;
                    max = i;
                }
            }
        }
        
       // System.out.println("cost="+table[p][max].cost);
       // System.out.println("len="+table[p][max].vertices.size());

        
        System.out.println(o+"\n"+(table[p][max].vertices.size()));

        int prev = max;

        while (prev != -1) {
            System.out.print((prev+1)+" ");
            prev = table[p][prev].prev;
            p--;
        }
        System.out.println("");

    }
}


