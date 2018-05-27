import java.util.stream.*;
import java.util.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
//import java.text.*;

public class CargoAgency
{
    public static int[] degrees;
    public static BigDecimal[] weights;
    public static Map<Integer, Map<Integer, Integer>> incidentMatrix;
    public static int n;

    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        
        n = in.nextInt();
        
        degrees = new int[n];
        weights = new BigDecimal[n];
        incidentMatrix = new HashMap<>();
        
        for (int i =0; i <n ; i++) {
            weights[i] = new BigDecimal(0);
        }

        int nc = n;
        
        BigDecimal N_MINUS_ONE = new BigDecimal((long)n).subtract(BigDecimal.ONE);

        while (nc-- > 1) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            int cost = in.nextInt();

            if (!incidentMatrix.containsKey(x)) {
                incidentMatrix.put(x, new HashMap<>());
            }

            if (!incidentMatrix.containsKey(y)) {
                incidentMatrix.put(y, new HashMap<>());
            }
            
            incidentMatrix.get(x).put(y, cost);
            incidentMatrix.get(y).put(x, cost);

            degrees[x]++;
            degrees[y]++;
        }
        
        Stack<Integer> leafs = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            if (degrees[i] == 1) {
                leafs.push(i);
            }
        }
        
//        System.out.println(Arrays.toString( degrees ));
        
        BigDecimal sum = new BigDecimal(0);

        while (leafs.size()>0) {
            int vertex = leafs.pop();
            
            if (incidentMatrix.get(vertex).keySet().size() == 0) {
                continue;
            }

            int neighbour = incidentMatrix.get(vertex).keySet().iterator().next();

            long cost = incidentMatrix.get(vertex).remove(neighbour);
            
//            System.out.println("branch: "+(vertex+1)+" - "+(neighbour+1));
//            System.out.println("cost: "+cost);
            
            incidentMatrix.get(neighbour).remove(vertex);

            if (incidentMatrix.get(neighbour).keySet().size() == 1) {
                leafs.push(neighbour);
            }
            
//          System.out.println("weight: "+weights[vertex]);

            //cost *=  ( n - 1L -  weights[vertex] ) * (weights[vertex] + 1L);
                BigDecimal cf = new BigDecimal(cost)
                                .multiply(
                                            N_MINUS_ONE.subtract( weights[vertex])
                                         )
                                .multiply( BigDecimal.ONE.add(weights[vertex])) ;

            // weights[vertex]*(n - 2 - weights[vertex]) + n - 1 
            // weights[vertex]*( n - weights[vertex]) + n;

            sum = sum.add( cf );

            weights[neighbour] = weights[neighbour].add( weights[vertex]).add(BigDecimal.ONE);

//            System.out.println("sum: "+sum);
//            System.out.println(Arrays.toString( weights ));
        }

//        System.out.printf("%.4f\n", (double)(sum*2f/(n*(n-1f))));
        
        sum = sum.multiply(new BigDecimal(2));

        BigDecimal squared = new BigDecimal(n).multiply(N_MINUS_ONE);

        System.out.println(sum.divide( squared, 6, RoundingMode.HALF_UP ));
    }

}
