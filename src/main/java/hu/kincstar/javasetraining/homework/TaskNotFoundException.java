package hu.kincstar.javasetraining.homework;

/**
 * Feladat nem található kivétel
 */
public class TaskNotFoundException extends TaskException {

    public TaskNotFoundException(Task task) {
        super(task, ErrorCodes.ERROR_TASK_ISNOT_EXIST.getMessage());
    }
}
