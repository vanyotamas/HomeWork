package hu.kincstar.javasetraining.homework.exceptions;

import hu.kincstar.javasetraining.homework.Task;
import hu.kincstar.javasetraining.homework.exceptions.TaskException;

/**
 * Feladat státusz állítás kivétel ősosztály
 */
public abstract class TaskSetStatusException extends TaskException {

    public TaskSetStatusException(Task task, String message) {
        super(task, message);
    }
}
