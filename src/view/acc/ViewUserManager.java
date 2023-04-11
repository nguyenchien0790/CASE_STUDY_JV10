package view.acc;

import config.Config;
import cotroller.user.UserController;
import model.account.User;

public class ViewUserManager {
    UserController userController = new UserController();
    User currentUser = userController.getCurrentUser();

    public void menuAdmin(){
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|                      USER MANAGER                      |");
        System.out.println("|--------------------------------------------------------|");
        System.out.println("|                     1. SHOW LIST USER                  |");
        System.out.println("|                     2. DELETE USER                     |");
        System.out.println("|                     3. CHANGE ROLE                     |");
        System.out.println("|                     4. BLOCK USER                      |");
        System.out.println("|                     0. BACK                            |");
        System.out.println("'--------------------------------------------------------'\n");
        switch (Config.getValidInteger()) {
            case 1:
                new ViewMyProfile().formShowListUser();
                break;
            case 2:
                new ViewMyProfile().formDeleteUser();
                break;
            case 3:
                new ViewMyProfile().formChangeRole();
                break;
            case 4:
                new ViewMyProfile().formBlockUser();
                break;
            case 0:
                return;
            default:
                System.out.print("Invalid choice : ");
        }
        menuAdmin();
    }
    public void menuPm(){
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|                      USER MANAGER                      |");
        System.out.println("|--------------------------------------------------------|");
        System.out.println("|                     1. SHOW LIST USER                  |");
        System.out.println("|                     2. DELETE USER                     |");
        System.out.println("|                     3. BLOCK USER                      |");
        System.out.println("|                     0. BACK                            |");
        System.out.println("'--------------------------------------------------------'\n");
        switch (Config.getValidInteger()) {
            case 1:
                new ViewMyProfile().formShowListUser();
                break;
            case 2:
                new ViewMyProfile().formDeleteUser();
                break;
            case 3:
                new ViewMyProfile().formBlockUser();
                break;
            case 0:
                return;
            default:
                System.out.print("Invalid choice : ");
        }
        menuPm();
    }
}
