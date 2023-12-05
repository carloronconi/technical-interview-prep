package taskscheduler;

import junit.framework.TestCase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TaskSchedulerTest extends TestCase {
    private TaskScheduler scheduler;
    private List<TaskSchedule> schedules;
    private List<TaskDependency> dependencies;

    public void testSimple() throws IOException {
        schedules = new ArrayList<>();
        dependencies = new ArrayList<>();

        schedules.add(new TaskSchedule(10, 50)); // ID = 1
        schedules.add(new TaskSchedule(20, 80)); // ID = 2
        schedules.add(new TaskSchedule(5, 100)); // ID = 3

        dependencies.add(new TaskDependency(3, 1));
        dependencies.add(new TaskDependency(3, 2));
        // expect 3 to be scheduled between 20 + 1 and 50 - 1;

        scheduler = new TaskScheduler(schedules, dependencies);

        String testFile = "./SchedulerTest";
        PrintStream out = new PrintStream(testFile);

        scheduler.schedule().forEach(out::println);

        assertEquals(
                "10 50\n" +
                        "20 80\n" +
                        "21 49\n",
                Files.readString(Path.of(testFile)));

        Files.delete(Path.of(testFile));
    }
}