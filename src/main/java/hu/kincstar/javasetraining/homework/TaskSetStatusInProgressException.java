package hu.kincstar.javasetraining.homework;

/**
 * Feladat IN_PROGRESS állapotba állítása kivétel
 */
public class TaskSetStatusInProgressException extends TaskSetStatusException {

    public TaskSetStatusInProgressException(Task task) {
        super(task, ErrorCodes.ERROR_TASK_SET_STATUS_IN_PROGRESS.getMessage());
    }
}
