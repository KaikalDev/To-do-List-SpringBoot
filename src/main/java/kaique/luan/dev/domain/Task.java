package kaique.luan.dev.domain;

import jakarta.persistence.*;
import kaique.luan.dev.Enuns.*;



@Entity
@Table(name = "tb_work")
public class Task implements Persistente{

    @Id
    @GeneratedValue(generator = "work_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "work_seq", sequenceName = "sq_work", initialValue = 1, allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "description", nullable = false, length = 150)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false)
    private TaskLevel level;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TaskStatus status;

    public User getUser() {
        return user;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskLevel getLevel() {
        return level;
    }

    public void setLevel(TaskLevel level) {
        this.level = level;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
