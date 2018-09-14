import java.util.*;
import java.io.*;

public class Erudite
{
    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in).useLocale(Locale.US);
        PrintWriter out = new PrintWriter(System.out);
        
        char table[][] = new char[4][4];

        for (int i = 0; i < 4; i++) {
            String s = in.next();
            for (int j = 0; j < 4; j++) {
                table[i][j] = s.charAt(j);
            }
        }
        

        int n = in.nextInt();

        while(n-- > 0) {
            check(table, in.next());
        }

    }

    public static boolean check(char[][] table, String s) {
        char pattern[] = s.toCharArray();
        boolean[] visited = new boolean[16];
        
        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 4; j++) {
                warn("Start from "+ i+ " "+j);
                if (dfsSearch(table, i, j, pattern, 0, visited)) {
                    System.out.println(s+": YES");
                    return true;
                }
            }
        }

        System.out.println(s+": NO");
        return false;
    }
    
    public static boolean dfsSearch(char[][] table, int x, int y, char[] pattern, int patternPos, boolean[] visited) {
        if (x < 0 || x > 3 || y < 0 || y > 3)
            return false;

        int p = y * 4 + x;

        if (visited[p])
            return false;

        if (table[y][x] != pattern[patternPos]) {
            return false;
        }
        
        if (patternPos == pattern.length-1) {
            warn("FOUND! "+" "+x+" "+y+" "+patternPos);
            return true;
        }
        
        warn("TRY! "+" "+x+" "+y+" "+patternPos+" "+table[y][x]);

        visited[p]=true;
        
        boolean result = dfsSearch(table, x, y - 1, pattern, patternPos+1, visited)
                         || dfsSearch(table, x, y + 1, pattern, patternPos+1, visited)
                         || dfsSearch(table, x + 1, y, pattern, patternPos+1, visited)
                         || dfsSearch(table, x-1, y, pattern, patternPos+1,visited); 

        visited[p]=false;
        return result;
    }

    public static final boolean DEBUG = false;
    public static void warn(String s) {
        if (DEBUG) {
            System.out.println(s);
        }
    }
}
