import java.util.*;
import java.io.*;
import java.text.DecimalFormat;
import java.math.RoundingMode;

public class Werewolf
{
    static boolean DEBUG = true;
    
    public static class Node
    {
        int N;
        boolean marked;
        Set<Node> parents;
        Set<Node> children;

        public Node(int N) {
            this.N = N;
            parents = new HashSet<>();
            children = new HashSet<>();
            marked = false;
        }
    }


    public static void main(String[] argv) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int inhabitants = Integer.parseInt(getLine(br));
        Node[] village = new Node[inhabitants+1];

        for (int i = 1; i <= inhabitants; i++) 
            village[i] = new Node(i);
        
        String input;

        while(true) {
            input = getLine(br);

            if ("BLOOD".equals(input)) {
                break;
            }

            int pos = input.indexOf(" ");

            int child = Integer.parseInt(input.substring(0,pos));
            int parent = Integer.parseInt(input.substring(pos+1));

            village[child].parents.add( village[parent] );
            village[parent].children.add( village[child] );
        }

        while (true) {
            input = getLine(br);

            if (input == null) 
                break;

            int victim = Integer.parseInt(input);
            
            markParents(village[victim]);
            markChildren(village[victim]);
            village[victim].marked=true;
        }
        
        boolean found = false;
        for (int i =1 ; i <= inhabitants; i++) {
            if (!village[i].marked) {
                System.out.print(i+" ");
                found = true;
            }
        }

        if (found) 
            System.out.println("");
        else 
            System.out.println("0");

    }

    static void markParents(Node node) {
        Stack<Node> stack = new Stack<>();

        stack.addAll(node.parents);

        while (!stack.empty()) {
            Node n = stack.pop();
            n.marked = true;

            stack.addAll(n.parents);
        }

    }

    static void markChildren(Node node) {
        Stack<Node> stack = new Stack<>();

        stack.addAll(node.children);

        while (!stack.empty()) {
            Node n = stack.pop();
            n.marked = true;

            stack.addAll(n.children);
        }

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
    

    public static void warn(String s) {
        if (DEBUG) {
            System.out.println(s);
        }
    }
}
