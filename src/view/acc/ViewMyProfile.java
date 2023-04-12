package view.acc;


import config.Config;
import cotroller.user.UserController;
import dto.response.ResponseMessenger;
import model.account.User;

import java.util.List;

import static config.Color.*;

public class ViewMyProfile {
    UserController userController = new UserController();
    User currentUser = userController.getCurrentUser();
    public void menuMyProfileAdmin(){
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|                     MENU MY PROFILE                    |");
        System.out.println("|--------------------------------------------------------|");
        System.out.println("|                     1. DETAIL PROFILE                  |");
        System.out.println("|                     2. CHANGE PROFILE USER      X      |");
        System.out.println("|                     3. CHANGE PASSWORD                 |");
        System.out.println("|                     0. BACK                            |");
        System.out.println("'--------------------------------------------------------'\n");
        switch (Config.getValidInteger()) {
            case 1:
                formDetailProfile();
                break;
            case 2:
                formChangeAllProfile();
                break;
            case 3:
                formChangePassword();
                break;
            case 0:
                return;
            default:
                System.out.print("Invalid choice : ");
        }
        menuMyProfileAdmin();
    }

    public void menuMyProfileUser(){
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|                     MENU MY PROFILE                    |");
        System.out.println("|--------------------------------------------------------|");
        System.out.println("|                     1. DETAIL PROFILE                  |");
        System.out.println("|                     2. CHANGE MY PROFILE           X   |");
        System.out.println("|                     3. CHANGE PASSWORD                 |");
        System.out.println("|                     0. BACK                            |");
        System.out.println("'--------------------------------------------------------'\n");
        switch (Config.getValidInteger()) {
            case 1:
                formDetailProfile();
                break;
            case 2:
                formChangeMyProfile();
                break;
            case 3:
                formChangePassword();
                break;
            case 0:
                return;
            default:
                System.out.print("Invalid choice : ");
        }
        menuMyProfileUser();
    }

    private void formChangeMyProfile() {


    }

    public void formChangeAllProfile() {
        System.out.println("ENTER USERNAME TO CHANGE PROFILE: ");
        String userName = Config.scanner().nextLine();
        if (userController.detailUser(userName).equals(currentUser.getUsername())){
            System.out.println(RED+"USERNAME IS NOT MATCH"+RESET);
        }else {
            User user = userController.detailUser(userName);
            System.out.println("OLD NAME: "+ currentUser.getName());
            System.out.println("ENTER NEW NAME: ");
            String newName;
            while (true){
                newName= Config.scanner().nextLine();
                if (newName.matches("[A-Z][a-zA-Z]{1,50}")){
                    newName = user.getName();
                    break;
                }else {
                    System.out.println(RED+"Invalid name, try again !!!"+RESET);
                }
            }
            System.out.println("OLD EMAIL: " + currentUser.getEmail());
            System.out.println("ENTER NEW EMAIL: ");
            String newEmail;
            while (true){
                newEmail = Config.scanner().nextLine();
                if (newEmail.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
                    newEmail = user.getEmail();
                    break;
                }else {
                    System.out.println(RED+"Invalid email, try again !!!"+RESET);
                }
            }
            User newUser = new User(currentUser.getId(), newName, newEmail, currentUser.getPassword());
            userController.editUser(userName,newUser);
            System.out.println(newUser);
            System.out.println("Update success");
            userController.displayDataUser();
        }
    }

    private void formDetailProfile() {
        System.out.print(GREEN+"DETAIL PROFILE :     ");
        System.out.println(currentUser.toString()+RESET);
    }

    public void formBlockUser() {
        formShowListUser();
        System.out.println("Enter id user to block");
        int id = Config.getValidInteger();
        ResponseMessenger messenger = userController.blockUser(id);

        switch (messenger.getMessage()) {
            case "not_found":
                System.out.println(RED+"ID not found"+RESET);
                break;
            case "blocked":
                System.out.println(GREEN+"You just blocked user id " + id+RESET);
                break;
            case "unblocked":
                System.out.println(GREEN+"You just unblocked user id " + id+RESET);
        }
        System.out.println();
    }

    public void formChangeRole() {
        formShowListUser();
        System.out.println("Enter id of user to change role");
        int id = Config.getValidInteger();
        System.out.println("Enter role to change (PM / USER)");
        String roleName = Config.scanner().nextLine();

        ResponseMessenger messenger = userController.changeRole(id, roleName);

        switch (messenger.getMessage()) {
            case "success":
                System.out.println(GREEN+"Change role successfully"+RESET);
                break;
            case "invalid_role":
                System.out.println(RED+"Invalid role !!!"+RESET);
                break;
            case "not_found":
                System.out.println(RED+"ID not found !!!"+RESET);
        }
        System.out.println();
    }

    public void formDeleteUser() {
        formShowListUser();
        System.out.println("Enter id of user to delete");
        int id = Config.getValidInteger();
        ResponseMessenger messenger = userController.deleteUser(id);
        switch (messenger.getMessage()) {
            case "success":
                System.out.println(GREEN+"Delete user successfully!"+RESET);
                break;
            case "not_found":
                System.out.println(RED+"ID not found !!!"+RESET);
        }
        System.out.println();
    }

    public void formShowListUser() {
        List<User> users = userController.displayDataUser();
//        System.out.println(users);
        System.out.println(GREEN+"                                          LIST USER");
        System.out.println(".------------------------------------------------------------------------------------------------.");
        System.out.println("|      ID      |            USERNAME             |          ROLE          |        STATUS        |");
        System.out.println("|------------------------------------------------------------------------------------------------|");

        for (User user : users) {
            System.out.printf("|      %-8d|             %-20s|         %-15s|     %-17s|\n", user.getId(), user.getUsername(), user.getRoleNameOfUser(), (user.isStatus() ? "BLOCKED" : "NOT BLOCKED"));
        }
        System.out.println("'------------------------------------------------------------------------------------------------'"+RESET);
        System.out.println();
    }


    public void formChangePassword() {
        String oldPassword;
        while (true) {
            System.out.println("Enter your old password:");
            oldPassword = Config.scanner().nextLine();
            if (oldPassword.matches("[a-zA-Z0-9]{1,10}")) {
                break;
            } else {
                System.out.println(RED+"Invalid password, try again!"+RESET);
            }
        }
        System.out.println("Enter your new password: ");
        String newPassword = Config.scanner().nextLine();
        System.out.println("Repeat the new password: ");
        String newPassword2 = Config.scanner().nextLine();

        if (!newPassword.equals(newPassword2)) {
            System.out.println(RED+"Repeat password incorrect"+RESET);
        } else {
            ResponseMessenger messenger = userController.changePassword(oldPassword, newPassword);
            switch (messenger.getMessage()) {
                case "not_match":
                    System.out.println(RED+"Old password does not matches!"+RESET);
                    break;
                case "success":
                    System.out.println(GREEN+"Change password successfully!"+RESET);
                    userController.logout();
            }
        }
        System.out.println();
    }


}
