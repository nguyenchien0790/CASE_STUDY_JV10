package view.pet;

import config.Config;
import cotroller.pet.PetController;
import cotroller.petcategory.CategoryController;
import cotroller.petcountry.CountryController;
import model.pet.Pet;
import model.petcategory.Category;
import model.petcountry.Country;
import service.category.CategoryServiceIMPL;
import service.country.CountryServiceIMPL;

import java.util.List;

import static config.Color.*;

public class ViewPet {
    PetController petController = new PetController();
    CategoryController categoryController = new CategoryController();
    CountryController countryController = new CountryController();
    List<Pet> petList = petController.showPetList();

    public void menu() {

        System.out.println(".--------------------------------------------------------.");
        System.out.println("|                       PETS MANAGER                     |");
        System.out.println("|--------------------------------------------------------|");
        System.out.println("|                     1. SHOW LIST PETS                  |");
        System.out.println("|                     2. ADD PET                x        |");
        System.out.println("|                     3. UPDATE PET                      |");
        System.out.println("|                     4. DELETE PET                      |");
        System.out.println("|                     5. SEARCH PET                      |");
        System.out.println("|                     0. BACK                            |");
        System.out.println("'--------------------------------------------------------'\n");
        switch (Config.getValidInteger()) {
            case 1:
                showListPet();
                break;
            case 2:
                createPet();
                break;
            case 3:
                updatePet();
                break;
            case 4:
                deletePet();
                break;
            case 5:
                searchPet();
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice : ");
        }
        menu();
    }

    public void searchPet() {
        System.out.print("     Enter Pet's name to search : ");
        String nameSearch = Config.scanner().nextLine();
        boolean checkName = false;
        System.out.println(GREEN + "       List Pets search with name : " + nameSearch);
        System.out.println(".-------------------------------------------------------------------------------------------------------------------------------------------------------------.");
        System.out.println("|      ID      |             NAME                |          COLOR         |       CATEGORY       |     PRICE(VND)    |    AMOUNT    |         COUNTRY         |");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------|");

        for (Pet pet : petList) {
            if (pet.getPetName().contains(nameSearch)) {
                         System.out.printf("|      %-8d|     %-28s|         %-15s|       %-15s|     %-14.2f|    %-10d|         %-16s|\n",
                    pet.getId(), pet.getPetName(),pet.getColor(),pet.getCategory().getCategoryName(),pet.getPrice(),pet.getAmount(),pet.getCountry().getCountryName());
                checkName = true;
            }
        }
        if (!checkName) {
            System.out.println(GREEN + "|"+ RED +"  Pet not found !!!                                                                                                                                 |"+ RESET);
        }
        System.out.println(GREEN+      "'-------------------------------------------------------------------------------------------------------------------------------------------------------------'" + RESET);
        System.out.println(" ");
    }

    public void deletePet() {
        showListPet();
        System.out.println("     Enter Pet's id to delete                      ");
        int idDelete = Config.getValidInteger();
        if (petController.findIdPet(idDelete) == null) {
            System.out.println(RED + ".--------------------------------------------------------.");
            System.out.println("|    ID not found !!!                                    |");
            System.out.println("'--------------------------------------------------------'\n" + RESET);
            return;
        }
        int choiceDelete;
        while (true) {
            System.out.println(GREEN + ".-------------------------------------------.");
            System.out.println("| Do you want delete? |  1. YES  |  2. NO   |");
            System.out.println("'-------------------------------------------'" + RESET);
            choiceDelete = Config.getValidInteger();
            switch (choiceDelete) {
                case 1:
                    petController.deletePet(idDelete);
                    System.out.println(GREEN + ".--------------------------------------------------------.");
                    System.out.println("|    Delete successfully!                                |");
                    System.out.println("'--------------------------------------------------------'\n" + RESET);
                    return;
                case 2:
                    new ViewPet().menu();
                    break;
            }
            System.out.println(" ");
        }
    }

    public void updatePet() {
        showListPet();
        System.out.println("    Enter Pet's Id to edit                              ");
        int idEdit = Config.getValidInteger();
        if (petController.findIdPet(idEdit) == null) {
            System.out.println(".--------------------------------------------------------.");
            System.out.println(RED + "|    ID not found !!!                                    |" + RESET);
            System.out.println("'--------------------------------------------------------'\n");
            return;
        }
        //name
        String name;
        while (true) {
            System.out.println("Enter the name Pet: ");
            name = Config.scanner().nextLine();
            if (name.matches("[a-z A-Z0-9]{1,50}")) {
                break;
            } else {
                System.out.println(RED + "Invalid name, try again !!!" + RESET);
            }
        }
        //color
        String color;
        while (true) {
            System.out.println("Enter the color Pet: ");
            color = Config.scanner().nextLine();
            if (color.matches("[a-z A-Z0-9]{1,50}")) {
                break;
            } else {
                System.out.println(RED + "Invalid color, try again !!!" + RESET);
            }
        }
        //category
        System.out.println("Enter number to choose CATEGORY:");
        for (int i = 0; i < CategoryServiceIMPL.categoryList.size(); i++) {
            System.out.println(i + 1 + ": " + CategoryServiceIMPL.categoryList.get(i).toString());

        }
        System.out.println("Input Id Category' Pet: ");
        int idCategory;
        Category category;
        while (true){
            idCategory = Config.getValidInteger();
            category = categoryController.findIdCategory(idCategory);
            if (category != null){
                break;
            }
            System.out.println(RED + "Invalid ID, try again !!!" + RESET);
        }

        //price
        double price;
        while (true) {
            System.out.println("Enter the price Pet: ");
            price = Config.getValidInteger();
            if (price >=0) {
                break;
            } else {
                System.out.println(RED + "Invalid price, try again !!!" + RESET);
            }
        }

        //amount
        int amount;
        while (true) {
            System.out.println("Enter the amount Pet: ");
            amount = Config.getValidInteger();
            if (amount >= 0) {
                break;
            } else {
                System.out.println(RED + "Invalid name, try again !!!" + RESET);
            }
        }

        // country
        System.out.println("Enter number to choose COUNTRY:");
        for (int i = 0; i < CountryServiceIMPL.countryList.size(); i++) {
            System.out.println(i + 1 + ": " + CountryServiceIMPL.countryList.get(i).toString());
        }
        System.out.println("Input Id country' pet: ");
        int idCountry;
        Country country;
        while (true){
            idCountry = Config.getValidInteger();
            country = countryController.findIDCountry(idCountry);
            if (country != null){
                break;
            }
            System.out.println(RED + "Invalid ID, try again !!!" + RESET);
        }

        Pet pet = new Pet(idEdit, name, color, category, price, amount, country);
        petController.updatePet(pet);

        System.out.println(GREEN + ".--------------------------------------------------------.");
        System.out.println("|    Edit successfully                                   |");
        System.out.println("'--------------------------------------------------------'\n" + RESET);
        System.out.println(" ");
    }

