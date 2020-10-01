package hu.kincstar.javasetraining.homework;

import java.util.Objects;

import static hu.kincstar.javasetraining.homework.TaskStatus.*;

public class Task {
    private String user;
    private int runningHour;
    private String description;
    private TaskStatus status;
    private TaskConnection taskConnection;
    private Tasks tasks = new Tasks();

    public Task(String user, int runningHour) {
        this.user = user;
        if (!Fibonacci.isFibonacciNumber(runningHour))
            throw new IllegalArgumentException("Task running time isn't a Fibonacci number!");
        this.runningHour = runningHour;
        this.status = NEW;
        this.description = "";
        this.taskConnection = TaskConnection.BASE;
    }

    public Task(String user, int runningHour, String description,
                TaskStatus status) {
        this.user = user;
        if (!Fibonacci.isFibonacciNumber(runningHour))
            throw new IllegalArgumentException("Task running time isn't a Fibonacci number!");
        this.runningHour = runningHour;
        this.status = status;
        this.description = description;
        this.taskConnection = TaskConnection.BASE;
    }

    public Task(String user, int runningHour, String description,
                TaskStatus status, TaskConnection taskConnection) {
        this.user = user;
        if (!Fibonacci.isFibonacciNumber(runningHour))
            throw new IllegalArgumentException("Task running time isn't a Fibonacci number!");
        this.runningHour = runningHour;
        this.status = status;
        this.description = description;
        this.taskConnection = taskConnection;
    }

    public Task(String user, int runningHour, String description,
                TaskStatus status, TaskConnection taskConnection, Tasks tasks) {
        this.user = user;
        if (!Fibonacci.isFibonacciNumber(runningHour))
            throw new IllegalArgumentException("Task running time isn't a Fibonacci number!");
        this.status = status;
        this.description = description;
        this.tasks = tasks;
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

    /**
     * Vannak-e alfeladatai az adott feladatnak
     * @return vannak-e
     */
    public boolean hasTasks() {
        return getTasks().size() > 0;
    }

    public void addTask(Task task) {
        tasks.addTask(task);
    }

    /***
     * Feladat állapotának beállítása
     * @param ts Állapot felsorolás
     * @throws TaskSetStatusDoneException DONE csak akkor, ha a CHIELD-ek mind DONE-ok
     * @throws TaskSetStatusInProgressException IIN_PROGRESS csak akkor, ha a PREDECESSOR-ok mind DONE-ok
     */
    public void setStatus(TaskStatus ts) throws TaskSetStatusDoneException, TaskSetStatusInProgressException {
        switch (ts) {
            case BLOCKED:
                status = ts;
                break;
            case DONE:
                if (!hasTasks())
                    status = ts;
                else
                    if (tasks.isAll(TaskConnection.CHILD, DONE))
                        status = ts;
                    else
                        throw new TaskSetStatusDoneException(this);
                break;
            case IN_PROGRESS:
                if (!hasTasks())
                    status = ts;
                else
                if (tasks.isAll(TaskConnection.PRECEDESSOR, DONE))
                    status = ts;
                else
                    throw new TaskSetStatusInProgressException(this);
                break;
            case NEW:
                status = ts;
                break;
        }
    }
}
