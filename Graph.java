
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

    public static class Graph {
        private Map<Integer, Map<Integer, Boolean>> incidentMatrix;
        private List<Edge> edges; // = new ArrayList<>();
        
        public Graph() {
            incidentMatrix = new HashMap<>();
            edges = new ArrayList<>();
        }
        
        public Set<Integer> vertices() {
            return incidentMatrix.keySet();
        }

        public void addEdge(Edge edge) {
            int a = edge.getFirst();
            int b = edge.getSecond();
            
            //System.out.println("addEdge: "+a+" "+b);

            if (!incidentMatrix.containsKey(a)) {
                incidentMatrix.put(a, new HashMap<>());
            }
            
            incidentMatrix.get(a).put(b,true);
            
            if (!incidentMatrix.containsKey(b)) {
                incidentMatrix.put(b, new HashMap<>());
            }

            incidentMatrix.get(b).put(a,true);
            
            edges.add( edge );
            //System.out.println(Arrays.toString(incidentMatrix.keySet().toArray()));
        }
        
        public void addNode(int a) {
            if (!incidentMatrix.containsKey(a)) {
                incidentMatrix.put(a, new HashMap<>());
            }
        }

        public boolean containsEdge(Edge edge) {
            return incidentMatrix.containsKey(edge.getFirst()) 
                    && incidentMatrix.get(edge.getFirst()).containsKey(edge.getSecond());
        }

        public Edge getEdgeByNumber(int n) {
            return edges.get(n-1);
        }

        public void removeEdge(Edge edge) {
            int a = edge.getFirst();
            int b = edge.getSecond();
            
            incidentMatrix.get(a).remove(b);
            incidentMatrix.get(b).remove(a);
        }
        
        public void makeSkeleton(Integer root, Graph skeleton, Set<Integer> visited, Map<Integer, Graph> skeletons) {
            
            //System.out.println("makeSkeleton: "+root);
            skeletons.put(root, skeleton);
            
            //System.out.println(Arrays.toString(incidentMatrix.keySet().toArray()));

            for (Integer neighbour: incidentMatrix.get(root).keySet()) {
                if (visited.contains(neighbour)) {
                    //System.out.println("Skip: "+neighbour);
                    continue;
                }
                
                visited.add(neighbour);

                skeleton.addEdge( new Edge( root, neighbour) );
                makeSkeleton(neighbour, skeleton, visited, skeletons);
            }
        }

    }
    
    public static class DisjoinedSet {
        
        Map<Integer, Graph> skeletonsByVertex;
        int size;

        public DisjoinedSet(Graph graph) {
            skeletonsByVertex = new HashMap<>();
            Set<Integer> visited = new HashSet<>();


            size = 0;

            for (Integer vertex: graph.vertices()) {
                if (visited.contains(vertex))
                    continue;
                
                Graph skeleton = new Graph();
                //System.out.println("XmakeSkeleton: "+vertex);
                graph.makeSkeleton(vertex, skeleton, visited, skeletonsByVertex);
                size++;
            }
        }
        
        public Graph getSkeletonByVertex(int n) {
            return skeletonsByVertex.get(n);
        }
        
        public int size() {
            return size;
        }
        
        public void update(Graph source, Edge removed) {
            Set<Integer> visited = new HashSet<>();
            
            size--;

            int list[] = new int[]{ removed.getFirst(), removed.getSecond() };

            for (int vertex: list) {
                if (visited.contains(vertex))
                    continue;
                
                Graph skeleton = new Graph();
                source.makeSkeleton(vertex, skeleton, visited, skeletonsByVertex);
                size++;
            }

        }

    }
