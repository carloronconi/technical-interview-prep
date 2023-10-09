package stringsthreadpool;

import java.util.concurrent.*;
import java.util.stream.IntStream;

public class StringsApp implements Callable<String> {
    private final String name;
    public StringsApp(String name) {
        this.name = name;
    }

    private void performActions() {
        IntStream.range(0, 5).forEach(i -> {
            System.out.println(name + i);
        });
    }

    @Override
    public String call() throws Exception {
        performActions();
        return "Instance named " + name + " finished running!";
    }
}
