import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Sandro
{

    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        
        char input[] = in.next().toCharArray();
        char sandro[] = {'S','a','n','d','r','o'};
        Set<Char> sandro_set = new HashSet<>();
        
        /*
         *    a  a  a  a  a  a  A  A  A  A  A  A
         * S 10 10 10 10 10 10  5  -  -  -  -  -
         * a  - 10 10 10 10 10 15 10  -  -  -  -
         * n  -  - 15 15 15 15 20 25 20  -  -  -
         * d  -  -  - 20 20 
         * r
         * o
         *
         *    M  y  N  a  m   e   I   s   A   l   e   x   a   n   d   e   r
           S  5 10  5 10 10  10   5   5   5  10  10  10   -   -   -   -   -
           a  - 10 20  5 15  15  20  10  15  10  15  15  10   -   -   -   -
           n  -  - 15 25 10  20  25  25  20  20  15  20  20  10   -   -   -
           d                                                     10   -   -
           r                                                         15   -
           o                                                             20

         * -  -  2  1  -   -   -   0   1   -   -   -   1   2   3   0   4
         *       5
         *
         * 5  0  5  0  0   0   0   0   5   0   0   0   0   0   0   0   0
         * 5  5  0  
         *
         *
         * 3  2  1  1  2   2   3   1   1   2   2   2   1   1   1   1   1
         * 3  5  6  7  9  11  14  15  16  17  19  21  22  23  24  25  26
         * цена
         * 11 11 10 9  10 10  10   8   8  8    8   7   -   -   -   -   -
         * ____________XXXXX
         *
         * AaaaaAAaaa
         * 1111111111
         */

        for (int i = 0; i < input.length; i++) {
            if (sandro_set.contains( input[i] )) {

            }
        }

    }
}
