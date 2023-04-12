package view.acc;

import config.Config;
import cotroller.cart.CartController;
import cotroller.pet.PetController;
import cotroller.user.UserController;
import model.account.User;
import model.cart.Cart;
import model.pet.Pet;
import view.menu.ViewMainMenu;
import view.pet.ViewPet;
import view.petcategory.ViewCategory;
import view.petcountry.ViewCountry;

import java.util.HashMap;
import java.util.List;

public class ViewRoleUser {
    UserController userController = new UserController();
    User currentUser = userController.getCurrentUser();
    CartController cartController = new CartController();
    PetController petController = new PetController();
    List<Pet> petList = petController.showPetList();
    public void menu(){
        System.out.println(".--------------------------------------------------------.");
        System.out.println("| WELCOME USER : "+ currentUser.getName()+"                                    |");
        System.out.println("|--------------------------------------------------------|");
        System.out.println("|                     1. LIST PET                        |");
        System.out.println("|                     2. LIST CATEGORY                   |");
        System.out.println("|                     3. LIST COUNTRY                    |");
        System.out.println("|                     4. MY PROFILE                      |");
        System.out.println("|                     5. ORDER PET        x      |");
        System.out.println("|                     6. MY SHOPPING CART                x      |");
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
                formMyShoppingCart();
                break;
            case 5:
                formOrderPet();
                break;
            case 6:
                formMyShoppingCart();
                break;
            case 0:
                userController.logout();
                new ViewMainMenu().menu();
                return;
        }
        menu();
    }

    private void formOrderPet() {
        while (true){
            System.out.printf("%-5s %-10s %s\n","ID", "NAME","AMOUNT");
            for(Pet pet: petList){
                System.out.printf("%-5s %-10s %s\n", pet.getId(),pet.getPetName(),pet.getAmount());
            }
            System.out.println("Enter ID PET to add to cart / enter 0 to exit: ");
            int id = Config.getValidInteger();
            if (id == 0){
                return;
            }
            if (petController.findIdPet(id) == null){
                System.out.println("ID PET not exist !!!");
                continue;
            }
            if(petController.findIdPet(id).getAmount()!=0){
                Pet product = petController.findIdPet(id);
                int newAmount = product.getAmount()-1;
                Pet newProduct2 = new Pet(newAmount);
                petController.editPetCart(id,newProduct2);
                cartController.createShoppingCart(id);
                System.out.println("Add success");


            }else{
                System.out.println("PET out stock !!!");
//                String backMenu = Config.scanner().nextLine();
//                if(backMenu.equalsIgnoreCase("0")){
//                    new ViewHome();
//                    break;
//                }
            }
        }
    }

    private void formMyShoppingCart() {
        while (true){
            System.out.println("MY SHOPPING CART");
            Cart cart = cartController.getMyShoppingCart();
            System.out.printf("%10s %20s %10s\n","ID ","PET NAME","AMOUNT");
            if (cart == null){
                System.out.println("CART IS EMPTY !!!");
                return;
            }
            for (int idProduct : cart.getCartMap().keySet()) {
                System.out.printf("%10d %20s %10d\n",petController.findIdPet(idProduct).getId(), petController.findIdPet(idProduct).getPetName(),cart.getCartMap().get(idProduct));
            }
            System.out.println("Choice:  1. PAY / 2. RESET CART / 3. BACK");
            switch (Config.getValidInteger()){
                case 1:
                    cart.setStatus(true);
                    cartController.save(cart);
                    System.out.println("PAY SUCCESS");
                    return;
                case 2:
                    for (int idProduct : cart.getCartMap().keySet()) {
                        Pet pet = petController.findIdPet(idProduct);
                        pet.setAmount(pet.getAmount()+cart.getCartMap().get(idProduct));
                        petController.savePet(pet);
                    }

                    cart.setCartMap(new HashMap<>());
                    cartController.save(cart);
                    System.out.println("CART IS EMPTY !!!");
                    return;

                case 3:
                    return;
                default:
                    System.out.println("Invalid choice !!!");
            }
        }
    }
}
