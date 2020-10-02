package hu.kincstar.javasetraining.homework;

/**
 * Feladat státusz állítás kivétel ősosztály
 */
public abstract class TaskSetStatusException extends TaskException {

    public TaskSetStatusException(Task task, String message) {
        super(task, message);
    }
}
