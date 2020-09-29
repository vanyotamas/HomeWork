package hu.kincstar.javasetraining.homework;

public class TaskRemoveException extends TaskException {

    public TaskRemoveException(Task task) {
        super(task, "Egy feladatot nem lehet törölni, míg a CHILD relációban lévő alfeladatok léteznek a rendszerben, vagy nincsenek DONE állapotban");
    }
}