    public void createPet() {
        //id
        int id = petController.getLastId();

        //name
        String name;
        while (true) {
            System.out.println("Enter the name Pet: ");
            name = Config.scanner().nextLine();
            if (name.matches("[a-z A-Z0-9]{1,50}")) {
                break;
            } else {
                System.out.println(RED + "Invalid name, try again !!!" + RESET);
            }
        }
        //color
        String color;
        while (true) {
            System.out.println("Enter the color Pet: ");
            color = Config.scanner().nextLine();
            if (color.matches("[a-z A-Z0-9]{1,50}")) {
                break;
            } else {
                System.out.println(RED + "Invalid color, try again !!!" + RESET);
            }
        }
        //category
        System.out.println("Enter number to choose CATEGORY:");
        for (int i = 0; i < CategoryServiceIMPL.categoryList.size(); i++) {
            System.out.println(CategoryServiceIMPL.categoryList.get(i).toString());
        }
        System.out.println("Input Id Category' Pet: ");
        int idCategory;
        Category category;
        while (true){
            idCategory = Config.getValidInteger();
            category = categoryController.findIdCategory(idCategory);
            if (category != null){
                break;
            }
            System.out.println(RED + "Invalid ID, try again !!!" + RESET);
        }

        //price
        double price;
        while (true) {
            System.out.println("Enter the price Pet: ");
            price = Config.getValidInteger();
            if (price > 0) {
                break;
            } else {
                System.out.println(RED + "Invalid price, try again !!!" + RESET);
            }
        }

        //amount
        int amount;
        while (true) {
            System.out.println("Enter the amount Pet: ");
            amount = Config.getValidInteger();
            if (amount > 0) {
                break;
            } else {
                System.out.println(RED + "Invalid name, try again !!!" + RESET);
            }
        }

        // country
        System.out.println("Enter number to choose COUNTRY:");
        for (int i = 0; i < CountryServiceIMPL.countryList.size(); i++) {
            System.out.println(CountryServiceIMPL.countryList.get(i).toString());
        }
        System.out.println("Input Id country' Pet: ");
        int idCountry;
        Country country;
        while (true){
            idCountry = Config.getValidInteger();
            country = countryController.findIDCountry(idCountry);
            if (country != null){
                break;
            }
            System.out.println(RED + "Invalid ID, try again !!!" + RESET);
        }

        //create new pet
        Pet pet = new Pet(id, name, color, category, price, amount, country);
        petController.savePet(pet);

        System.out.println(GREEN + "Create Category in list success !!!" + RESET);
        System.out.println(" ");
    }

    public void showListPet() {
        System.out.println(GREEN + "\n     LIST PETS                        ");
        System.out.println(".-------------------------------------------------------------------------------------------------------------------------------------------------------------.");
        System.out.println("|"+WHITE+"      ID      |             NAME                |          COLOR         |       CATEGORY       |     PRICE(VND)    |    AMOUNT    |         COUNTRY         "+GREEN+"|");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------|");

        for (Pet pet : petList) {
            System.out.printf("|      %-8d|     %-28s|         %-15s|       %-15s|     %-14.2f|    %-10d|         %-16s|\n",
                    pet.getId(), pet.getPetName(), pet.getColor(), pet.getCategory().getCategoryName(), pet.getPrice(), pet.getAmount(), pet.getCountry().getCountryName());
        }
        System.out.println("'-------------------------------------------------------------------------------------------------------------------------------------------------------------'\n" + RESET);

    }

    public void sortPet() {
        petController.sortByPrice();
        System.out.println("LIST PET SORT BY PRICE");
        System.out.println(GREEN+".-------------------------------------------------------------------------------------------------------------------------------------------------------------.");
        System.out.println("|"+WHITE+"      ID      |             NAME                |          COLOR         |       CATEGORY       |     PRICE(VND)    |    AMOUNT    |         COUNTRY         "+GREEN+"|");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println(petController.showPetList());
        System.out.println("'-------------------------------------------------------------------------------------------------------------------------------------------------------------'\n" + RESET);

    }
}
