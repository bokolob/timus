import java.util.Arrays;
import java.io.*;
import java.util.*;

public class FarAwayKingdom
{
    
    
    static int[][] map = new int[9][9];
    static int pos = 1;

    static void fillCircle(int cx, int cy, int offset, int len) {
        int cnt = 4 * len - 4;
        int k = 0;
        int old_pos = pos;

        warn("len="+len+" cnt="+cnt);

        while (pos != old_pos+cnt && pos < 81) {
            prn();
            updateHor(cx,cy-offset,k);
            updateVert(cx+offset,cy,k);
            updateHor(cx,cy+offset,k);
            updateVert(cx-offset,cy,k);
            k++;
        }

        warn ("-----------------");
    }

    static void updateVert(int x, int y, int k) {

        warn("v: "+x+" "+y);
        
        if ( map[y-k-1][x-1] == 0)
            map[y-k-1][x-1] = pos++;
        
        if (k>0 &&  map[y+k-1][x-1] == 0) {
            map[y+k-1][x-1] = pos++;
        }
    }

    static void updateHor(int x, int y, int k) {
        warn("h: "+x+" "+y+" "+k);

        if (map[y-1][x - k-1] == 0)
            map[y-1][x - k-1] = pos++;
            
        if (k>0 && map[y-1][x+k-1]==0) {
            map[y-1][x+k-1] = pos++;
        }
    }
    
    public static boolean DEBUG=false;

    public static void warn(String s) {
        if (DEBUG) {
            System.out.println(s);
        }
    }

    public static void prn() {

        for (int i = 0; i<9;i++) {
            warn(Arrays.toString(map[i]));
        }
    }

    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in).useLocale(Locale.US);
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();

        for (int i = 1; i < 9; i++) {
            fillCircle(5,5,i,2*i+1);
        }

        prn();
        
        int cnt = n*n;
        Integer input[] = new Integer[cnt];
        
        while(cnt-- > 0) {
            input[cnt]=in.nextInt();
        }
        
        Arrays.sort(input, Collections.reverseOrder());

        warn(Arrays.toString(input));
        
        int printed = 0;

        for (int i = 0; i <9; i++) {
            for (int j = 0; j < 9; j++) {
                if (map[i][j] < input.length) {
                    System.out.print(input[ map[i][j] ]+" ");
                    printed++;

                    if (printed == n) {
                        printed=0;
                        System.out.println("");
                    }

                }

            }
        }


    }

}
