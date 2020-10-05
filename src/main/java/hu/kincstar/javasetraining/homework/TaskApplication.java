package hu.kincstar.javasetraining.homework;

import hu.kincstar.javasetraining.homework.exceptions.*;

/**
 * Feladatkezelő app főosztály / belépési pont
 */
public class TaskApplication {
    public static void main(String[] args) {

        Tasks applicationTasks = new Tasks();

        System.out.println("=== Feladatok létrehozása ===");
        Task task1;
        try {
             task1 = new Task("vanyot", 6, "leárás1", TaskStatus.BLOCKED);
        }
        catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        task1 = new Task("vanyot", 5, "Task1", TaskStatus.NEW);
        System.out.println("Task1-------------------");
        System.out.println(task1.toString());
        applicationTasks.addTask(task1);

        System.out.println("=== Kapcsolódó feladatok hozzáadása === ");
        Task task11 = new Task("vanyot", 3, "Task1.1", TaskStatus.NEW, TaskConnection.CHILD);
        Task task12 = new Task("vanyot", 2, "Task1.1", TaskStatus.NEW, TaskConnection.CHILD);
        task1.addRelatedTask(task11);
        try {
            task1.addRelatedTask(task12);
        }
        catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        task12.setDescription("Task1.2");
        task1.addRelatedTask(task12);
        System.out.println("Task1---Task1.1----Task1.2----");
        System.out.println(task1.toString(true));

        System.out.println("Task1---Task1.1----Task1.2----Task1.2.1");
        Task task121 = new Task("vanyot", 1, "Task1.2.1", TaskStatus.IN_PROGRESS, TaskConnection.PRECEDESSOR);
        task12.addRelatedTask(task121);
        System.out.println(task1.toString());

        System.out.println("=== DONE-ra állítás ===");
        System.out.println("Task1 setStatus DONE");
        try {
            task1.setStatus(TaskStatus.DONE);
        }
        catch (TaskSetStatusDoneException | TaskChangeStatusDoneException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("Task1 setStatus DONE");
        try {
            task11.setStatus(TaskStatus.DONE);
            task1.setStatus(TaskStatus.DONE);
        }
        catch (TaskSetStatusDoneException | TaskChangeStatusDoneException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("=== Állapotok állítása folyamatában ===");
        task11.setStatus(TaskStatus.IN_PROGRESS);
        task121.setStatus(TaskStatus.DONE);
        task12.setStatus(TaskStatus.IN_PROGRESS);
        task11.setStatus(TaskStatus.DONE);
        task12.setStatus(TaskStatus.DONE);

        System.out.println("Task1 setStatus DONE");
        task1.setStatus(TaskStatus.IN_PROGRESS);
        task1.setStatus(TaskStatus.DONE);

        System.out.println("=== Feladatok listázása ===");
        System.out.println(task1.getRelatedTasks().listTasks("vanyot", true));

        Task task2 = new Task("vanyot", 2, "Task2", TaskStatus.NEW, TaskConnection.PARENT);
        Task task21 = new Task("vanyot", 1, "Task2.1", TaskStatus.IN_PROGRESS, TaskConnection.CHILD);
        task2.addRelatedTask(task21);

        applicationTasks.addTask(task2);
        System.out.println("DONE feladatok");
        System.out.println(applicationTasks.listTasks(TaskStatus.DONE, true));
        System.out.println("IN_PREOGRESS feladatok");
        System.out.println(applicationTasks.listTasks(TaskStatus.IN_PROGRESS, true));
    }
}
