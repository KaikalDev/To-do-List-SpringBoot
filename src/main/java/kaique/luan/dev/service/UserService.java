package kaique.luan.dev.service;

import kaique.luan.dev.domain.User;
import kaique.luan.dev.repository.UserRepository;
import kaique.luan.dev.service.Interfaces.IUserServices;
import kaique.luan.dev.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends GenericService<User, UserRepository> implements IUserServices {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean UserExist(String userName) {
        return repository.findByUserName(userName).isPresent();
    }

    @Override
    public boolean EmailExist(String email) {
        return repository.findByEmail(email).isPresent();
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return repository.findByUserName(userName);
    }

    @Override
    public boolean checkPassword(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public User cadastrar(User entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return super.cadastrar(entity);
    }
}
