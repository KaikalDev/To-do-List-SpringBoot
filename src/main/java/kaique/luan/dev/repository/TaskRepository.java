package kaique.luan.dev.repository;

import kaique.luan.dev.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Collection<Task> findByUserId(Long userId);
}
