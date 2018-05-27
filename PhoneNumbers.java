import java.util.*;
import java.io.*;

/*
 * 1 ij    2 abc   3 def
 * 4 gh    5 kl    6 mn
 * 7 prs   8 tuv   9 wxy
 *         0 oqz
 * 
 * */

public class PhoneNumbers
{

    public static char[] decode(String str)
    {
        char out[] = new char[str.length()];

        for (int i = 0; i < str.length(); i++) {
            switch(str.charAt(i)) {
                case 'i' :
                case 'j' :
                    out[i] = '1';
                    break;
                
                case 'a':
                case 'b':
                case 'c':
                    out[i]='2';
                    break;
                
                case 'd':
                case 'e':
                case 'f':
                    out[i]='3';
                    break;
                
                case 'g':
                case 'h':
                    out[i]='4';
                    break;
                
                case 'k':
                case 'l':
                    out[i]='5';
                    break;

                case 'm':
                case 'n':
                    out[i]='6';
                    break;
                
                case 'p':
                case 'r':
                case 's':
                    out[i]='7';
                    break;
                
                case 't':
                case 'u':
                case 'v':
                    out[i]='8';
                    break;
                
                case 'w':
                case 'x':
                case 'y':
                    out[i]='9';
                    break;

                case 'o':
                case 'q':
                case 'z':
                    out[i]='0';
                    break;

            };
        }
        
        return out;
    }


    public static class Trie {
        public Node root;

        public Trie() 
        {
            root = new Node();
        }

        public List<Integer> getAllWords(String text, int pos) {

            Trie.Node node = root;

            List<Integer> list = new ArrayList<>();

            for (int i = pos; i < text.length() &&  node != null; i++) {
                char c = text.charAt(i);

                node = node.next(c);

                if (node != null && node.is_end) {
                    list.add(node.mark);
                }

            }

            //System.out.println("getAllWords: "+pos+" "+list);
                
            return list;
        }
        
        public void add(char[] str, int mark) {
            Node pos = root;

            for (int i = 0; i < str.length; i++) {
                pos = pos.add(str[i]);
            }

            pos.is_end = true;
            pos.mark = mark;

            //System.out.println("mrk: "+mark);

        }

        public static class Node 
        {
            Map<Character, Node> refs;
            Set<Integer> visited;
            int mark;
            boolean is_end;

            public Node() {
                refs = new HashMap<>();
                is_end = false;
                visited = null;
            }

            public Node add(char c) {
                if (refs.containsKey(c)) {
                    return refs.get(c);
                }
                else {
                    Node n = new Node();
                    refs.put(c, n);
                    return n;
                }
            }
            
            public boolean wasVisitedAt(int position) {
                return visited != null && visited.contains(position);
            }

            public void visitedAt(int position) {
                if (visited == null) {
                    visited = new HashSet<>();
                }

                visited.add(position);
            }

            public Node next(char c) {
                if (refs.containsKey(c)) {
                    return refs.get(c);
                }
                
                return null;
            }

        }
    }


    public static List<Integer> getDecoding(Trie trie, String str, String[] dict) {
        int[] cache = new int[str.length()];
        int[] minWords = new int[str.length()];

        for (int i = str.length() -1; i >= 0; i--) {
            List<Integer> words = trie.getAllWords(str, i);
            
            int minima = Integer.MAX_VALUE;
            int bestWord = -1;

            for (Integer w: words) {
                int len = dict[w].length();
                
               // System.out.println("Trying "+dict.get(w)+" i="+i+" len="+len+" cache.l="+cache.length);

                if (i+len > cache.length)
                    continue;

                int rc;

                if (i+len == cache.length) {
                    rc = 1;
                }
                else {
                    if (cache[i+len] == Integer.MAX_VALUE)
                        continue;

                    rc = 1 + cache[i+len];
                }

               // System.out.println("rc= "+rc);

                if (rc < minima) {
                    minima = rc;
                    bestWord = w;
                }
            }

            cache[i] = minima;
            minWords[i] = bestWord;
        }

        List<Integer> results = new ArrayList<>();
        
        for (int j = 0; j < minWords.length; j += dict[minWords[j]].length()) {

            if (minWords[j]==-1)
                return null;

            results.add(minWords[j]);
        }

        return results;
        
    }



    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        
        while(true) {
            String number = in.next();
            
            if (number.equals("-1")) {
                break;
            }
        
            Trie trie = new Trie();

            int n = in.nextInt();
            
            //List<String> words = new ArrayList<>();
            String[] words = new String[n];

            for (int j = 0; j < n; j++) {
                String s = in.next();
                words[j]=s;

                //System.out.println(s+" = "+String.valueOf(decode(s)));

                trie.add( decode(s), j);
            }
            
            //System.out.println(number);
            List<Integer> minimalText = getDecoding(trie, number, words);
            
            if (minimalText == null) {
                System.out.println("No solution.");
            }
            else {
                for(Integer i: minimalText) {
                    System.out.print(words[i]);
                    System.out.print(" ");
                }
                System.out.println("");
            }

        }
        
    }
}

