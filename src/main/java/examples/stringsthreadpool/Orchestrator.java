package examples.stringsthreadpool;

import java.util.concurrent.*;

public class Orchestrator implements Runnable {

    @Override
    public void run() {
        StringsApp alice = new StringsApp("aaaaaaXX");
        StringsApp bob = new StringsApp("boboaaboaXX");

        /*
        app.run();
         */

        /* If we wanted to run it in a new thread
        Thread thread = new Thread(app);
        thread.start();
        */

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<String> futureAlice = executor.submit(alice);
        Future<String> futureBob = executor.submit(bob);

        try {
            System.out.println(futureAlice.get() + "\n" + futureBob.get()); // .get() is blocking
            if (!executor.awaitTermination(1, TimeUnit.SECONDS)) executor.shutdown(); // indicated way to wait for threads to finish
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Orchestrator finished running!");
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Orchestrator orchestrator = new Orchestrator();
        orchestrator.run();
    }
}
