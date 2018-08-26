import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class AwesomeBackupSystem
{
    public static final long modulo =1000000007;

    static Node[] nodes;
    static long[] results;
    static SqrtDecomposition sqrtDecompose;

    public static void main(String[] argv) {
        PrintWriter out = new PrintWriter(System.out);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        //test();
        int n = Integer.parseInt(getLine(br));

        nodes = new Node[n];
        results = new long[n];

        String[] defs = getLine(br).split(" ");
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(Long.parseLong(defs[i]));
        }
        
        for (int i = 0; i < n-1; i++) {
            String[] parts = getLine(br).split(" ");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            addEdge(x, y);
        }

        bfs();
        
        sqrtDecompose = new SqrtDecomposition(results);

        int reqs = Integer.parseInt(getLine(br));

        for (int i = 0; i < reqs; i++) {
            String[] parts = getLine(br).split(" ");
            int t = Integer.parseInt(parts[0]);
            int v = Integer.parseInt(parts[1]);

            v--;

            if (t == 1) {
                Node target = nodes[v];
                long cnt = results[ nodes[v].id ];

                if (target.parent != null) {
                    results[ target.parent.id ] = ( cnt + results[ target.parent.id ]  ) % modulo;
                }

                if (target.lchild >= 0) {
                    sqrtDecompose.add( sqrtDecompose.get(nodes[v].id ), target.lchild, target.rchild);
                }

            }
            else {
                System.out.println( sqrtDecompose.get(nodes[v].id ) + "");

//                System.out.println( results[ nodes[v].id ] + "");
            }
        }

    }

    static void bfs()
    {
        Queue<Node> queue = new LinkedList<>();
        
        queue.add(nodes[0]);
        int pos = 1;

        while (queue.size() > 0) {
            Node node = queue.poll();
            results[ node.id ] = node.count;
            
            node.lchild = pos;

            for (Node child: node.references) {
                if (child == node || child.processed)
                    continue;

                if (child.parent == null) {
                    child.parent=node;
                    child.id=pos++;

                    //System.out.println("Set id="+child.id);

                    node.rchild = child.id;

                    queue.add(child);
                }

            }

            if (node.lchild > node.rchild) {
                node.lchild = node.rchild = -1;
            }

            node.processed = true;

        }

    }

    static void addEdge(int x, int y) {
        x--;
        y--;
        
        nodes[x].references.add(nodes[y]);
        nodes[y].references.add(nodes[x]);
    }

    public static String getLine(BufferedReader br) {
        String s = null;

        try {
            s = br.readLine();
        }
        catch (Exception e) {
        }

        return s;
    }

    static class Node {
        Node parent;
        boolean processed;
        long count;
        int id;
        int lchild;
        int rchild;

        List<Node> references;

        public Node(long cnt) {
            references = new ArrayList<>();
            processed = false;
            count = cnt;
            parent = null;
            id = 0;
            lchild = 0;
            rchild = 0;
        }
    }

    static class SqrtDecomposition
    {
        long[] init_values;
        long[] blocks;
        int blockLen;
        
        public SqrtDecomposition(long[] iv) {
            init_values = iv;
            blockLen = (int)Math.sqrt(iv.length);
            blocks = new long[ iv.length/blockLen + 1 ];
        }

        void add(long value, int l, int r) {

            if (r < l)
                return ;

            if ( r/blockLen == l/blockLen) {
                while (l <= r) {
                    init_values[l] = (init_values[l]+value) % modulo;
                    l++;
                }
                return;
            }
            
  //          System.out.println("add("+value+","+l+","+r+")");
            
            while (l <= r && l % blockLen > 0) {
                init_values[l] = (init_values[l]+value) % modulo;
                l++;
            }
            

            int k = l / blockLen;
            int end = r / blockLen;

            while (k < end) {
                blocks[k] = (blocks[k]+value) % modulo;
                k++;
            }

            int j = end * blockLen;
            while (j <= r) {
                init_values[j] = (init_values[j]+value) % modulo;
                j++;
            }
        }

        long get(int k) {
            return ( init_values[k] + blocks[ k / blockLen ] ) % modulo;
        }

        
    }

    public static void test() {
        long[] tv = new long[11];
        long[] expected = new long[11];

        SqrtDecomposition sqrtDecompose = new SqrtDecomposition(tv);
        
        for (int start = 0; start < tv.length; start++) {
            for (int len = 0; len < tv.length - start; len++) {
                sqrtDecompose.add(3, start, start+len-1);

                for (int k  = start; k < start+len; k++) {
                    expected[k]+=3;
                }


                boolean ok = true;

                for (int j  =0; j < expected.length; j++) {
                    if (sqrtDecompose.get(j) != expected[j]) {
                        ok = false;
                    }
                }

                if (ok) {
                    System.out.print("Ok "+start+" "+len+" "+Arrays.toString(expected));
                    for (int j  =0; j < expected.length; j++) {
                        System.out.print(" "+sqrtDecompose.get(j));
                    }
                    System.out.println("");
                }
                else {
                    System.out.print("Fail "+start+" "+len+" "+Arrays.toString(expected));
                    for (int j  =0; j < expected.length; j++) {
                        System.out.print(" "+sqrtDecompose.get(j));
                    }
                    System.out.println("");
                }

            }
        }

        System.out.println("--------------");

    }


}
    
