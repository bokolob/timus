import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class NestedSegments
{

    public static class Segment
    {
        static long number = 0;

        int beg;
        int end;
        int pos;
        long num;
        Segment parent;

        List<Segment> subsegments;
        List<Integer> coords;

        public Segment(int s, int e) {
            beg = s;
            end = e;
            pos = 0;
            num = number++;
            parent = null;
        }
        

        public void add(Segment s) {
            if (subsegments == null) {
                subsegments = new ArrayList<>();
                coords = new ArrayList<>();
            }
            
            //System.out.println(""+s+" added to "+this);
            
            s.parent = this;
            subsegments.add(s);
            coords.add(s.beg);
        }
        
        public String toString() {
            return "["+beg+","+end+"]";
        }

        public Segment find(int c) {
            
            Segment current = this;
            
            //System.out.println("+find:  "+this+ " x="+c);
            //System.out.println("coords:  "+ Arrays.toString(this.coords.toArray()));

            while (current.coords != null) {
                int t = upperBound(current.coords, current.pos, current.coords.size() - 1, c);

             //   System.out.println("upperBound:  "+t);

                if (t == -1) {
                    break;
                }
                else {
                    Segment found = current.subsegments.get(t);
                    current.pos = t;

                    if (found.end < c) {
                        break;
                    }

                    current = found;
                    //System.out.println("current="+current);
                }
            }

            //System.out.println("3found="+current+" c="+c);
            return current.beg <= c && current.end >= c ? current : null;
        }

        private int upperBound(List<Integer> list, int from, int to, int value) {
            //System.out.println(Arrays.toString( list.subList(from, to+1).toArray() ));

            if (to < from) {
                return -1;
            }
            else if (to == from) {
                return list.get(to) > value ? -1 : to;
            }
            else if (to == from + 1) {
                if (list.get(from) > value) {
                    return -1;
                }
                else if (list.get(to) > value) {
                    return from;
                }
                else {
                    return to;
                }
            }

            int m = from + (to - from)/2;
            int v = list.get(m);

            if (v > value) {
                return upperBound(list, from, m-1, value);
            }
            else {
                return upperBound(list, m, to, value);
            }
        }

    }

    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int segments = in.nextInt();
        Stack<Segment> parentSegments = new Stack<>();
        
        Segment root;
        Segment parent = new Segment(Integer.MIN_VALUE, Integer.MAX_VALUE);
        
        root = parent;

        while (segments-- > 0) {
            int a = in.nextInt();
            int b = in.nextInt();

            Segment newSeg = new Segment(a,b);

            while(a < parent.beg || b > parent.end) {
        //        System.out.println("pop parent");
                parent = parentSegments.pop();
            }

        //    System.out.println("new parent");
            parent.add(newSeg);
            parentSegments.push(parent);
            parent = newSeg;
        }
        
        int queries = in.nextInt();
        
        Segment current = root;

        while (queries-- > 0) {
            int x = in.nextInt();

            Segment found = null;

            while ( current != null && (found = current.find(x)) == null ) {
                current = current.parent;
            }

            if (found == null || found.num == 0) {
                System.out.println("-1");
            }
            else {
                current = found;
                System.out.println(found.num);
            }
        }

    }
}
