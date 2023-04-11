package view.menu;

import config.Config;
import cotroller.user.UserController;
import dto.request.SignInDTO;
import dto.request.SignUpDTO;
import dto.response.ResponseMessenger;
import model.account.User;
import view.acc.ViewHome;
import view.acc.ViewMyProfile;
import view.pet.ViewPet;
import view.petcategory.ViewCategory;

import java.util.List;

import static config.Color.*;

public class ViewMainMenu {
    UserController userController = new UserController();
    public void menu(){
        System.out.println(".-----------------------------------------------------------.");
        System.out.println("|    ღ ღ(¯`◕‿◕´¯) ღ ღ   MOMO PET SHOP   ღ ღ(¯`◕‿◕´¯)ღ ღ    |");
        System.out.println("|-----------------------------------------------------------|");
        System.out.println("|                      1. REGISTER                          |");
        System.out.println("|                      2. LOGIN                             |");
        System.out.println("|                      3. LIST PETS                         |");
        System.out.println("|                      4. LIST CATEGORY                     |");
        System.out.println("|                      5. SEARCH PET                        |");
        System.out.println("|                      6. SEARCH CATEGORY                   |");
        System.out.println("|                      7. SHOW USER LIST                    |");
        System.out.println("|                      0. EXIT ☠ ☠ ☠ ☠️               |");
        System.out.println("'-----------------------------------------------------------'\n");
        switch (Config.getValidInteger()){
            case 90:
                formShowUser();
                break;
            case 1:
                new ViewMainMenu().fromRegister();
                break;
            case 2:
                new ViewMainMenu().formLogin();
                break;
            case 3:
                new ViewPet().showListPet();
                break;
            case 4:
                new ViewCategory().showListCategory();
                break;
            case 5:
                new ViewPet().searchPet();
                break;
            case 6:
                new ViewCategory().searchCategory();
                break;
            case 7:
                new ViewMyProfile().formShowListUser();
                break;
            case 0:
                System.exit(0);
        }
        menu();
    }

    public void formShowUser() {
        List<User> userList = userController.displayDataUser();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    public void formLogin() {
        System.out.println("          LOGIN");
        System.out.println("Enter user name: ");
        String username = Config.scanner().nextLine();
        System.out.println("Enter password: ");
        String password = Config.scanner().nextLine();
        SignInDTO signInDTO = new SignInDTO(username,password);
        ResponseMessenger messenger = userController.login(signInDTO);

        switch (messenger.getMessage()){
            case "blocked":
                System.out.println(RED+"This User is blocked !!!"+RESET);
                break;
            case "username_not_found":
                System.out.println(RED+"Username not found !!!"+RESET);
                break;
            case "password_wrong":
                System.out.println(RED+"Password incorrect !!!"+RESET);
                break;
            case "success":
                System.out.println(GREEN+"Login successful"+RESET);
                new ViewHome().menuLogin();
        }
    }

    public void fromRegister() {
        System.out.println("          REGISTER");
        //id
        int id = userController.getLastId();
        //name
        String name;
        while (true) {
            System.out.println("Enter name: ");
            name = Config.scanner().nextLine();
            if (name.matches("[A-Z][a-z A-Z]{1,50}")) {
                break;
            } else {
                System.out.println(RED+"Invalid name, try again !!!"+RESET);
            }
        }
        //username
        String username;
        while (true) {
            System.out.println("Enter username: ");
            username = Config.scanner().nextLine();
            if (username.matches("[a-zA-Z0-9]{1,50}")) {
                break;
            } else {
                System.out.println(RED+"Invalid username, try again !!!"+RESET);
            }
        }
        //password
        String password;
        while (true) {
            System.out.println("Enter password: ");
            password = Config.scanner().nextLine();
            if (password.matches("[a-zA-Z0-9]{1,50}")) {
                break;
            } else {
                System.out.println(RED+"Invalid username, try again !!!"+RESET);
            }
        }
        //email
        String email;
        while (true) {
            System.out.println("Enter email: ");
            email = Config.scanner().nextLine();
            if (email.matches("[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)*@[a-z]+(\\.[a-z]+){1,5}")) {
                break;
            } else {
                System.out.println(RED+"Invalid email, try again !!!"+RESET);
            }
        }

        SignUpDTO signUpDTO = new SignUpDTO(id, name, username, password, email);
        ResponseMessenger messenger = userController.register(signUpDTO);
        switch (messenger.getMessage()) {
            case "username_exists":
                System.out.println(RED+ "Username already exists !!!"+RESET);
                break;
            case "email_exists":
                System.out.println(RED+"Email already exists !!!"+RESET);
                break;
            case "success":
                System.out.println(BLUE+"Register success"+RESET);
        }
    }
}
