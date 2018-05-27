import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class MagneticStorm
{

    protected static class MyDeque
    {
        Deque<Integer> values;
        Deque<Integer> counters;
        int size;

        public MyDeque() {
            values = new ArrayDeque<>();
            counters = new ArrayDeque<>();
            size = 0;
        }

        public int getMax() {
            return values.peekFirst();
        }

        public int size() {
            return size;
        }
        
        public void removeFirst() {
            if (counters.peekFirst()>1) {
                counters.addFirst( counters.removeFirst()-1);
            }
            else {
                counters.removeFirst();
                values.removeFirst();
            }
            size--;
        }
        
        public void addLast(int v) {
            
            int s = 0;

            while (values.size() > 0 && values.peekLast() < v) {
                values.removeLast();
                s += counters.removeLast();
            }
            
            values.addLast(v);
            counters.addLast(s+1);
            size++;
        }

    }

    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        
        int window = in.nextInt();
        
        MyDeque deque = new MyDeque();

        while (true) {
            int n = in.nextInt();
            if (n == -1) 
                break;
            
            if (deque.size() >= window) {
                deque.removeFirst();
            }
            
            deque.addLast(n);

            if (deque.size() >= window) {
                System.out.println(deque.getMax());
            }

            //System.out.println("POS: "+deque.size()+" ADD: "+n);
        }

    }

}
