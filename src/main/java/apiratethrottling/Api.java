package apiratethrottling;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Simulate implementing per-customer api throttling: each customer is allowed to make a maximum of 5 requests every 2 seconds.
 * You can ignore the additional requests they make, and reply to the ones below the limit.
 * Roughly follows the answer I gave, with slight changes due to me forgetting something or improvements.
 */
public class Api implements Runnable {
    private static final int REQUEST_LIMIT = 5;
    private static final int TIME_LIMIT_MILLIS = 5 * 1000;
    private static final String RESULT = "Result!";
    private static final String FAIL = "Fail :(";
    private final HashMap<Long, ArrayList<Timestamp>> requestsTable = new HashMap<>();

    private String addRequestAndGetResult(long customerId) {
        if (!requestsTable.containsKey(customerId)) {
            requestsTable.put(customerId, new ArrayList<>());
        }
        requestsTable.get(customerId).add(new Timestamp(System.currentTimeMillis()));
        return RESULT;
    }

    /**
     * this signature is the only code given by the problem (agreed with interviewer - problem statement was slightly more vague)
     */
    public String requestApiAccess(long customerId) {
        if (!requestsTable.containsKey(customerId) || requestsTable.get(customerId).size() < REQUEST_LIMIT) {
            return addRequestAndGetResult(customerId);
        }
        if (requestsTable.get(customerId).get(0).getTime() + TIME_LIMIT_MILLIS < System.currentTimeMillis()) {
            requestsTable.get(customerId).remove(0);
            return addRequestAndGetResult(customerId);
        }
        return FAIL;
    }

    @Override
    public void run() {
        System.out.println("Write a number to make a request as user with that ID, you will get the API response\n");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        while (!input.equals("quit")) {
            int customerId = Integer.parseInt(input);
            System.out.println(requestApiAccess(customerId));
            input = scanner.next();
        }
    }

    public static void main(String[] args) {
        Api api = new Api();
        api.run();
    }
}
