package examples.binarytree.treemapdictionary;

import java.time.LocalDate;

/**
 * TreeMap is like a HashMap but also keeps the entries ordered by their key's comparator
 */
public class TreeMapDictionaryApp {
    public static void main(String[] args) {
        UserRepository repository = new UserRepository();
        repository.addRecord(
                new User("Mike", "Trumpets", LocalDate.of(1982, 5, 4)),
                new CreditCard(Long.getLong("CCC"), 250));
        repository.addRecord(
                new User("Mike", "Trumpets", LocalDate.of(1982, 5, 5)),
                new CreditCard(Long.getLong("CCC"), 200));
        repository.addRecord(
                new User("Mike", "Trumpets", LocalDate.of(1973, 9, 2)),
                new CreditCard(Long.getLong("BBB"), 300));
        repository.addRecord(
                new User("Tony", "Hoppers", LocalDate.of(1999, 2, 16)),
                new CreditCard(Long.getLong("AAA"), 150));

        repository.printOrderedUsers();
    }
}
