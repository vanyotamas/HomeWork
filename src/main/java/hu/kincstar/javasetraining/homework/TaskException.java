package hu.kincstar.javasetraining.homework;

public class TaskException  extends RuntimeException {
    public TaskException(Task task, String message) {
        super(message + task.toString());
    }
}
