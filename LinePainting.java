import java.util.*;
import java.io.*;

public class LinePainting
{
    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        
        int n = in.nextInt();
        
        List<Command> commands = new ArrayList<>();
        List<Integer> bounds = new ArrayList<>();
        
        commands.add(new Command(0, 1000_000_000, 'w'));

        bounds.add(0);
        bounds.add(1000_000_000);

        while(n-- > 0) {
            int start  = in.nextInt();
            int end    = in.nextInt();
            char color = in.next().toCharArray()[0];

            commands.add(new Command(start, end, color));
            bounds.add(start);
            bounds.add(end);
        }

        Collections.sort(bounds);
        //System.out.println(bounds);
        
        List<Segment> segments = new ArrayList<>();

        int start = bounds.get(0);

        for (int i = 0; i < bounds.size(); i++) {
            int end = bounds.get(i);
            if (start == end)
                continue;
            
            segments.add(new Segment(start, end));
            start = end;
        }

        //System.out.println(segments);
        for (int i = 0; i < segments.size(); i++) {
            Segment sgt = segments.get(i);
            for (int j = 0; j < commands.size(); j++) {
                Command cmd = commands.get(j);
                
                //System.out.println("Applying "+cmd+" to "+sgt);

                if (cmd.start <= sgt.start && cmd.end >= sgt.end) {
                    sgt.color = cmd.color;
                    sgt.right_open = false;

                    if (sgt.color == cmd.color)
                        sgt.left_open = false;
                    else
                       sgt.left_open = true; 

                    sgt.color = cmd.color;
                }
                else {
                    if (cmd.start == sgt.end)
                        sgt.right_open = true;

                    if (cmd.end == sgt.start)
                        sgt.left_open = false;
                }
                
            }

         //   System.out.println(segments);
        }

        Segment whiteStart = null;
        Segment whiteEnd = null;
        int maxLen = 0;

        int curLen = 0;
        Segment curStart = null;

        for (int i = 0; i < segments.size(); i++) {

            Segment current = segments.get(i);

            if (curStart == null && current.color == 'w') {
                curStart = current;
                curLen   = current.length();
           //     System.out.println("curStart ="+curStart+"/"+curLen);
            }

            if ( curStart != null ) {
                //Нашли кнец промежутка
                if( current.color != 'w'   //не белый
                        || (current.left_open  //точка выколота
                            && segments.get(i-1).right_open) ) {
                    
                    if (curLen > maxLen) {
                        whiteStart = curStart;
                        whiteEnd = segments.get(i-1);
                        maxLen = curLen;
                    }

                    curLen     = 0;
                    curStart   = null;
                }
                else {
                    curLen += current.length();
                    
                    //System.out.println("curLen ="+curLen);

                    if (i == segments.size() - 1 && curLen > maxLen) {
                        whiteStart = curStart;
                        whiteEnd = current;
                        maxLen = curLen;
                    }
                }
            }
            
        }
        
        int lBound = whiteStart.start;

        /*if (!whiteStart.left_open && !whiteEnd.right_open) {
                lBound = whiteStart.start - 1;
        }
        else {
            lBound = whiteStart.start;
        }*/

        System.out.println(lBound+" "+whiteEnd.end);
    }

    public static class Command
    {
        int start;
        int end;
        char color;

        public Command(int s, int e, char c) {
            start = s;
            end = e;
            color = c;
        }

        public String toString() {
            return "<"+start+","+end+","+color+">";
        }
    }

    public static class Segment
    {
        int start;
        int end;
        char color;
        boolean left_open;
        boolean right_open;

        public Segment(int s, int e) {
            start = s;
            end = e;
        }
        
        public int length() {
            return end - start;
        }

        public String toString() {
            return (left_open ? "(" : "[") 
                         +start+" ... "+end+" "+color+
                    (right_open ? ")" : "]");
        }

    }

}
