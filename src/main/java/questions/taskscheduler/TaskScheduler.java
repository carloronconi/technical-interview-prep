package questions.taskscheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implement a task scheduler, being given a list of tasks with their optimal start/end times (integers).
 * You should maximise the task's run times while not exceeding their optimal times and considering their dependencies:
 * if 2 -> 1 (process 2 depends on process 1) it means that 2 should only run while 1 is running. The dependencies list
 * is also provided at the start.
 * You should return a schedule for each process, or "IMPOSSIBLE" if the constraints can't be satisfied, which happens
 * in two cases:
 * - the dependencies are incompatible among themselves and with the requirements
 * - there is a circular dependency
 * Tasks are identified by IDs: the schedules passed to the constructor are ordered by ID starting from 1 (meaning the
 * first item in the list refers to the schedule of the task with ID = 1, the second with ID = 2...).
 * You are only given this class's signatures and TaskSchedule, TaskDependency classes, and have to complete this class.
 */
public class TaskScheduler {
    private final List<Task> tasks;
    /**
     * @param schedules list of schedules where the task ID is the index + 1
     * @param dependencies list of dependencies between IDs
     */
    public TaskScheduler(List<TaskSchedule> schedules, List<TaskDependency> dependencies) {
        tasks = new ArrayList<>();

        // careful: IDs start from 1, while indexes start from 0, so to get task with ID x from tasks do tasks.get(x - 1)
        for (int i = 0; i < schedules.size(); i++) {
            TaskSchedule schedule = schedules.get(i);
            int currId = i + 1;
            tasks.add(new Task(currId, schedule.start(), schedule.end(),
                    dependencies
                            .stream().filter(d -> d.taskId() == currId)
                            .map(TaskDependency::dependsOn)
                            .collect(Collectors.toList())));
        }
    }

    private boolean isCircularDep(int id) {
        System.out.println("Testing if " + id + " has circular deps");

        if (tasks.get(id - 1).getDependencies().isEmpty()) return false;

        // if any of its dependencies has a circular dep it also has circular dep, otherwise it doesn't
        return tasks.get(id - 1).getDependencies().stream()
                .map(this::isCircularDep)
                .filter(b -> b)
                .findAny()
                .orElse(false);
    }

    /**
     * schedule according to constraints and dependencies
     * @return list of schedules for each task ID, ordered as the input schedules
     */
    public List<String> schedule() {
        List<String> result = new ArrayList<>();

        for (Task t : tasks) {
            if (isCircularDep(t.getId())) {
                result.add("IMPOSSIBLE");
                return result;
            }

            if (t.getDependencies().isEmpty()) continue;

            int depStart = t.getDependencies().stream().map(id -> tasks.get(id - 1).getStart()).max(Integer::compareTo).get();
            int depEnd = t.getDependencies().stream().map(id -> tasks.get(id - 1).getEnd()).min(Integer::compareTo).get();

            int start = Math.max(depStart + 1, t.getStart());
            int end = Math.min(depEnd - 1, t.getEnd());

            if (end <= start) {
                result.add("IMPOSSIBLE");
                return result;
            }

            t.setStart(start);
            t.setEnd(end);
        }

        for (Task t : tasks) {
            String line = t.getStart() + " " + t.getEnd();
            result.add(line);
        }

        return result;
    }
}
