package view.role;

import config.Config;
import cotroller.user.UserController;
import view.menu.ViewMainMenu;

public class ViewUser {
    UserController userController = new UserController();
    public void menu(){
        System.out.println("Hello User");
        System.out.println("1. Log out");
        int id = Config.getValidInteger();
        if (id == 1){
            userController.logout();
            new ViewMainMenu().menu();
            return;
        }
        menu();
    }
}
