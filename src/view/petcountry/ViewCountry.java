package view.petcountry;

import config.Config;
import cotroller.petcountry.CountryController;
import model.petcountry.Country;

import java.util.List;

import static config.Color.*;

public class ViewCountry {

    CountryController countryController = new CountryController();
    List<Country> countryList = countryController.showCategoryList();

    public void menu() {

        System.out.println(".--------------------------------------------------------.");
        System.out.println("|                      COUNTRY MANAGER                   |");
        System.out.println("|--------------------------------------------------------|");
        System.out.println("|                     1. SHOW LIST COUNTRY               |");
        System.out.println("|                     2. ADD COUNTRY                     |");
        System.out.println("|                     3. UPDATE COUNTRY                  |");
        System.out.println("|                     4. DELETE COUNTRY                  |");
        System.out.println("|                     5. SEARCH COUNTRY                  |");
        System.out.println("|                     0. BACK                            |");
        System.out.println("'--------------------------------------------------------'\n");
        switch (Config.getValidInteger()) {
            case 1:
                showListCountry();
                break;
            case 2:
                createCountry();
                break;
            case 3:
                updateCountry();
                break;
            case 4:
                deleteCountry();
                break;
            case 5:
                searchCountry();
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice : ");
        }
        menu();
    }

    private void searchCountry() {
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Enter Country's name to search :                    |");
        System.out.print("|    ");
        String nameSearch = Config.scanner().nextLine();
        boolean checkName = false;
        System.out.println("'--------------------------------------------------------'");

        System.out.println(GREEN + "       Country list search with name : " + nameSearch);
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|       ID       |               NAME                    |");
        System.out.println("|--------------------------------------------------------|"+RESET);
        for (Country country : countryList) {
            if (country.getCountryName().contains(nameSearch)) {
                System.out.printf(GREEN+"|       %-9d|              %-25s|\n"+RESET, country.getId(), country.getCountryName());
                checkName = true;
            }
        }

        if (!checkName){
            System.out.println(GREEN+"|"+RED+"  Country not found !!!"+RESET);

        }
        System.out.println(GREEN+"'--------------------------------------------------------'" + RESET);
        System.out.println(" ");
    }
    private void deleteCountry() {
        showListCountry();
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Enter Country's id to delete                        |");
        System.out.print("|    ");
        int idDelete = Config.getValidInteger();
        System.out.println("'--------------------------------------------------------'\n");
        if (countryController.findIDCountry(idDelete) == null) {
            System.out.println(".--------------------------------------------------------.");
            System.out.println(RED+"|    ID not found !!!                                    |"+RESET);
            System.out.println("'--------------------------------------------------------'\n");
            return;
        }
        countryController.deleteCountry(idDelete);
        System.out.println(".--------------------------------------------------------.");
        System.out.println(GREEN + "|    Delete successfully!                                |" + RESET);
        System.out.println("'--------------------------------------------------------'\n");
        System.out.println(" ");
    }


    private void updateCountry() {
        showListCountry();
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Enter Country's Id to edit                          |");
        System.out.print("|    ");
        int idEdit = Config.getValidInteger();
        System.out.println("'--------------------------------------------------------'\n");
        if (countryController.findIDCountry(idEdit) == null) {
            System.out.println(".--------------------------------------------------------.");
            System.out.println(RED+"|    ID not found !!!                                    |"+RESET);
            System.out.println("'--------------------------------------------------------'\n");
            return;
        }
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Enter Country's name                                |");
        System.out.print("|    ");
        String name;
        while (true) {
            name = Config.scanner().nextLine();
            if (name.matches("[a-zA-Z0-9]{1,10}")) {
                break;
            } else {
                System.out.println(RED+"Invalid name, try again !!!"+RESET);
            }
        }
        System.out.println("'--------------------------------------------------------'\n");

        countryController.updateCountry(new Country(idEdit, name));
        System.out.println(".--------------------------------------------------------.");
        System.out.println(GREEN + "|    Edit successfully                                   |" + RESET);
        System.out.println("'--------------------------------------------------------'\n");
        System.out.println(" ");
    }

    private void createCountry() {
        //id
        int id = countryController.getLastId();
        //name
        String name;
        while (true) {
            System.out.println("Enter the name Country: ");
            name = Config.scanner().nextLine();
            if (name.matches("[a-zA-Z0-9]{1,10}")) {
                break;
            } else {
                System.out.println(RED+"Invalid name, try again !!!"+RESET);
            }
        }
        Country country = new Country(id, name);
        countryController.saveCountry(country);

        System.out.println(GREEN + "Create Country in list success !!!" + RESET);
        System.out.println(" ");
    }

    public void showListCountry() {
        System.out.println(GREEN+"                      COUNTRY LIST                       ");
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|"+WHITE+"       ID       |               NAME                    "+GREEN+"|");
        System.out.println("|--------------------------------------------------------|");

        for (Country country : countryList) {
            System.out.printf("|       %-9d|              %-25s|\n", country.getId(), country.getCountryName());
        }
        System.out.println("'--------------------------------------------------------'"+RESET);
        System.out.println(" ");
    }

}
