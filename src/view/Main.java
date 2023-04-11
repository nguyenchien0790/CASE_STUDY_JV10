package view;

import cotroller.user.UserController;
import model.account.User;
import view.acc.ViewHome;
import view.menu.ViewMainMenu;


public class Main {
    public Main(){
        User currentUser = new UserController().getCurrentUser();
        if (currentUser == null){
            new ViewMainMenu().menu();
        }else {
            new ViewHome().menuLogin();
        }
        new ViewMainMenu().menu();

    }
    public static void main(String[] args) {
        new Main();
    }
}
