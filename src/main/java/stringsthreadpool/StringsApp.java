package stringsthreadpool;

import java.util.concurrent.*;
import java.util.regex.Pattern;
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

    private void regexMatchName() {
        String regex = "^(aa|bo)*XX$"; //^ and $ important because start and end of word, otherwise find will be true even if
        // pattern found in the middle of the word
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        String match = pattern.matcher(name).find()? "Pattern found match:" : "Didn't match:";
        match = match.concat(" " + name + " with " + regex);
        System.out.println(match + "\n");
    }

    @Override
    public String call() throws Exception {
        //performActions();
        regexMatchName();
        return "Instance named " + name + " finished running!";
    }
}
