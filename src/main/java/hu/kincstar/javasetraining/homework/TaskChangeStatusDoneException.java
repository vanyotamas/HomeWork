package hu.kincstar.javasetraining.homework;

/**
 * Státusváltás kivétel
 */
public class TaskChangeStatusDoneException extends TaskSetStatusException {

    public TaskChangeStatusDoneException(Task task) {
        super(task, ErrorCodes.ERROR_CHANGE_STATUS_TO_DONE.getMessage());
    }
}
