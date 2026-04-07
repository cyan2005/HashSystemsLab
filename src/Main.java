import java.util.*;

// USERNAME CHECKER
class UsernameChecker {
    HashMap<String, Integer> users = new HashMap<>();
    HashMap<String, Integer> attempts = new HashMap<>();

    boolean checkAvailability(String name) {
        attempts.put(name, attempts.getOrDefault(name, 0) + 1);
        return !users.containsKey(name);
    }

    void register(String name, int id) {
        users.put(name, id);
    }

    List<String> suggest(String name) {
        List<String> list = new ArrayList<>();
        list.add(name + "1");
        list.add(name + "2");
        list.add(name.replace("_", "."));
        return list;
    }
}

// INVENTORY SYSTEM
class Inventory {
    HashMap<String, Integer> stock = new HashMap<>();
    HashMap<String, Queue<Integer>> waitlist = new HashMap<>();

    void addProduct(String id, int qty) {
        stock.put(id, qty);
        waitlist.put(id, new LinkedList<>());
    }

    synchronized String buy(String id, int user) {
        int s = stock.getOrDefault(id, 0);
        if (s > 0) {
            stock.put(id, s - 1);
            return "Success! Remaining: " + (s - 1);
        } else {
            waitlist.get(id).add(user);
            return "Added to waitlist. Position: " + waitlist.get(id).size();
        }
    }
}

// MAIN CLASS
public class Main {
    public static void main(String[] args) {

        UsernameChecker uc = new UsernameChecker();
        uc.register("john_doe", 1);

        System.out.println(uc.checkAvailability("john_doe"));
        System.out.println(uc.suggest("john_doe"));

        Inventory inv = new Inventory();
        inv.addProduct("IPHONE", 2);

        System.out.println(inv.buy("IPHONE", 101));
        System.out.println(inv.buy("IPHONE", 102));
        System.out.println(inv.buy("IPHONE", 103));
    }
}