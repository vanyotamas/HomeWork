package hu.kincstar.javasetraining.homework;

import java.util.List;
import java.util.Objects;

public class Task {
    private String user;
    private int runningHour;
    private TaskStatus status;
    private String description;
    private Tasks tasks;
    private TaskConnection taskConnection;

    public Task(String user, int runningHour, TaskStatus status, String description) {
        this.user = user;
        if (!Fibonacci.isFibonacciNumber(runningHour))
            throw new IllegalArgumentException("Task running time isn't a Fibonacci number!");
        this.runningHour = runningHour;
        this.status = status;
        this.description = description;
        this.taskConnection = TaskConnection.BASE;
    }

    public Task(String user, int runningHour, TaskStatus status, String description, TaskConnection taskConnection) {
        this.user = user;
        if (!Fibonacci.isFibonacciNumber(runningHour))
            throw new IllegalArgumentException("Task running time isn't a Fibonacci number!");
        this.runningHour = runningHour;
        this.status = status;
        this.description = description;
        this.taskConnection = taskConnection;
    }

    public String getUser() {
        return user;
    }

    public int getRunningHour() {
        return runningHour;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public Tasks getTasks() {
        return tasks;
    }

    public TaskConnection getTaskConnection() {
        return taskConnection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return runningHour == task.runningHour &&
                Objects.equals(user, task.user) &&
                status == task.status &&
                Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, runningHour, status, description);
    }

    @Override
    public String toString() {
        return "Task{" +
                "user='" + user + '\'' +
                ", runningHour=" + runningHour +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", tasks=" + tasks.toString() +
                ", taskConnection=" + taskConnection +
                '}';
    }

    public void addTask(Task task) {
        tasks.addTask(task);
    }
}
