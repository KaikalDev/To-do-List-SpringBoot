package kaique.luan.dev.service.Interfaces;

import kaique.luan.dev.Enuns.TaskLevel;
import kaique.luan.dev.Enuns.TaskStatus;
import kaique.luan.dev.domain.Task;
import kaique.luan.dev.domain.User;
import kaique.luan.dev.service.generic.IGenericServices;

import java.util.Collection;
import java.util.Optional;

public interface ITaskServices extends IGenericServices<Task> {
    public Collection<Task> findByUser(Long userId);

    public Collection<Task> filter(TaskStatus status, Collection<Task> tasks);

    public Collection<Task> filter(TaskLevel level, Collection<Task> tasks);

    public Collection<Task> filter(String title, Collection<Task> tasks);

    public void toggleCompleted(Task task);
}
