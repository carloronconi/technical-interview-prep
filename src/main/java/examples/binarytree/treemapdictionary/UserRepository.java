package examples.binarytree.treemapdictionary;

import java.util.TreeMap;

public class UserRepository {
    private final TreeMap<User, CreditCard> userCardMap = new TreeMap<>();

    public void addRecord(User user, CreditCard card) {
        userCardMap.put(user, card);
    }

    public CreditCard getCardByUser(User user) {
        return userCardMap.get(user);
    }

    public void printOrderedUsers() {
        userCardMap.forEach((user, card) -> System.out.println(user + "\t | " + card));
    }
}
