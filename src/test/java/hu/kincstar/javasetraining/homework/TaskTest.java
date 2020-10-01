package hu.kincstar.javasetraining.homework;

import org.junit.Assert;
import org.junit.Test;

public class TaskTest {

    @Test(expected = IllegalArgumentException.class)
    public void TestTaskFeaures() {
        Task task = new Task("vanyot", 6);
        Assert.assertFalse(task.hasTasks());

        Task task2 = new Task("vanyot", 3, "leírás2", TaskStatus.NEW, TaskConnection.CHILD);
        Assert.assertFalse(task.equals(task2));
        task.addTask(task2);
        
        Assert.assertTrue(task.hasTasks());
        // task.setStatus(TaskStatus.DONE);




    }
}
