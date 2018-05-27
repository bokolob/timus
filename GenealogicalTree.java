import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class  GenealogicalTree
{
    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        
        List<Set<Integer>> incidentMatrix = new ArrayList<>();
        
        int n = in.nextInt();

        for (int i = 0; i < n; i++) {
            int child = in.nextInt();
        
            Set<Integer> children = new HashSet<>();
            incidentMatrix.add(children);

            while (child != 0) {
                children.add(child-1);
                child = in.nextInt();
            }
        }
        
        Stack<Integer> result = new Stack<Integer>();

        dfs(result, incidentMatrix);
        
        while(result.size() > 0) {
            System.out.print((result.pop()+1)+" ");
        }

        System.out.println("");
    }

    public static void dfs(Stack<Integer> stack, List<Set<Integer>> incidentMatrix) {
        Set<Integer> visited = new HashSet<>();

        for (int vertex = 0; vertex < incidentMatrix.size(); vertex++) {
            if (!visited.contains(vertex)) {
                _dfs(vertex, stack, incidentMatrix, visited);
            }
        }
    }
    
    private static void _dfs(int root, Stack<Integer> stack, List<Set<Integer>> incidentMatrix, Set<Integer> visited) {
        
        visited.add(root);

        for(Integer child : incidentMatrix.get(root)) {
            if (visited.contains(child)) 
                continue;

            _dfs(child, stack, incidentMatrix, visited);
        }

        stack.push(root);
    }

}

