package interviews.taskscheduler;

/**
 * dependency in the form A -> B ("A" depends on "B"
 * @param taskId id of A
 * @param dependsOn id of B
 */
public record TaskDependency(int taskId, int dependsOn) {
}
