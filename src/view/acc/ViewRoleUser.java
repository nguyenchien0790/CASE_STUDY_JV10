package view.acc;

import config.Config;
import cotroller.cart.CartController;
import cotroller.pet.PetController;
import cotroller.user.UserController;
import model.account.User;
import model.cart.Cart;
import model.pet.Pet;
import service.pet.PetServiceIMPL;
import view.menu.ViewMainMenu;
import view.pet.ViewPet;
import view.petcategory.ViewCategory;
import view.petcountry.ViewCountry;

import java.util.HashMap;
import java.util.List;

import static config.Color.*;

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
        System.out.println("|                     4. SEARCH PET                      |");
        System.out.println("|                     5. SEARCH CATEGORY                 |");
        System.out.println("|                     6. SORT PET BY PRICE               |");
        System.out.println("|                     7. ORDER PET                       |");
        System.out.println("|                     8. MY SHOPPING CART                |");
        System.out.println("|                     9. MY PROFILE                      |");
        System.out.println("|                     0. Log out                         |");
        System.out.println("'--------------------------------------------------------'\n");
        switch (Config.getValidInteger()) {
            case 1:
                new ViewPet().showListPet();
                break;
            case 2:
                new ViewCategory().showListCategory();
                break;
            case 3:
                new ViewCountry().showListCountry();
                break;
            case 4:
                new ViewPet().searchPet();
                break;
            case 5:
                new ViewCategory().searchCategory();
                break;
            case 6:
                new ViewPet().sortPetByPrice();
                new PetServiceIMPL().sortPetById();
                break;
            case 7:
                formOrderPet();
                break;
            case 8:
                formMyShoppingCart();
                break;
            case 9:
                new ViewMyProfile().menuMyProfileUser();
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
            System.out.println(                GREEN+".--------------------------------------------------------.");
            System.out.printf(WHITE+"|%s|%s|%s|%s|\n","  ID  ","      PET NAME      ","   AMOUNT   ","     PRICE     "+RESET);
            System.out.println( GREEN+"|--------------------------------------------------------|");
            for(Pet pet: petList){
                System.out.printf("|  %2d  |  %-18s|      %-6d|  %-13.2f|\n", pet.getId(),pet.getPetName(),pet.getAmount(),pet.getPrice());
            }
            System.out.println("'--------------------------------------------------------'"+RESET);
            System.out.println("                                            .---------. ");
            System.out.println("     Input ID PET to add to cart            | 0. EXIT | ");
            System.out.println("                                            '---------' ");

            int id = Config.getValidInteger();
            if (id == 0){
                return;
            }
            if (petController.findIdPet(id) == null){
                System.out.println(RED+"ID PET not exist !!!"+RESET);
                continue;
            }
            if(petController.findIdPet(id).getAmount()!=0){
                Pet product = petController.findIdPet(id);
                int newAmount = product.getAmount()-1;
                Pet newProduct2 = new Pet(newAmount);
                petController.editPetCart(id,newProduct2);
                cartController.createShoppingCart(id);
                System.out.println(GREEN+"Add success"+RESET);
            }else{
                System.out.println(RED+"PET out stock !!!"+RESET);
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
            System.out.println(                GREEN+".--------------------------------------------------------.");
            System.out.printf(WHITE+"|%s|%s|%s|%s|\n","  ID  ","      PET NAME      ","   AMOUNT   ","     PRICE     "+RESET);
            System.out.println(                GREEN+"|--------------------------------------------------------|");

            if (cart == null){
                System.out.println(RED+"   CART IS EMPTY !!!"+RESET);
                System.out.println(                GREEN+"'--------------------------------------------------------'"+RESET);

                return;
            }
            double sumPay =0;
            for (int idProduct : cart.getCartMap().keySet()) {
                System.out.printf(GREEN+"|  %2d  |  %-18s|      %-6d|  %-13.2f|\n",petController.findIdPet(idProduct).getId(), petController.findIdPet(idProduct).getPetName(),cart.getCartMap().get(idProduct) ,petController.findIdPet(idProduct).getPrice());
                System.out.println(                "'--------------------------------------------------------'"+RESET);
                sumPay+=petController.findIdPet(idProduct).getPrice()*cart.getCartMap().get(idProduct);
            }
            System.out.println(".--------------------------------------------------------.");
            System.out.println("| Total pay     |  "+sumPay+ " VND");
            System.out.println("|--------------------------------------------------------|");
            System.out.println("| Choice number |  1. PAY  |  2. RESET CART  |  3. BACK  |");
            System.out.println("'--------------------------------------------------------'");
            switch (Config.getValidInteger()){
                case 1:
                    cart.setStatus(true);
                    cartController.save(cart);
                    System.out.println(GREEN+"PAY SUCCESS !" +RESET);
                    return;
                case 2:
                    for (int idProduct : cart.getCartMap().keySet()) {
                        Pet pet = petController.findIdPet(idProduct);
                        pet.setAmount(pet.getAmount()+cart.getCartMap().get(idProduct));
                        petController.savePet(pet);
                    }

                    cart.setCartMap(new HashMap<>());
                    cartController.save(cart);
                    System.out.println(GREEN+"CART IS EMPTY !!!"+RESET);
                    return;

                case 3:
                    return;
                default:
                    System.out.println(GREEN+"Invalid choice !!!"+RESET);
            }
        }
    }
}
