import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Gentlements 
{
    public static int realWeight;
    public static int correctWeights[];
    
    private static Map<String,Result> cache;

    public static Result funcCached(int left, int right, int coin) {
        String key = ""+left+"_"+right+"_"+coin;
        
        if (!cache.containsKey(key)) {
            cache.put(key, func2(left, right, coin));
        }

        return new Result(cache.get(key));
    }

    public static class Result {
        List<Integer> cards;
        int ways;

        public Result(List<Integer> cards, int ways) {
            if (cards != null) {
                this.cards = new LinkedList<>();
                this.cards.addAll(cards);
            }
            else {
                cards = null;
            }

            this.ways = ways;
        }
        
        public Result (Result r) {
            this(r.cards, r.ways);
        }
        
        public String toString() {
            if (cards == null) {
                return "[null]";
            }

            return Arrays.toString(cards.toArray());
        }

        public void print() {
            warn(toString());
        }
    }
    
    public static Result func2(int left, int right, int coin) {

        //warn("l="+left+" r="+right+" c="+coin );

        if (left == 0 && right == 0) {
            return new Result(new LinkedList<>(), 1);
        }
        else if (left < 0 || right < 0) {
            return new Result(null, 0);
        }
        else if (coin > correctWeights.length - 1) {
            return new Result(null, 0);
        }
    
        Result lr = funcCached(left - correctWeights[coin], right, coin+1);
        Result rl = funcCached(left, right - correctWeights[coin], coin+1);
        
        if (lr.cards != null) {
            lr.cards.add(coin+1);
            lr.ways += rl.ways;
            return lr;
        }
        else {
            if (rl.cards != null) {
               rl.ways += lr.ways;
               return rl;
            }
        }
        
        return new Result(null, 0);
    }

    public static void warn(String s) {
        System.out.println(s);
    }

    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        realWeight = in.nextInt();

        int n = in.nextInt();
        correctWeights = new int[n];
        
        int sum= 0;

        for (int i = 0; i < n; i++) {
            correctWeights[i] = in.nextInt();
            sum += correctWeights[i];
        }
        
        cache = new HashMap<>();

        Result r = funcCached(sum - realWeight,realWeight,0);

        if (r.cards != null) {
            if (r.ways > 1) {
                out.println("-1");
            }
            else {
                out.println(r.cards.stream().sorted().map(e -> e.toString()).collect(Collectors.joining(" ")));
            }
        }
        else {
            out.println( "0"  );
        }


        out.flush();
    }

}
