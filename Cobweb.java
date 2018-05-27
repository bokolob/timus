import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Cobweb
{
    public static class Edge {
        private int first;
        private int second;

        public Edge(int a, int b) {
           first = a;
           second = b;
        }

        public int getFirst() {
            return first;
        }

        public int getSecond() {
            return second;
        }
    }

    public static class DSU
    {
        int parent[];
        int size[];
        int count;

        public DSU(int elems) {
            parent = new int[elems+1];
            size = new int[elems+1];
            count = 0;
        }

        public int findSet(int n) {

            if (parent[n] != n) {
                parent[n] = findSet(parent[n]);
            }
            
            return parent[n];
        }

        public int count() {
            return count;
        }

        public void unionSet(int a, int b) {
            a = findSet(a);
            b = findSet(b);

            if (a == b)
                return;

            if (size[a] > size[b]) {
                int tmp = a;
                a = b;
                b = tmp;
            }

            parent[a] = b;
            size[b] += size[a];
            count--;
        }
        
        public void makeSet(int n) {
            if (parent[n] == 0) {
                parent[n] = n;
                size[n] = 1;
                count++;
            }
        }

    }

    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        
        int nodes = in.nextInt();
        int edges = in.nextInt();
       
        DSU dsu = new DSU(nodes);

        for (int i = 1; i <= nodes; i++)
            dsu.makeSet(i);

        Map<Integer, Edge> edgesList = new HashMap<>();

        for (int i = 1; i <= edges; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            
            edgesList.put(i, new Edge(a,b));
        }
        
        int forRemove = in.nextInt();
        
        List<Edge> removedList = new ArrayList<>();

        while(forRemove-- > 0) {
            int k = in.nextInt();
            removedList.add( edgesList.get(k) );
            edgesList.remove(k);
        }
        
        for(Edge e: edgesList.values()) {
            dsu.unionSet(e.getFirst(), e.getSecond());
        }
        
        Stack<Integer> stack = new Stack<>();
        
        stack.push(dsu.count());
        
        for (int i = removedList.size()-1 ; i > 0; i--) {
            Edge e = removedList.get(i);
            dsu.unionSet(e.getFirst(), e.getSecond());
            stack.push(dsu.count());
        }

        while(stack.size() > 0) {
            System.out.print(stack.pop()+" ");
        }
        
        System.out.println("");
    }
}
