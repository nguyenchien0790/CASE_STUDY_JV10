package service.user;

import model.account.User;
import service.IGenericService;

public interface IUserService extends IGenericService<User> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    User getCurrentUser();
    void serCurrentUser(User currentUser);
}
