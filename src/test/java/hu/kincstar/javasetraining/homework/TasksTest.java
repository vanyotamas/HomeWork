package hu.kincstar.javasetraining.homework;

import hu.kincstar.javasetraining.homework.exceptions.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Feladatok tesztelése
  */
public class TasksTest {
    @Test
    public void Integral()
    {
        Tasks applicationTasks = new Tasks();

        Task task1 = new Task("vanyot", 5, "Task1",
                TaskStatus.NEW);
        applicationTasks.addTask(task1);
        Assert.assertEquals(applicationTasks.size(), 1);

        // feladatok hozzáadása
        Task task11 = new Task("vanyot", 3, "Task1.1",
                TaskStatus.NEW, TaskConnection.CHILD);
        Task task12 = new Task("vanyot", 2, "Task1.1",
                TaskStatus.NEW, TaskConnection.CHILD);
        task1.addRelatedTask(task11);
        try {
            task1.addRelatedTask(task12);
            Assert.fail("Feladatot hozzáadta, pedig nem lehetett volna");
        }
        catch (IllegalArgumentException ex) {
        }

        task12.setDescription("Task1.2");
        task1.addRelatedTask(task12);
        Assert.assertEquals(task1.getRelatedTasks().size(), 2);

        Task task121 = new Task("vanyot", 1, "Task1.2.1",
                TaskStatus.IN_PROGRESS, TaskConnection.PRECEDESSOR);
        task12.addRelatedTask(task121);
        Assert.assertEquals(task12.getRelatedTasks().size(), 1);

        // DONE-ra állítás
        try {
            task1.setStatus(TaskStatus.DONE);
            Assert.fail("Done állítás sikeres volt, pedig nem kellet volna!");
        }
        catch (TaskSetStatusDoneException | TaskChangeStatusDoneException ex) {
        }

        try {
            task11.setStatus(TaskStatus.DONE);
            task1.setStatus(TaskStatus.DONE);
            Assert.fail("Done állítás sikeres volt, pedig nem kellet volna!");
        }
        catch (TaskSetStatusDoneException | TaskChangeStatusDoneException ex) {
        }

        // először IN_PROGRESS-re, majd DONE-ra állítás
        task11.setStatus(TaskStatus.IN_PROGRESS);
        task121.setStatus(TaskStatus.DONE);
        task12.setStatus(TaskStatus.IN_PROGRESS);
        task11.setStatus(TaskStatus.DONE);
        task12.setStatus(TaskStatus.DONE);

        task1.setStatus(TaskStatus.IN_PROGRESS);
        task1.setStatus(TaskStatus.DONE);

        // feladtok listázása, eredmény feladatok számának ellenőrzése
        String l = task1.getRelatedTasks().listTasks("vanyot", true);
        Assert.assertEquals(3, countOfDelim(l, '{'));

        Task task2 = new Task("vanyot", 2, "Task2",
                TaskStatus.NEW, TaskConnection.PARENT);
        Task task21 = new Task("vanyot", 1, "Task2.1",
                TaskStatus.IN_PROGRESS, TaskConnection.CHILD);
        task2.addRelatedTask(task21);

        applicationTasks.addTask(task2);
        l = applicationTasks.listTasks(TaskStatus.DONE, true);
        Assert.assertEquals(4, countOfDelim(l, '{'));

        l = applicationTasks.listTasks(TaskStatus.IN_PROGRESS, true);
        System.out.println();
        Assert.assertEquals(1, countOfDelim(l, '{'));

        // feladatok törlése
        Task task3 = new Task("billg", 13, "Task3");
        Task task31 = new Task("billg", 21, "Task3.1",
                TaskStatus.IN_PROGRESS, TaskConnection.CHILD);
        task3.addRelatedTask(task31);

        try {
            task3.removeRelatedTask(task31);
            Assert.fail("TFeladatot törölték, peding nem lehetett volna");
        }
        catch (TaskRemoveException ex) {
        }

        try {
            applicationTasks.removeTask(task3);
            Assert.fail("Nem létező feladatot próbáltak törölni");
        }
        catch (TaskNotFoundException ex) {
        }
    }

    /**
     * Elválasztó karakterek számolása
     * @param someString Szöveg, amiben számolni kell
     * @param someChar Elválasztó karalter
     * @return Elválasztó laralterel száma
     */
    private int countOfDelim(String someString, char someChar) {
        int count = 0;

        for (int i = 0; i < someString.length(); i++) {
            if (someString.charAt(i) == someChar) {
                count++;
            }
        }
        return count;
    }
}
