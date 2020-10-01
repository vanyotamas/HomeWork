package hu.kincstar.javasetraining.homework;

public abstract class TaskSetStatusException extends TaskException {
    public TaskSetStatusException(Task task, String message) {
        super(task, message);
    }
}
