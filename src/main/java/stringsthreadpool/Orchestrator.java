package stringsthreadpool;

import java.util.concurrent.*;

public class Orchestrator implements Runnable {

    @Override
    public void run() {
        StringsApp alice = new StringsApp("aaaaaace");
        StringsApp bob = new StringsApp("Bob");

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
            System.out.println(futureAlice.get() + "\n" + futureBob.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        executor.shutdown();

        System.out.println("Orchestrator finished running!");
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Orchestrator orchestrator = new Orchestrator();
        orchestrator.run();
    }
}
