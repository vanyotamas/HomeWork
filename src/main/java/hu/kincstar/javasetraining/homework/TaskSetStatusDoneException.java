package hu.kincstar.javasetraining.homework;

public class TaskSetStatusDoneException extends TaskSetStatusException {
    public TaskSetStatusDoneException(Task task) {
        super(task, "Egy feladatot nem lehet DONE státuszba helyezni, amíg a CHILD relációban lévő feladatok nincsenek mind DONE státuszban");
    }
}
