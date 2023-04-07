package view.role;

import cotroller.user.UserController;
import model.account.User;
import view.role.ViewAdmin;
import view.role.ViewPm;
import view.role.ViewUser;

public class ViewHome {
    UserController userController = new UserController();
    User currentUser = userController.getCurrentUser();
    public ViewHome(){
        switch (currentUser.getRoleNameOfUser()) {
            case ADMIN:
                new ViewAdmin();
                break;
            case PM:
                new ViewPm();
                break;
            case USER:
                new ViewUser();
                break;

        }
    }
}
