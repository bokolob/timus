public class DSU
{
    int parent[];
    int size[];
    int marks[];
    int count;

    public DSU(int elems) {
        parent = new int[elems+1];
        size = new int[elems+1];
        marks = new int[elems+1];
        count = 0;
    }

    public int findSet(int n) {

        if (parent[n] != n) {
            parent[n] = findSet(parent[n]);
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
        if (parent[n] == 0) {
            parent[n] = n;
            size[n] = 1;
            count++;
        }
    }

}
