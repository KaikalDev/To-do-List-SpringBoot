package kaique.luan.dev.Enuns;

public enum TaskStatus {
    PENDENTE, CONCLUIDA;

    public static TaskStatus getByName(String value) {
        for (TaskStatus status : TaskStatus.values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        return null;
    }
}
