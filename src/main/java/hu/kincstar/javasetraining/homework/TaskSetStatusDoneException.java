package hu.kincstar.javasetraining.homework;

/**
 * Feladat DONE állapotba állítása kivétel
 */
public class TaskSetStatusDoneException extends TaskSetStatusException {

    public TaskSetStatusDoneException(Task task) {
        super(task, ErrorCodes.ERROR_TASK_SET_STATUS_DONE.getMessage());
    }
}
