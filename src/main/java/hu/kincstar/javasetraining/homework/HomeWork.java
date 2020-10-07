package hu.kincstar.javasetraining.homework;

/**
 * Házi feladat fő belépési pont
 */
public class HomeWork {
    public static void main(String[] args) {
        TaskApplication taskApplication = new TaskApplication();
        taskApplication.createTasks();
        taskApplication.createRelatedTasks();
        taskApplication.trySetDone();
        taskApplication.trySetStatus();
        taskApplication.listTasks();
        taskApplication.tryRemoveTasks();
    }
}
