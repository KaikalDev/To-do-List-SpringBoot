package kaique.luan.dev.domain;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "TB_USER")
public class User implements Persistente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "sq_user", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false, length = 50, unique = true)
    private String userName;

    @Column(name = "password", nullable = false, length = 250)
    private String password;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> TaskList;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Task> getTaskList() {
        return TaskList;
    }

    public void setTaskList(List<Task> taskList) {
        TaskList = taskList;
    }
}
