package questions.shopopeninghours;

import java.util.ArrayList;
import java.util.List;

public class OpeningHours implements Runnable {

    private String[] createLog(String customersLog) {
        if (customersLog == null) throw new IllegalArgumentException();
        return customersLog.split(" ");
    }

    /**
     * Question 1/3: Compute the shop's penalty for being open when no customers came and for being closed when customers came
     * @param customersLog space-separated log of customers for every hour: Y or N if somebody came - e.g. "Y Y N Y"
     * @param openHours how many hours (always starting from beginning) the shop stayed open - for time greater than this the shop stays closed (can't reopen on same day)
     * @return the penalty for the given schedule/customer log: increase by 1 for every hour the shop was closed/open and customers were/weren't there
     */
    private int computePenalty(String customersLog, int openHours) throws IllegalArgumentException { // throwing exception: I suggested it while discussing with interviewer
        if (openHours < 0) throw new IllegalArgumentException();
        String[] log = createLog(customersLog);
        int penalty = 0;
        for (int i = 0; i < log.length; i++) {
            if (!log[i].equals("Y") && !log[i].equals("N")) throw new IllegalArgumentException();
            if (log[i].equals("Y") && i >= openHours || log[i].equals("N") && i < openHours) penalty++;
        }
        return penalty;
    }

    /**
     * Question 2/3: Use the previously defined method to find the best possible open hours for a given customersLog
     */
    private int computeBestOpenHours(String customersLog) {
        int logSize = createLog(customersLog).length;
        int bestOpenHours = 0;
        int lowestPenalty = logSize; // worst case
        for (int i = 0; i <= logSize; i++) {
            int penalty = computePenalty(customersLog, i);
            if (penalty <= lowestPenalty) {
                lowestPenalty = penalty;
                bestOpenHours = i;
            }
        }
        return bestOpenHours;
    }

    /**
     * Question 3/3: consider a different log format for multiple days. Some logs can be badly written and there are BEGIN and END
     * labels, sometimes somebody forgets to write one of the labels, you should find a way to isolate the logs for the correct days only to
     * then compute the best open hours for each of them.
     * Log example: BEGIN Y N Y BEGIN Y N Y Y END Y N BEGIN Y END N Y END <- here you should only keep valid days BEGIN Y N Y Y END & BEGIN Y END -> "Y N Y Y" & "Y"
     */
    private void computeBestOpenHoursMultipleDays(String log) {
        List<String> validLogs = new ArrayList<>();
        String[] strings = log.split("BEGIN");
        String[] cleanStrings = new String[strings.length];
        for (int i = 0; i < strings.length; i++) {
            cleanStrings[i] = strings[i].trim();
        }
        for (String s : cleanStrings) {
            if (!s.contains("END")) continue;
            validLogs.add(s.split("END")[0]); // only valid strings contain an END (substring before that)
        }
        for (String l : validLogs) {
            String trimmedLog = l.trim();
            System.out.printf("For the valid log %s the best open hours are %d%n", trimmedLog, computeBestOpenHours(trimmedLog));
        }
    }

    @Override
    public void run() {
        String customersLog = "N N N Y";
        int openHours = 4;
        String multiDayLog = "BEGIN Y N Y BEGIN Y N Y Y END Y N BEGIN Y END N Y END";
        System.out.printf("The penalty for log %s open for %d hours is %d%n", customersLog, openHours, computePenalty(customersLog, openHours));
        System.out.printf("The best possible open hours for log %s is %d%n", customersLog, computeBestOpenHours(customersLog));
        System.out.printf("Computing the best open hours for the multi-day log %s%n", multiDayLog);
        computeBestOpenHoursMultipleDays(multiDayLog);
    }

    public static void main(String[] args) {
        OpeningHours app = new OpeningHours();
        app.run();
    }
}
