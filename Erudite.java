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
        
        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 4; j++) {
                if (table[i][j] == pattern[0]) {
                    boolean[] visited = new boolean[16];
                    warn("Start from "+ (i*4+j));
                    if (dfsSearch(table, i*4+j, pattern, 0, visited)) {
                        System.out.println(s+": YES");
                        return true;
                    }
                }
            }
        }

        System.out.println(s+": NO");
        return false;
    }
    
    public static boolean dfsSearch(char[][] table, int p, char[] pattern, int patternPos, boolean[] visited) {
        if (patternPos > pattern.length-1)
            return false;

        if (patternPos == pattern.length-1)
            return true;

        visited[p]=true;

        if (p-4>=0 && !visited[p-4] && table[(p-4)/4][(p-4)%4] == pattern[patternPos]) {
            warn("1 "+p);
            if (dfsSearch(table,p-4, pattern, patternPos+1, visited)) {
                return true;
            }
        }
        if ( p+4 < 16  && !visited[p+4] && table[(p+4)/4][(p+4)%4] == pattern[patternPos]) {
            warn("2 "+p);
            if (dfsSearch(table,p+4, pattern, patternPos+1, visited)) {
                return true;
            }
        }
        if (p-1>=0 && !visited[p-1] && table[(p-1)/4][(p-1)%4] == pattern[patternPos]) {
            warn("3 "+p);
            if (dfsSearch(table,p-1, pattern, patternPos+1, visited)) {
                return true;
            }
        }
        if (p+1 < 16 && !visited[p+1] && table[(p+1)/4][(p+1)%4] == pattern[patternPos]) {
            warn("4 "+p);
            if (dfsSearch(table,p+1, pattern, patternPos+1, visited)) {
                return true;
            }
        }

        visited[p]=false;
        return false;
    }

    public static final boolean DEBUG = true;
    public static void warn(String s) {
        if (DEBUG) {
            System.out.println(s);
        }
    }
}
