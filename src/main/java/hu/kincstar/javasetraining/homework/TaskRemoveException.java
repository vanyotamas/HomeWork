package hu.kincstar.javasetraining.homework;

/**
 * Feladat eltávolítása kivétel
 */
public class TaskRemoveException extends TaskException {

    public TaskRemoveException(Task task) {
        super(task, ErrorCodes.ERROR_TASK_REMOVE.getMessage());
    }
}
