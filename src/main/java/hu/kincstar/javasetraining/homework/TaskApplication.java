package hu.kincstar.javasetraining.homework;

import hu.kincstar.javasetraining.homework.exceptions.*;

/**
 * Feladatkezelő app főosztály
 */
public class TaskApplication {
    private Tasks applicationTasks = new Tasks();
    private Task task1;
    private Task task11;
    private Task task12;
    private Task task121;
    private Task task2;
    private Task task21;
    private Task task3;
    private Task task31;

    public void createTasks() {
        System.out.println("=== Feladatok létrehozása ===");
        try {
            task1 = new Task("vanyot", 6, "leárás1", TaskStatus.BLOCKED);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        task1 = new Task("vanyot", 5, "Task1", TaskStatus.NEW);
        System.out.println("Task1-------------------");
        System.out.println(task1.toString());
        applicationTasks.addTask(task1);
    }

    public void createRelatedTasks() {
        System.out.println("=== Kapcsolódó feladatok hozzáadása === ");
        task11 = new Task("vanyot", 3, "Task1.1", TaskStatus.NEW, TaskConnection.CHILD);
        task12 = new Task("vanyot", 2, "Task1.1", TaskStatus.NEW, TaskConnection.CHILD);
        task1.addRelatedTask(task11);
        try {
            task1.addRelatedTask(task12);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        task12.setDescription("Task1.2");
        task1.addRelatedTask(task12);
        System.out.println("Task1---Task1.1----Task1.2----");
        System.out.println(task1.toString(true));

        System.out.println("Task1---Task1.1----Task1.2----Task1.2.1");
        task121 = new Task("vanyot", 1, "Task1.2.1", TaskStatus.IN_PROGRESS, TaskConnection.PRECEDESSOR);
        task12.addRelatedTask(task121);
        System.out.println(task1.toString());
    }

    public void trySetDone() {
        System.out.println("=== DONE-ra állítás ===");
        System.out.println("Task1 setStatus DONE");
        try {
            task1.setStatus(TaskStatus.DONE);
        } catch (TaskSetStatusDoneException | TaskChangeStatusDoneException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("Task1 setStatus DONE");
        try {
            task11.setStatus(TaskStatus.DONE);
            task1.setStatus(TaskStatus.DONE);
        } catch (TaskSetStatusDoneException | TaskChangeStatusDoneException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void trySetStatus() {
        System.out.println("=== Állapotok állítása folyamatában ===");
        task11.setStatus(TaskStatus.IN_PROGRESS);
        task121.setStatus(TaskStatus.DONE);
        task12.setStatus(TaskStatus.IN_PROGRESS);
        task11.setStatus(TaskStatus.DONE);
        task12.setStatus(TaskStatus.DONE);

        System.out.println("Task1 setStatus DONE");
        task1.setStatus(TaskStatus.IN_PROGRESS);
        task1.setStatus(TaskStatus.DONE);
    }

    public void listTasks() {
        System.out.println("=== Feladatok listázása ===");
        System.out.println(task1.getRelatedTasks().listTasks("vanyot", true));

        task2 = new Task("vanyot", 2, "Task2", TaskStatus.NEW, TaskConnection.PARENT);
        task21 = new Task("vanyot", 1, "Task2.1", TaskStatus.IN_PROGRESS, TaskConnection.CHILD);
        task2.addRelatedTask(task21);

        applicationTasks.addTask(task2);
        System.out.println("DONE feladatok");
        System.out.println(applicationTasks.listTasks(TaskStatus.DONE, true));
        System.out.println("IN_PREOGRESS feladatok");
        System.out.println(applicationTasks.listTasks(TaskStatus.IN_PROGRESS, true));
    }

    public void tryRemoveTasks() {
        // feladatok törlése
        System.out.println("=== Feladatok törlése ===");
        task3 = new Task("billg", 13, "Task3");
        task31 = new Task("billg", 21, "Task3.1",
                TaskStatus.IN_PROGRESS, TaskConnection.CHILD);
        task3.addRelatedTask(task31);

        try {
            task3.removeRelatedTask(task31);
        }
        catch (TaskRemoveException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            applicationTasks.removeTask(task3);
        }
        catch (TaskNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
