import java.util.*;
import java.io.*;

public class HusbandInShop
{

    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        
        int productsAmount = in.nextInt();

        Map<String, Integer> supply = new HashMap<>();
        Deque<Remaining> deque = new LinkedList<>();

        while(productsAmount-- > 0) {
            int amount = in.nextInt();
            in.next();  //"of"
            String product = in.next();
            supply.put(product, amount);
        }

        int customers = in.nextInt();

        while (customers-- > 0) {
            int amount = in.nextInt();
            in.next();  //"of"
            String product = in.next();
            Remaining remaining = new Remaining(amount, product);
            deque.addLast(remaining);
        }
        
        int wait = 0;

        while(deque.size() > 0) {
            Remaining nextCustomer = deque.removeFirst();
            Integer available = supply.get(nextCustomer.name);

            if (available == null || available == 0) {
//go home
            }
            else if (available >= nextCustomer.amount) {
                available -= nextCustomer.amount;
                supply.put(nextCustomer.name, available);
            }
            else {
                if (deque.size() > 0) {
                    Remaining secondCistomer = deque.removeFirst();
                    nextCustomer.amount = available;
                    deque.addFirst(nextCustomer);
                    deque.addFirst(secondCistomer);
                }
            }
            
            wait++;
        }

        System.out.println(wait);
    }

    public static class Remaining
    {
        int amount;
        String name;

        public Remaining(int amount, String name) {
            this.amount = amount;
            this.name   = name;
        }
    }

}
