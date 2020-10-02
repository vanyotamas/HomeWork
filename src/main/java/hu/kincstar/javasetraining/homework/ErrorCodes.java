package hu.kincstar.javasetraining.homework;

/**
 * Hibaüzenetek
 * TODO erőforrás fájlba kellene tenni ezeket
 */
public enum ErrorCodes {
    ERROR_ADDTASK_PARAM_NULL("addTask parameter is null"),
    ERROR_ADDTASK_ALREADY_HAS("Tasks already has this task"),
    ERROR_NOT_FIBONACCI("Task running time isn't a Fibonacci number!"),
    ERROR_TASK_ISNOT_EXIST("Task isn't exists:"),
    ERROR_TASK_REMOVE("Egy feladatot nem lehet törölni, míg a CHILD relációban lévő alfeladatok léteznek a rendszerben, vagy nincsenek DONE állapotban"),
    ERROR_TASK_SET_STATUS_DONE("Egy feladatot nem lehet DONE státuszba helyezni, amíg a CHILD relációban lévő feladatok nincsenek mind DONE státuszban"),
    ERROR_TASK_SET_STATUS_IN_PROGRESS("Egy feladatot nem lehet IN_PROGRESS státuszba helyezni amíg a PRECEDESSOR típusú linkelt feladatok nincsenek DONE státuszban"),
    ERROR_CHANGE_STATUS_TO_IN_PROGRESS("IN_PROGRESS státuszba, csak NEW és BLOCKED állapotból kerülhet a feladat"),
    ERROR_CHANGE_STATUS_TO_DONE("DONE státuszba, csak IN_PROGRESS státuszból kerülhet a feladat,");

    private final String message;

    ErrorCodes(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
