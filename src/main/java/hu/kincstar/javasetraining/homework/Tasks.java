package hu.kincstar.javasetraining.homework;

import java.util.List;

public class Tasks {
    private List<Task> tasks;

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
    
    public Task findTask(Task task, boolean recursive) {
        Task res = null;
        for (Task t : tasks) {
            if (res == null) {
                if (task.equals(t))
                    res = t;
                if (recursive && res == null && t.hasTasks())
                    res = task.getTasks().findTask(task, recursive);
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
