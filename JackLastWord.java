import java.util.Arrays;
import java.io.*;
import java.util.*;

public class JackLastWord
{
    
    public static class CyclicHash
    {
        public static final long x = 1103515245L;
        public static final long m = 1L << 31;
        
        long val = 0;
        long lastX = 1;

        public void eat(char c) {
            val = ((val * x) % m + (long)c)%m;
            warn("eat = "+val);
        }

        public void reat(char c) {
            val = (val + ( (long)c * lastX) % m ) % m;
            lastX = (lastX * x) % m;
            warn("reat = "+val);
        }
        
        public long getVal() {
            return val;
        }
    }

    public static void main(String[] argv)
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] source = null;
        char[] jackWord = null;

        try {
            source = br.readLine().toCharArray();
            jackWord = br.readLine().toCharArray();
        }
        catch (Exception e) {
        }
        
        //1 2 3 4 5 6
        //5 6 7 1 1 2

        Set<Long> prefixesHashes = new HashSet<>();

        CyclicHash ch = new CyclicHash();

        for (int i = 0; i < source.length; i++) {
            ch.eat(source[i]);
            prefixesHashes.add(ch.getVal());
        }

        int lastWordEnd = jackWord.length;
        Stack<String> substrings = new Stack<>();

        boolean found = false;

        CyclicHash ch2 = new CyclicHash();

        for (int i = jackWord.length-1; i >=0; i--) {
            found=false;
            ch2.reat(jackWord[i]);

            if (prefixesHashes.contains(ch2.getVal()) && isPrefix(source, jackWord, i, lastWordEnd)) {
                substrings.push(new String( jackWord, i, lastWordEnd - i));
                lastWordEnd = i;
                found = true;
                ch2 = new CyclicHash();
            }
        }

        if(found) {
            System.out.println("No");
            while(substrings.size()> 0) {
                System.out.print(substrings.pop()+" ");
            }
            System.out.println();
        }
        else {
            System.out.println("Yes");
        }
    }
    
    public static boolean isPrefix(char[] a, char[] b, int start, int end) 
    {
        int pos = 0;


        while (pos < a.length && start < end) {
            if (a[pos] != b[start]) {
                return false;
            }
            pos++;
            start++;
        }
        
        return start == end ? true : false;
    }

    public static boolean DEBUG=false;

    public static void warn(String s) {
        if (DEBUG) {
            System.out.println(s);
        }
    }

}
