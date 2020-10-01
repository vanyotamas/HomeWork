package hu.kincstar.javasetraining.homework;

public class TaskSetStatusInProgressException extends TaskSetStatusException {
    public TaskSetStatusInProgressException(Task task) {
        super(task, "Egy feladatot nem lehet IN_PROGRESS státuszba helyezni amíg a PRECEDESSOR típusú linkelt feladatok nincsenek DONE státuszban");
    }
}
