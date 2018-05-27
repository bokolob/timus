import java.util.stream.*;
import java.util.*;
import java.io.*;
//запускаем бфс из двух вершин компоненты связанности. первый запуск найдет  лист из максимального пути, а второй найдет его целиком.

public class HappinesBFS
{
    static int arrivingCost[];
    static int degree[];
    static int visited[];
    static int neighbours[];
    public static Map<Integer, Map<Integer, Integer>> incidentMatrix;
    static int n;

    public static long bfs_search(int leaf, int prev) {
        long cost = arrivingCost[leaf];
        visited[leaf] = 1;
        neighbours[leaf] = -1;
        
        for(int nextNeighbour: incidentMatrix.get(leaf).keySet()) {
            if (prev >= 0 && nextNeighbour == prev)
                continue;
            
            long r = bfs_search(nextNeighbour, leaf) + incidentMatrix.get(leaf).get(nextNeighbour) + arrivingCost[leaf];

            if (r > cost) {
                cost = r;
                neighbours[leaf] = nextNeighbour;
            }
        }

        //System.out.println("Cache "+leaf+" "+prev+"="+cost);

        return cost;
    }
    
    static long max_cost =-1;

    public static int bfs_calc() {
        int max_i = 0;

        for(int i = 0; i < n; i++) {
            if (degree[i] > 1 || visited[i] == 1)
                continue;

            bfs_search(i, -1);
            
            int last = i;
            
            while (neighbours[last] != -1)
                last = neighbours[last];
            
            long c = bfs_search(last, -1);

            if (c > max_cost) {
                max_cost = c;
                max_i = last;
            }

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
        degree = new int[n];
        visited = new int[n];
        neighbours = new int[n];

        for (int i = 0; i < n ;i++) {
            arrivingCost[i] = in.nextInt();
            incidentMatrix.put(i, new HashMap<>());
        }

        for (int i = 0; i < connected; i++) {
            int x = in.nextInt()-1;
            int y = in.nextInt()-1;
            int cost = in.nextInt();
            
            degree[x]++;
            degree[y]++;

            incidentMatrix.get(x).put(y,cost);
            incidentMatrix.get(y).put(x,cost);
        }

        int last = bfs_calc();
        
        List<Integer> route = new ArrayList<>();
        
        route.add(last+1);
        while (neighbours[last] != -1) {
            last = neighbours[last];
            route.add(last+1);
        }

        System.out.println(max_cost);
        System.out.println(route.size());
        
        for(int i = 0; i < route.size(); i++) {
            System.out.print(route.get(i)+" ");
        }

        System.out.println("");

    }
}


