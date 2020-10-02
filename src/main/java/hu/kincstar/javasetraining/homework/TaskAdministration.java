package hu.kincstar.javasetraining.homework;

/**
 * Feladatkezelő app főosztály / belépési pont
 */
public class TaskAdministration {
    public static void main(String[] args) {

        Tasks tasks = new Tasks();

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

        Task task11 = new Task("vanyot", 3, "Task1.1", TaskStatus.NEW, TaskConnection.CHILD);
        Task task12 = new Task("vanyot", 2, "Task1.1", TaskStatus.NEW, TaskConnection.CHILD);
        task1.addTask(task11);
        try {
            task1.addTask(task12);
        }
        catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        task12.setDescription("Task1.2");
        task1.addTask(task12);
        System.out.println("Task1---Task1.1----Task1.2----");
        System.out.println(task1.toString(true));

        System.out.println("Task1---Task1.1----Task1.2----Task1.2.1");
        Task task121 = new Task("vanyot", 1, "Task1.2.1", TaskStatus.IN_PROGRESS, TaskConnection.PRECEDESSOR);
        task12.addTask(task121);
        System.out.println(task1.toString());

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

        task11.setStatus(TaskStatus.IN_PROGRESS);
        task121.setStatus(TaskStatus.DONE);
        task12.setStatus(TaskStatus.IN_PROGRESS);
        task11.setStatus(TaskStatus.DONE);
        task12.setStatus(TaskStatus.DONE);

        System.out.println("Task1 setStatus DONE");
        task1.setStatus(TaskStatus.IN_PROGRESS);
        task1.setStatus(TaskStatus.DONE);

        System.out.println("listTasks");
        System.out.println(task1.getTasks().listTasks("vanyot", true));
    }
}
