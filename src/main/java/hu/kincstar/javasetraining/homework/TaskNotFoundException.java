package hu.kincstar.javasetraining.homework;

public class TaskNotFoundException extends TaskException {
    public TaskNotFoundException(Task task) {
        super(task, "Task isn't exists:");
    }
}
