package interviews.towercontest;

import junit.framework.TestCase;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

enum EventType {ENTER, EXIT, QUERY}

record ContestSchedule(int time, int height, EventType event) {}

public class TowerContestTest extends TestCase {
    private TowerContest contest;
    private final List<TowerDescription> myDescriptions = new ArrayList<>();
    private final List<TowerSchedule> mySchedules = new ArrayList<>();
    private final PriorityQueue<ContestSchedule> contestSchedules = new PriorityQueue<>((a, b) -> Integer.compare(a.time(), b.time()));

    public void setUp() {
        myDescriptions.add(new TowerDescription("Burj Kalifa", 350));
        myDescriptions.add(new TowerDescription("Tour Eiffel", 120));
        myDescriptions.add(new TowerDescription("Empire State Building", 230));

        mySchedules.add(new TowerSchedule("Tour Eiffel", 5, 10));
        mySchedules.add(new TowerSchedule("Empire State Building", 15, 25));
        mySchedules.add(new TowerSchedule("Burj Kalifa", 20, 30));

        contest = new TowerContest(myDescriptions, mySchedules);

        // Tour Eiffel
        contestSchedules.add(new ContestSchedule(5, 120, EventType.ENTER));
        contestSchedules.add(new ContestSchedule(10, 120, EventType.EXIT));

        // Empire State Building
        contestSchedules.add(new ContestSchedule(15, 230, EventType.ENTER));
        contestSchedules.add(new ContestSchedule(25, 230, EventType.EXIT));

        // Burj Kalifa
        contestSchedules.add(new ContestSchedule(20, 350, EventType.ENTER));
        contestSchedules.add(new ContestSchedule(30, 350, EventType.EXIT));

        // Other contestant's towers
        contestSchedules.add(new ContestSchedule(1, 100, EventType.ENTER));
        contestSchedules.add(new ContestSchedule(7, 230, EventType.ENTER));
        contestSchedules.add(new ContestSchedule(9, 100, EventType.EXIT));
        contestSchedules.add(new ContestSchedule(18, 230, EventType.EXIT));
        contestSchedules.add(new ContestSchedule(23, 400, EventType.ENTER));
        contestSchedules.add(new ContestSchedule(28, 400, EventType.EXIT));

        // When to ask for tallest contestants                                         // expected:
        contestSchedules.add(new ContestSchedule(2, 0, EventType.QUERY));   // ""
        contestSchedules.add(new ContestSchedule(6, 0, EventType.QUERY));   // Tour Eiffel
        contestSchedules.add(new ContestSchedule(8, 0, EventType.QUERY));   // ""
        contestSchedules.add(new ContestSchedule(16, 0, EventType.QUERY));   // Empire State Building
        contestSchedules.add(new ContestSchedule(19, 0, EventType.QUERY));   // Empire State Building
        contestSchedules.add(new ContestSchedule(19, 0, EventType.QUERY));   // Empire State Building
        contestSchedules.add(new ContestSchedule(24, 0, EventType.QUERY));   // ""
        contestSchedules.add(new ContestSchedule(26, 0, EventType.QUERY));   // ""
        contestSchedules.add(new ContestSchedule(29, 0, EventType.QUERY));   // Burj Kalifa
    }

    public void testContestSchedule() throws IOException {
        ContestSchedule event = contestSchedules.poll();
        String fileName = "./TowerContestTest";
        PrintStream stream = new PrintStream(fileName);
        while(event != null) {
            switch (event.event()){
                case ENTER -> contest.towerEntered(event.time(), event.height());
                case EXIT -> contest.towerExited(event.time(), event.height());
                case QUERY -> stream.println(contest.tallestCurrentTowersOwned());
            }
            event = contestSchedules.poll();
        }

        assertEquals(
                "[]\n" +
                "[Tour Eiffel]\n" +
                "[]\n" +
                "[Empire State Building]\n" +
                "[Empire State Building]\n" +
                "[Empire State Building]\n" +
                "[]\n" +
                "[]\n" +
                "[Burj Kalifa]\n",
                Files.readString(Path.of(fileName)));

        Files.delete(Path.of(fileName));
    }

}