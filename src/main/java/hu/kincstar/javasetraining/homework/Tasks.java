package hu.kincstar.javasetraining.homework;

import java.util.ArrayList;
import java.util.List;

public class Tasks {
    private List<Task> tasks = new ArrayList<>();

    public Tasks() {
    }

    public Tasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        if (task == null)
            throw new IllegalArgumentException("addTask parameter is null");
        if (findTask(task, false) != null)
            throw new IllegalArgumentException("Tasks already has this task");
        tasks.add(task);
    }

    /**
     * Feladatok listázása
     * @return feladat lista
     */
    public String listTasks() {
        return listTasks(false);
    }

    /**
     * Feladatok listázása rekurzívan
     * @param recursive rekurzív végrehajtás?
     * @return feladat lista
     */
    public String listTasks(boolean recursive) {
        StringBuilder s = new StringBuilder();
        for (Task task : tasks) {
            s.append(task.toString()).append("\n");
            if (recursive && task.hasTasks())
                s.append(task.getTasks().listTasks(true));
        }
        return s.toString();
    }

    /**
     * Felhasználó feladatainak listázása
     * @param user felhasználó neve
     * @return feladat lista
     */
    public String listTasks(String user) {
        return listTasks(user, false);
    }

    /**
     * Felhasználó feladatainak listázása rekurzívan
     * @param user felhasználó neve
     * @param recursive rekurzív végrehajtás?
     * @return feladat lista
     */
    public String listTasks(String user, boolean recursive) {
        StringBuilder s = new StringBuilder();
        for (Task task : tasks) {
            if (task.getUser().equals(user))
                s.append(task.toString()).append("\n");
            if (recursive && task.hasTasks())
                s.append(task.getTasks().listTasks(user, true));
        }
        return s.toString();
    }

    /**
     * Adott státuszú feladatok listázása
     * @param ts státusz azonosítója
     * @return feladat lista
     */
    public String listTasks(TaskStatus ts) {
        return listTasks(ts, false);
    }

    /**
     * Adott státuszú feladatok listázása rekúrzívan
     * @param ts státusz azonosítója
     * @param recursive rekurzív végrehajtás?
     * @return feladat lista
     */
    public String listTasks(TaskStatus ts, boolean recursive) {
        StringBuilder s = new StringBuilder();
        for (Task task : tasks) {
            if (task.getStatus() == ts)
                s.append(task.toString()).append("\n");
            if (recursive && task.hasTasks())
                s.append(task.getTasks().listTasks(ts, true));
        }
        return s.toString();
    }

    /**
     * Feladat törlése
     * Egy feladatot nem lehet törölni, míg a CHILD relációban lévő alfeladatok léteznek a rendszerben, vagy nincsenek DONE állapotban)
     * @param task Törlendő task
     */
    public void removeTask(Task task) throws TaskRemoveException, TaskNotFoundException {
        if (!isTaskExists(task, false))
            throw new TaskNotFoundException(task);

        if (!task.getTasks().isAll(TaskConnection.CHILD, TaskStatus.DONE))
            throw new TaskRemoveException(task);

        tasks.remove(task);
    }

    public Task findTask(Task task, boolean recursive) {
        Task res = null;
        for (Task t : tasks) {
            if (res == null) {
                if (task.equals(t))
                    res = t;
                if (recursive && res == null && t.hasTasks())
                    res = task.getTasks().findTask(task, true);
            }
        }
        return res;
    }

    public boolean isTaskExists(Task task, boolean recursive)
    {
        Task res = findTask(task, recursive);
        return res == task;
    }


    /**
     * Minden megadott kapcsolatú feladat a megadott státuszú-e
     * Végigmegy a teljes fán
     * @param tc kapcsolat típusa
     * @param ts státsuz típusa
     * @return megadott állapitúak-e a kapcsolatok
     */
    public boolean isAll(TaskConnection tc, TaskStatus ts) {
        boolean res = true;
        for (Task task : tasks) {
            if (task.getTaskConnection() == tc)
                res &= task.getStatus() == ts;
            if (task.hasTasks())
                res &= task.getTasks().isAll(tc, ts);
        }
        return res;
    }

    private int numberOfConnection(TaskConnection tc) {
        int res = 0;
        for (Task task : tasks)
            if (task.getTaskConnection() == tc)
                res++;
        return res;
    }

    public int size()
    {
        return tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.toString()).append('\n');
        }
        return sb.toString();
    }
}
