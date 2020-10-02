package hu.kincstar.javasetraining.homework;

/**
 * Feladati státusza felsorolás
 */
public enum TaskStatus {
    NEW,
    IN_PROGRESS,
    BLOCKED,
    DONE;

    /**
     * Állapotváltás lehetséges-e
     * @param oldStatus Régi állapot
     * @param newStatus Új állapot
     * @return Lehetséges?
     */
    public static boolean isStatusChangeable(TaskStatus oldStatus, TaskStatus newStatus) {
        switch (newStatus)
        {
            case IN_PROGRESS:
                return (oldStatus == TaskStatus.NEW ||
                    oldStatus == TaskStatus.BLOCKED);
            case DONE:
                return (oldStatus == TaskStatus.IN_PROGRESS);
        }
        return true;
    }
}
