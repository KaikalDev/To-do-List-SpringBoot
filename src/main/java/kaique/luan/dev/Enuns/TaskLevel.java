package kaique.luan.dev.Enuns;

import java.util.Arrays;
import java.util.Collection;

public enum TaskLevel {
    URGENTE, IMPORTANTE, NORMAL;

    public static TaskLevel getByName(String value) {
        for (TaskLevel status : TaskLevel.values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        return null;
    }

    public static Collection<TaskLevel> getValues() {
        return Arrays.asList(TaskLevel.values());
    }
}
