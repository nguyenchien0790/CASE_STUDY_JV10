package view.acc;

import config.Config;
import cotroller.user.UserController;
import model.account.User;
import view.menu.ViewMainMenu;
import view.pet.ViewPet;
import view.petcategory.ViewCategory;
import view.petcountry.ViewCountry;

public class ViewRoleUser {
    UserController userController = new UserController();
    User currentUser = userController.getCurrentUser();
    public void menu(){
        System.out.println(".--------------------------------------------------------.");
        System.out.println("| WELCOME USER : "+ currentUser.getName()+"                                    |");
        System.out.println("|--------------------------------------------------------|");
        System.out.println("|                     1. LIST PET                        |");
        System.out.println("|                     2. LIST CATEGORY                   |");
        System.out.println("|                     3. LIST COUNTRY                    |");
        System.out.println("|                     4.                     |");
        System.out.println("|                     5. MY PROFILE                      |");
        System.out.println("|                     0. Log out                         |");
        System.out.println("'--------------------------------------------------------'\n");
        switch (Config.getValidInteger()) {
            case 1:
                new ViewPet().menu();
                break;
            case 2:
                new ViewCategory().menu();
                break;
            case 3:
                new ViewCountry().menu();
                break;
            case 4:

                break;
            case 5:
                new ViewMyProfile().menuMyProfileUser();
                break;
            case 0:
                userController.logout();
                new ViewMainMenu().menu();
                return;
        }
        menu();

    }
}
