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
        if (findTask(task) != null)
            throw new IllegalArgumentException("Tasks already has this task");
        tasks.add(task);
    }
    
    private Task findTask(Task task) {
        Task res = null;
        for (Task t : tasks)
            if (task.equals(t))
                res = t;
        return res;
    }

    public void removeTask(Task task)
    {


    }

    /**
     * Minden megadott kapcsolatú feladat a megadott státuszú-e
     * @param tc kapcsolat típusa
     * @param ts státsuz típusa
     * @return megadott állapitúak-e a kapcsolatok
     */
    private boolean isAll(TaskConnection tc, TaskStatus ts) {
        boolean res = true;
        for (Task task : tasks) {
            if (task.getTaskConnection() == tc)
                res &= task.getStatus() == ts;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks)
            sb.append(task.toString()).append('\n');
        return sb.toString();
    }
}
