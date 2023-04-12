package view.acc;

import config.Config;
import cotroller.user.UserController;
import model.account.User;
import view.menu.ViewMainMenu;
import view.pet.ViewPet;
import view.petcategory.ViewCategory;
import view.petcountry.ViewCountry;

public class ViewRoleAdmin {
    UserController userController = new UserController();
    User currentUser = userController.getCurrentUser();
    public void menu(){
        System.out.println(".--------------------------------------------------------.");
        System.out.println("| WELCOME ADMIN : "+ currentUser.getName()+"                                  |");
        System.out.println("|--------------------------------------------------------|");
        System.out.println("|                     1. PET MANAGER                     |");
        System.out.println("|                     2. CATEGORY MANAGE                 |");
        System.out.println("|                     3. COUNTRY MANAGE                  |");
        System.out.println("|                     4. USER MANAGER                    |");
        System.out.println("|                     5. MY PROFILE                      |");
        System.out.println("|                     6. SHOW USER LIST                  |");
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
                new ViewUserManager().menuAdmin();
                break;
            case 5:
                new ViewMyProfile().menuMyProfileAdmin();
                break;
            case 6:
                new ViewMyProfile().formShowListUser();
                break;
            case 0:
                userController.logout();
                new ViewMainMenu().menu();
                return;
        }
        menu();

    }


}
