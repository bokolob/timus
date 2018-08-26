import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class ImageEncoding
{
    private static long max(long a, long b) {
        return a > b ? a : b;
    }

    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        
        String s = in.nextLine();
        
        int white = s.indexOf(' ');

        if (white >= 0) {
            secondType(in, Integer.parseInt( s.substring(0,white) ), Integer.parseInt(s.substring(white+1, s.length())));
        }
        else {
            firstType(in, Integer.parseInt(s));
        }
    }
    
    public static void secondType( Scanner in, int x, int y) {
    
       SortedSet<Pixel> pixels = new TreeSet<>((a,b) -> a.x < b.x ? -1 : ( a.x > b.x ? 1 : ( a.y < b.y ? -1 : ( a.y > b.y ? 1 : 0)    ) ) );

            Queue<Pixel> q = new LinkedList<>();
            q.add(new Pixel(x,y));
        
            while(q.size() > 0) {
                Pixel p = q.poll();
                pixels.add(p);

                String line = in.nextLine();
                
                for(int i =0;i < line.length(); i++) {
                    char c = line.charAt(i);
                    
                    if (c == 'R') {
                        q.add( new Pixel(p.x+1,p.y) );
                    }
                    else if (c == 'L') {
                        q.add( new Pixel(p.x-1,p.y) );
                    }
                    else if (c == 'T') {
                        q.add( new Pixel(p.x,p.y+1) );
                    }
                    else if (c == 'B') {
                        q.add( new Pixel(p.x,p.y-1) );
                    }
                }
            }

            System.out.println(pixels.size());

            for (Pixel p : pixels) {
                System.out.println(p.x+" "+p.y);
            }
    }

    public static void firstType( Scanner in, int k) {
        Set<Pixel> pixels = new HashSet<>();

        if (k==0) {
            System.out.println(".");
            return;
        }   
        
        Pixel first = null;

        while (k-- > 0) {
            int x = in.nextInt();
            int y = in.nextInt();

            Pixel p = new Pixel(x,y);
            pixels.add(p);

            if (first==null) {
                first=p;
            }
        }

        Queue<Pixel> q = new LinkedList<>();
        q.add(first);
        pixels.remove(first);

        System.out.println(first.x+" "+first.y);

        while(q.size() > 0) {
            Pixel p = q.poll();

            if (pixels.contains(p.right()))  {
                System.out.print("R");
                q.add(p.right());
                pixels.remove(p.right());
            }

            if (pixels.contains(p.top())) {
                System.out.print("T");
                q.add(p.top());
                pixels.remove(p.top());
            }

            if (pixels.contains(p.left())) {
                System.out.print("L");
                q.add(p.left());
                pixels.remove(p.left());
            }

            if (pixels.contains(p.bottom())) {
                System.out.print("B");
                q.add(p.bottom());
                pixels.remove(p.bottom());
            }

            
            if (q.size() > 0) {
                System.out.println(",");
            }
            else {
                System.out.println(".");
            }
        }

    }

    public static class Pixel {
        int x;
        int y;

        @Override
        public int hashCode() {
            return x * 31 + y;
        }
        
        @Override
        public String toString() {
            return "<"+x+","+y+">";
        }

        
        public Pixel(int x, int y) {
            this.x=x;
            this.y=y;
        }

        @Override
        public boolean equals (Object o) {
            return x == ((Pixel)o).x && y == ((Pixel)o).y;
        }

        public Pixel top() {
            return new Pixel(x,y+1);
        }

        public Pixel bottom() {
            return new Pixel(x,y-1);
        }

        public Pixel left() {
            return new Pixel(x-1,y);
        }

        public Pixel right() {
            return new Pixel(x+1,y);
        }

    }

}
    
