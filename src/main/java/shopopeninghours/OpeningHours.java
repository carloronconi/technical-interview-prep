package shopopeninghours;

public class OpeningHours implements Runnable {

    /**
     * Compute the shop's penalty for being open when no customers came and for being closed when customers came
     * @param customersLog space-separated log of customers for every hour: Y or N if somebody came - e.g. "Y Y N Y"
     * @param openHours how many hours (always starting from beginning) the shop stayed open - for time greater than this the shop stays closed (can't reopen on same day)
     * @return the penalty for the given schedule/customer log: increase by 1 for every hour the shop was closed/open and customers were/weren't there
     */
    private int computePenalty(String customersLog, int openHours) throws IllegalArgumentException { // throwing exception: I suggested it while discussing with interviewer
        if (customersLog == null || openHours < 0) throw new IllegalArgumentException();
        String[] log = customersLog.split(" ");
        int penalty = 0;
        for (int i = 0; i < log.length; i++) {
            if (!log[i].equals("Y") && !log[i].equals("N")) throw new IllegalArgumentException();
            if (log[i].equals("Y") && i > openHours || log[i].equals("N") && i < openHours) penalty++; //TODO: fix condition
        }
        return penalty;
    }

    // TODO: complete implementation and remember third question
    private int computeBestOpenHours(String customersLog) {
        return 0;
    }

    @Override
    public void run() {
        String customersLog = "Y Y N Y";
        int openHours = 1;
        System.out.printf("The penalty for log %s is %d%n", customersLog, computePenalty(customersLog, openHours));
    }

    public static void main(String[] args) {
        OpeningHours app = new OpeningHours();
        app.run();
    }
}
