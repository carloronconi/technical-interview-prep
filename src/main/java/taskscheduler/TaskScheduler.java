package taskscheduler;

import java.util.List;

/**
 * You have to implement a task scheduler, being given a list of tasks with their optimal start/end times (integers).
 * You should maximise their run times while not exceeding the requirements, while also preserving their dependencies:
 * if 2 -> 1 (process 2 depends on process 1) it means that 2 should only run while 1 is running. The dependencies list
 * is also provided at the start.
 * You should return a schedule for each process, or "IMPOSSIBLE" if the constraints can't be satisfied, which happens
 * in two cases:
 * - the dependencies are incompatible with the requirements
 * - there is a circular dependency
 * Tasks are identified by IDs: the schedules passed to the constructor are ordered by ID starting from 1 (meaning the
 * first item in the list refers to the schedule of the task with ID = 1, the second with ID = 2...).
 * You are only given this class's signatures and the Task records, and have to implement the class below.
 */
public class TaskScheduler {
    /**
     * @param schedules list of schedules where the task ID is the index + 1
     * @param dependencies list of dependencies between IDs
     */
    public TaskScheduler(List<TaskSchedule> schedules, List<TaskDependency> dependencies) {
    }

    /**
     * schedule according to constraints and dependencies
     * @return list of schedules for each task ID, ordered as the input schedules
     */
    public List<String> schedule() {

    }
}
