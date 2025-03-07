package kaique.luan.dev.service;

import kaique.luan.dev.Enuns.TaskLevel;
import kaique.luan.dev.Enuns.TaskStatus;
import kaique.luan.dev.domain.Task;
import kaique.luan.dev.repository.TaskRepository;
import kaique.luan.dev.service.Interfaces.ITaskServices;
import kaique.luan.dev.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService extends GenericService<Task, TaskRepository> implements ITaskServices {

    @Autowired
    protected TaskService(TaskRepository repository) {
        super(repository);
    }

    @Override
    public Collection<Task> findByUser(Long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public Collection<Task> filter(TaskStatus status, Collection<Task> tasks) {
        return tasks.stream()
                .filter(task -> task.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Task> filter(TaskLevel level, Collection<Task> tasks) {
        return tasks.stream()
                .filter(task -> task.getLevel().equals(level))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Task> filter(String title, Collection<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) {
            return Collections.emptyList();
        }

        if (title == null || title.trim().isEmpty()) {
            return tasks;
        }

        String titleLower = title.toLowerCase().trim();

        return tasks.stream()
                .filter(task -> task.getTitle() != null && task.getTitle().toLowerCase().contains(titleLower))
                .collect(Collectors.toList());
    }

    @Override
    public void toggleCompleted(Task task) {
        TaskStatus status = task.getStatus();
        switch (status) {
            case PENDENTE:
                task.setStatus(TaskStatus.CONCLUIDA);
                break;
            case CONCLUIDA:
                task.setStatus(TaskStatus.PENDENTE);
                break;
        }
        repository.save(task);
    }
}
