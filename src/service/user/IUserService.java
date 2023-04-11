package service.user;

import model.account.Role;
import model.account.User;
import service.IGenericService;

public interface IUserService extends IGenericService<User> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    User getCurrentUser();
    void saveCurrentUser(User currentUser);
    void changeRole(int id, Role role);
    void changeStatus(int id);
}
