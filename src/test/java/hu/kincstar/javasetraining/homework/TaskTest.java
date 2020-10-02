package hu.kincstar.javasetraining.homework;

import org.junit.Assert;
import org.junit.Test;

public class TaskTest {

    @Test (expected = IllegalArgumentException.class)
    public void Illegal() {
        Task task = new Task("vanyot", 6);
    }

    @Test
    public void Base() {
        Task task = new Task("vanyot", 5);
        Assert.assertFalse(task.hasTasks());
        // hiányos konstruktor miatti ellenőrzések
        Assert.assertEquals(task.getStatus(), TaskStatus.NEW);
        Assert.assertEquals(task.getTaskConnection(), TaskConnection.BASE);

        // sima feladatok hozzáadása
        Task task2 = new Task("vanyot", 3, "leírás2", TaskStatus.NEW, TaskConnection.CHILD);
        Task task3 = new Task("vanyot2", 2, "leírás2", TaskStatus.BLOCKED, TaskConnection.PARENT);
        task.addTask(task2);
        task.addTask(task3);

        Assert.assertTrue(task.hasTasks());
        Assert.assertEquals(task.getTasks().size(), 2);
    }
}
