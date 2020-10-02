package hu.kincstar.javasetraining.homework;

/**
 * Státusváltás kivétel
 */
public class TaskChangeStatusInProgressException extends TaskSetStatusException{

    public TaskChangeStatusInProgressException(Task task) {
        super(task, ErrorCodes.ERROR_CHANGE_STATUS_TO_IN_PROGRESS.getMessage());
    }
}
