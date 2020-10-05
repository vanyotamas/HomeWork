package hu.kincstar.javasetraining.homework.exceptions;

import hu.kincstar.javasetraining.homework.Task;

/**
 * Feladat eltávolítása kivétel
 */
public class TaskRemoveException extends TaskException {

    public TaskRemoveException(Task task) {
        super(task, ErrorCodes.ERROR_TASK_REMOVE.getMessage());
    }
}
