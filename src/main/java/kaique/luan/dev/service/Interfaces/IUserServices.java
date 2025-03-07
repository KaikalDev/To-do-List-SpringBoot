package kaique.luan.dev.service.Interfaces;

import kaique.luan.dev.domain.User;
import kaique.luan.dev.service.generic.IGenericServices;

import java.util.Optional;

public interface IUserServices extends IGenericServices<User> {
    public boolean UserExist(String userName);

    public boolean EmailExist(String email);

    public Optional<User> findByUserName(String userName);

    public boolean checkPassword(User user, String password);
}
