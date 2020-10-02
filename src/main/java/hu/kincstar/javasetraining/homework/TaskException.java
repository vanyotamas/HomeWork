package hu.kincstar.javasetraining.homework;

/**
 * Taskkezelő alkalmazás hibák őse
 */
public class TaskException  extends RuntimeException {
    public TaskException(Task task, String message) {
        super(message + " (" + task.getDescription() + ")"); // TODO ide inkább egy Task azonosító kellene, de olyan nincs
    }
}
