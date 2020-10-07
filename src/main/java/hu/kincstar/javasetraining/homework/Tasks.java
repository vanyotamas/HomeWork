package hu.kincstar.javasetraining.homework;

import hu.kincstar.javasetraining.homework.exceptions.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Feladatok "lista" osztály
 */
public class Tasks {
    private List<Task> tasks = new ArrayList<>();

    public Tasks() {
    }

    public Tasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Feladat hozzáadása a listához
     * @param task Hozzáadandó feladat
     */
    public void addTask(Task task) {
        if (task == null)
            throw new IllegalArgumentException(ErrorCodes.ERROR_ADDTASK_PARAM_NULL.getMessage());
        if (findTask(task, false) != null)
            throw new IllegalArgumentException(ErrorCodes.ERROR_ADDTASK_ALREADY_HAS.getMessage());
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
            if (recursive && task.hasRelatedTasks())
                s.append(task.getRelatedTasks().listTasks(true));
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
            if (recursive && task.hasRelatedTasks())
                s.append(task.getRelatedTasks().listTasks(user, true));
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
            if (recursive && task.hasRelatedTasks())
                s.append(task.getRelatedTasks().listTasks(ts, true));
        }
        return s.toString();
    }

    /**
     * Feladat törlése
     * Egy feladatot nem lehet törölni, míg a CHILD relációban lévő alfeladatok léteznek a rendszerben, vagy nincsenek DONE állapotban)
     * @param task Törlendő task
     */
    public void removeTask(Task task) {
        if (!isTaskExists(task, false))
            throw new TaskNotFoundException(task);

        if (!isAll(TaskConnection.CHILD, TaskStatus.DONE, true))
            throw new TaskRemoveException(task);

        tasks.remove(task);
    }

    /**
     * Task keresése a listában
     * @param task keresendő Task
     * @param recursive rekurzív végrehajtás?
     * @return megtalált Task vagy null
     */
    public Task findTask(Task task, boolean recursive) {
        Task res = null;
        for (Task t : tasks) {
            if (res == null) {
                if (task.equals(t))
                    res = t;
                if (recursive && res == null && t.hasRelatedTasks())
                    res = task.getRelatedTasks().findTask(task, true);
            }
        }
        return res;
    }

    /**
     * Létezik-e az adott Task
     * @param task keresendő Task
     * @param recursive rekurzív végrehajtás?
     * @return megatalálta?
     */
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
     * @param recursive rekurzív végrehajtás?
     * @return megadott állapitúak-e a kapcsolatok
     */
    public boolean isAll(TaskConnection tc, TaskStatus ts, boolean recursive) {
        boolean res = true;
        for (Task task : tasks) {
            if (task.getTaskConnection() == tc)
                res &= (task.getStatus() == ts);
            if (recursive && task.hasRelatedTasks())
                res &= task.getRelatedTasks().isAll(tc, ts, true);
        }
        return res;
    }

    /**
     * Megszámolja a feladatlistában a megadott kapcsolatú feladatokat
     * @param tc feladat kapcsolat fajtája
     * @param recursive rekurzív végrehajtás?
     * @return feladatok száma
     */
    private int numberOfConnection(TaskConnection tc, boolean recursive) {
        int res = 0;
        for (Task task : tasks) {
            if (task.getTaskConnection() == tc)
                res++;
            if (recursive && task.hasRelatedTasks())
                res += task.getRelatedTasks().numberOfConnection(tc, true);
        }
        return res;
    }

    /**
     * Feladatok számának meghatározása
     * @return feladatok száma
     */
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
