package view.acc;

import cotroller.user.UserController;
import model.account.User;

public class ViewHome{
    UserController userController = new UserController();
    User currentUser = userController.getCurrentUser();
    public void menuLogin(){
        switch (currentUser.getRoleNameOfUser()) {
            case ADMIN:
                new ViewRoleAdmin().menu();
                break;
            case PM:
                new ViewRolePm().menu();
                break;
            case USER:
                new ViewRoleUser().menu();
                break;

        }
    }
}
