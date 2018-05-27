import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class  DistanceInTree
{

    public static class DSU
    {
        int parent[];
        int size[];
        int marks[];
        int count;

        public DSU(int elems) {
            parent = new int[elems+1];
        
            for (int i = 0; i < parent.length; i++) 
                parent[i] = -1;

            size = new int[elems+1];
            marks = new int[elems+1];
            count = 0;
        }

        public int findSet(int n) {
            
            int p = parent[n];

            if (p != n) {
                parent[n] = findSet(p);
            }
            
            return parent[n];
        }
        
        public void setMark(int s, int value) {
            marks[ findSet(s) ] = value;
        }
        
        public int getMark(int s) {
            return marks[ findSet(s) ];
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
            if (parent[n] == -1) {
                parent[n] = n;
                size[n] = 1;
                count++;
            }
        }

    }
    public static class Graph {
        //private Map<Integer, Map<Integer, Integer>> incidentMatrix;
        
        private  Map<Integer, Integer>[] incidentMatrix;

        @SuppressWarnings("unchecked")
        public Graph(int n) {
            incidentMatrix = new HashMap[n];
            //incidentMatrix = new HashMap<>();
        }

        public Set<Integer> neighbours(int node) {
            return incidentMatrix[node].keySet();
        }
        
        public int weight(int a, int b) {
            return incidentMatrix[a].get(b);
        }

        public void addNode(int a) {
            if (incidentMatrix[a] == null) {
                incidentMatrix[a] = new HashMap<>();
            }
        }

        public void addEdge(int a, int b, int w) {
            
            //System.out.println("addEdge: "+a+" "+b);

            if (incidentMatrix[a] == null) {
                incidentMatrix[a] =  new HashMap<>();
            }
            
            incidentMatrix[a].put(b, w);
            
            if (incidentMatrix[b]==null) {
                incidentMatrix[b] = new HashMap<>();
            }

            incidentMatrix[b].put(a, w);
            
            //System.out.println(Arrays.toString(incidentMatrix.keySet().toArray()));
        }
        
    }
    
    static long[] distances;
    static int[] parents;
    static Map<Integer, Map<Integer, Integer>> LCA;
    static DSU dsu;
    static boolean[] visited;

    public static void dfsRecurse(Graph graph, int node) {
        distances[node] = 0;
        parents[node] = node;
        _dfsRecurse(graph, node); 
    }

    public static void _dfsRecurse(Graph graph, int node) {
        for (Integer child: graph.neighbours(node)) {
            if (child == parents[node])
                continue;
            
            int cost = graph.weight(node, child);

            parents[child] = node;
            distances[child] = distances[node]+cost; 
            
            // System.out.println("Go to "+child+" from "+node);
            _dfsRecurse(graph, child);
        }
        
        processPostOrder(graph, node);
    }

    public static void processPostOrder(Graph graph, int vertex) {
        //System.out.println("postOrder: "+vertex);

        if (LCA.containsKey(vertex)) {
            for (Integer pair: LCA.get(vertex).keySet()) {
                //System.out.println("visited: "+visited);
                if (visited[pair]==true) {
                   int pairSet = dsu.findSet(pair);
                   //System.out.println("getMark:"+dsu.getMark(pairSet)); 
                   setLCA(vertex, pair, dsu.getMark(pairSet));
                }
            }
        }
        
        dsu.unionSet( vertex, dsu.findSet(parents[vertex]));
        dsu.setMark(vertex, parents[vertex]);
        
        //System.out.println("connect "+vertex+" to "+parents[vertex]);

        visited[vertex]=true;
    }
    
    public static void setLCA(int u, int v, int lca) {
         //System.out.println("setLCA("+u+", "+v+", "+lca);
         LCA.get(u).put(v, lca);
    }

    public static long findDistance(int v, int u) {
        if (v == u) {
            return 0;
        }

        Integer lca = LCA.get(v).get(u);

        if (lca == null)
            lca = LCA.get(u).get(v);

//        System.out.println("pu="+u+" pv="+v+" lca="+lca+" "+LCA);

        return distances[u] - distances[lca]*2L + distances[v];
    }
    
    public static Graph readTree(BufferedReader br) {
        
        String s = getLine(br);

        int nodes = Integer.parseInt(s);
        Graph graph = new Graph(nodes);
        dsu = new DSU(nodes);
    
        if (nodes == 1) {
            graph.addNode(0);
            dsu.makeSet(0);
        }

        distances = new long[nodes];
        parents   = new int[nodes]; 
        visited = new boolean[nodes];
        
        constructGraph(br, graph, nodes);

        return graph;
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

    public static void constructGraph(BufferedReader br, Graph graph, int nodes)  {
        while (nodes-- > 1) {
            String[] s = getLine(br).split(" ");

            int u = Integer.parseInt(s[0]); 
            int v = Integer.parseInt(s[1]);
            int w = Integer.parseInt(s[2]);
            
            dsu.makeSet(u);
            dsu.makeSet(v);

            graph.addEdge(u,v, w);
        }
    }

    public static void main(String[] argv) {
        //Scanner in = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        Graph graph = readTree(br);

        //Random       random    = new Random();
        //int root = random.nextInt(nodes);
        int root = 0;
        
        LCA = new LinkedHashMap<>(16, 0.75f, false);
        
        String s2 = getLine(br);
        int queries = Integer.parseInt(s2);
        
        //System.out.println("SSS "+queries);
        
        Queue<Integer> q = new LinkedList<>();

        while(queries-- > 0) {
            String[] s = getLine(br).split(" ");
            int u = Integer.parseInt(s[0]); 
            int v = Integer.parseInt(s[1]);
            
            addQuery(u,v);
            addQuery(v,u);
            q.add(v);
            q.add(u);
        }

        dfsRecurse(graph, root);

        while (q.size() > 0) {
              System.out.println(findDistance(q.remove(), q.remove()));
        }

    }

    public static void addQuery(int u, int v) {
        if (!LCA.containsKey(u)) {
            LCA.put(u, new LinkedHashMap<>(16, 0.75f, false));
        }
        
        LCA.get(u).put(v,null);
    }
}

