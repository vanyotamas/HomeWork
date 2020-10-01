package hu.kincstar.javasetraining.homework;

import org.junit.Assert;
import org.junit.Test;

public class TaskTest {

    @Test (expected = IllegalArgumentException.class)
    public void Illegal()
    {
        Task task = new Task("vanyot", 6);
    }

    @Test (expected = TaskSetStatusDoneException.class)
    public void Feaures() {
        Task task = new Task("vanyot", 5);
        Assert.assertFalse(task.hasTasks());

        Task task2 = new Task("vanyot", 3, "leírás2", TaskStatus.NEW, TaskConnection.CHILD);
        Assert.assertNotEquals(task, task2);
        task.addTask(task2);
        
        Assert.assertTrue(task.hasTasks());
        task.setStatus(TaskStatus.DONE);

        task2.setStatus(TaskStatus.DONE);
        task.setStatus(TaskStatus.DONE);
    }
}
