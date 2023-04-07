package view;

import view.pet.ViewPet;

public class Main {
    public Main(){
        new ViewPet().menu();
//        User currentUser = new UserController().getCurrentUser();
//        if (currentUser == null){
//            new ViewMainMenu().menu();
//        }else {
//            new ViewHome();
//        }
//        new ViewMainMenu().menu();

    }
    public static void main(String[] args) {
        new Main();
    }
}
