import java.util.*;
import java.io.*;

public class Electrification
{
    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n,k;

        n = in.nextInt();
        k = in.nextInt();

        int adjacencyMatrix[][] = new int[n][n];

        Set<Integer> powers = new HashSet<>();
        Set<Integer> cities = new HashSet<>(); 

        int sum = 0;

        for (int i = 0; i < k; i++)
            powers.add(in.nextInt()-1);

        for (int i = 0; i < n ; i++) {

            if (!powers.contains(i))
                cities.add(i);

            for (int j = 0; j < n; j++) {
                adjacencyMatrix[i][j]=in.nextInt();
            }
        }


        while (cities.size() > 0) {
            int minLen = Integer.MAX_VALUE;
            int v = 0;

            for (int i : cities) {
                for (int j : powers) {
                    if (adjacencyMatrix[i][j] < minLen) {
                        minLen = adjacencyMatrix[i][j];
                        v = i;
                    }
                }
            }

            sum += minLen;
            powers.add(v);
            cities.remove(v);
        }

        System.out.println(sum);
    }

    public static void floyd(int[][] dist, int[][] next) {
        for (int k = 0; k < dist.length; k++) {
            for (int i = 0; i < dist.length; i++) {
                for (int j = 0; j < dist.length; j++) {
                    if (dist[i][j] > dist[i][k]+dist[k][j]) {
                        dist[i][j] = dist[i][k]+dist[k][j];
                        next[i][j] = k;
                    }
                }
            }
        }
    }
    public static void   printTable(int[][] table) {
        System.out.println("------------------");
        for (int i = 0; i < table.length; i++) {
            System.out.println(Arrays.toString(table[i]));
        }
    }

}
