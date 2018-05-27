import java.util.*;
import java.io.*;

/*
 * A[i][k] - минимальный штра для строки диной k в которйо ровно i друзей добавлено
 * Мы лиюо добавляем 1 в текущую позицию, либо не добавляем
 * A[i][k] = min( A[i][k-1] + штраф_в_тек_позиции, A[i-1][k-1] + штраф_за_новую_единицу )
 * 
 * Одновременно заполняем таблицу с позициями
 * */

public class Decimation
{
    static int n;
    static int k;
    
    static int table[][];
    static int positions[][];
    
    static protected void prepare(char data[]) {
        int cnt = 0;
        for (int i = 0; i < data.length; i++) {
            if ((i+1) % 10 == 0 && data[i]=='1') {
                cnt++;
            }

            table[0][i] = cnt;
            positions[0][i] = 0;
        }
    }
    
    static protected void fillTable(char data[]) {
        for (int i = 0; i < data.length+k; i++) {
            for (int j = 1; j <= k; j++) {
                if (i < j || i >= data.length + j) {
                    table[j][i] = 0;
                    positions[j][i] = 1;
                    continue;
                }
                else {
                    
                    int in_current_pos = ((data[ i - j ] == '1') && ((i+1) % 10 == 0)) ? 1 : 0;
                    int new_one_cost = ((i+1) % 10 == 0) ? 1 : 0;

                    if (table[j][i - 1] + in_current_pos < table[j -1][i - 1] + new_one_cost) {
                        table[j][i] = table[j][i - 1] + in_current_pos;
                        positions[j][i] = 0;
                    }
                    else {
                        table[j][i] = table[j -1][i - 1] + new_one_cost;
                        positions[j][i] = 1;
                    }
                }
            }
        }
    }

    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        
        n = in.nextInt();
        k = in.nextInt();

        char data[] = in.next().toCharArray();

        table = new int[k+1][n+k];
        positions = new int[k+1][n+k];
        
        prepare(data);
        fillTable(data);

        int min_penalty=Integer.MAX_VALUE;
        int min_cnt=0;

        for (int i = 0; i <= k; i++) {
            if (table[i][data.length + i - 1] < min_penalty) {
                min_penalty = table[i][data.length+i-1];
                min_cnt = i;
            }
        }

        System.out.print(""+min_penalty+"\n"+min_cnt+" ");
        
        int start = data.length + min_cnt - 1;
        while(min_cnt >= 0 && start >=0) {

            while (start >=0 && positions[min_cnt][start]==0 && start >= min_cnt) {
                start--;
            }
            
            if (start >=0 && positions[min_cnt][start]==1) {
                System.out.print(""+(start+1)+" ");
                min_cnt--;
                start--;
            }
        }
        
         System.out.println("");
    }

}
