package hu.kincstar.javasetraining.homework;

import hu.kincstar.javasetraining.homework.exceptions.*;
import java.util.Objects;

/**
 * Feladat osztály
 */
public class Task {

    // region Tulajdonságok
    /**
     * Feladat felhasználója tulajdonság
     */
    private String user;

    /**
     * Becsült végrehajtási idő órában tulajdonság
     */
    private int runningHour;

    /**
     * Feladat leírása tulajdonság
     */
    private String description;

    /**
     * Feladat státusza tulajdonság
     */
    private TaskStatus status;

    /**
     * Feladat kapcsolati szintje tulajdonság
     */
    private TaskConnection taskConnection;

    /**
     * Alfeladatok listája tulajdonság
     */
    private Tasks relatedTasks = new Tasks();
    // endregion

    // region Konstruktorok
    public Task(String user, int runningHour) {
        TaskConstructor(user, runningHour, "", TaskStatus.NEW, TaskConnection.PARENT, null);
    }

    public Task(String user, int runningHour, String description) {
        TaskConstructor(user, runningHour, description, TaskStatus.NEW, TaskConnection.PARENT, null);
    }

    public Task(String user, int runningHour, String description,
                TaskStatus status) {
        TaskConstructor(user, runningHour, description, status, TaskConnection.PARENT, null);
    }

    public Task(String user, int runningHour, String description,
                TaskStatus status, TaskConnection taskConnection) {
        TaskConstructor(user, runningHour, description, status, taskConnection, null);
    }

    public Task(String user, int runningHour, String description,
                TaskStatus status, TaskConnection taskConnection, Tasks relatedTasks) {
        TaskConstructor(user, runningHour, description, status, taskConnection, relatedTasks);
    }

    /**
     * Közös konstruktor
     * @param user Felhasználó
     * @param runningHour Futási idő
     * @param description Leírás
     * @param status Státusz
     * @param taskConnection Kapcsolat
     * @param relatedTasks Alfeladatok
     */
    private void TaskConstructor(String user, int runningHour, String description,
                TaskStatus status, TaskConnection taskConnection, Tasks relatedTasks) {
        this.user = user;
        if (!Fibonacci.isFibonacciNumber(runningHour))
            throw new IllegalArgumentException(ErrorCodes.ERROR_NOT_FIBONACCI.getMessage());
        this.status = status;
        this.description = description;
        this.taskConnection = taskConnection;
        if (relatedTasks != null)
            this.relatedTasks = relatedTasks;
    }
    // endregion

    // region Getter-ek, Setter-ek
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

    public Tasks getRelatedTasks() {
        return relatedTasks;
    }

    public TaskConnection getTaskConnection() {
        return taskConnection;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setRunningHour(int runningHour) {
        this.runningHour = runningHour;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTaskConnection(TaskConnection taskConnection) {
        this.taskConnection = taskConnection;
    }
    // endregion

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return runningHour == task.runningHour &&
                Objects.equals(user, task.user) &&
                Objects.equals(description, task.description) &&
                status == task.status &&
                taskConnection == task.taskConnection;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, runningHour, description, status, taskConnection);
    }

    @Override
    public String toString() {
        return toString(false);
    }

    /**
     * Feladat adatainak összeszedése
     * @param recursive rekurzív végrehajtás?
     * @return feladat(ok) jellemző adatai
     */
    public String toString(boolean recursive) {
        return "Task{" +
                "user='" + user + '\'' +
                ", runningHour=" + runningHour +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", taskConnection=" + taskConnection +
                (recursive && hasRelatedTasks() ? "\nTasks=[\n" + relatedTasks.toString() + "]\n" : "") +
                '}';
    }

    // region Feladat műveletek
    /**
     * Vannak-e alfeladatai az adott feladatnak
     * @return vannak-e
     */
    public boolean hasRelatedTasks() {
        return getRelatedTasks().size() > 0;
    }

    /**
     * Feladat hozzáadása az "al"feladatokhoz
     * @param task Hozzáadandó feladat
     */
    public void addRelatedTask(Task task) {
        relatedTasks.addTask(task);
    }

    /**
     * Feladatat eltávolítása az "al"feladatokból
     * @param task Törlendő feladat
     */
    public void removeRelatedTask(Task task) {
        relatedTasks.removeTask(task);
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
                if (TaskStatus.isStatusChangeable(getStatus(), TaskStatus.BLOCKED))
                    status = ts;
                break;
            case NEW:
                if (TaskStatus.isStatusChangeable(getStatus(), TaskStatus.NEW))
                    status = ts;
                break;
            case DONE:
                if (!TaskStatus.isStatusChangeable(getStatus(), TaskStatus.DONE))
                    throw new TaskChangeStatusDoneException(this);
                if (!hasRelatedTasks())
                    status = ts;
                else
                    if (relatedTasks.isAll(TaskConnection.CHILD, TaskStatus.DONE, true))
                        status = ts;
                    else
                        throw new TaskSetStatusDoneException(this);
                break;
            case IN_PROGRESS:
                if (!TaskStatus.isStatusChangeable(getStatus(), TaskStatus.IN_PROGRESS))
                    throw new TaskChangeStatusInProgressException(this);
                if (!hasRelatedTasks())
                    status = ts;
                else
                    if (relatedTasks.isAll(TaskConnection.PRECEDESSOR, TaskStatus.DONE, true))
                        status = ts;
                    else
                        throw new TaskSetStatusInProgressException(this);
                break;
        }
    }
    // endregion
}
