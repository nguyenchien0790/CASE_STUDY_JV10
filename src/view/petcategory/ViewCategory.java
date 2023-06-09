package view.petcategory;

import config.Config;
import cotroller.petcategory.CategoryController;
import model.petcategory.Category;

import java.util.List;

import static config.Color.*;

public class ViewCategory {
    CategoryController categoryController = new CategoryController();
    List<Category> categoryList = categoryController.showCategoryList();

    public void menu(){

        System.out.println(".--------------------------------------------------------.");
        System.out.println("|                       CATEGORY LIST                    |");
        System.out.println("|--------------------------------------------------------|");
        System.out.println("|                     1. SHOW LIST CATEGORY              |");
        System.out.println("|                     2. ADD CATEGORY                    |");
        System.out.println("|                     3. UPDATE CATEGORY                 |");
        System.out.println("|                     4. DELETE CATEGORY                 |");
        System.out.println("|                     5. SEARCH CATEGORY                 |");
        System.out.println("|                     0. BACK                            |");
        System.out.println("'--------------------------------------------------------'\n");
        switch (Config.getValidInteger()){
            case 1:
                showListCategory();
                break;
            case 2:
                createCategory();
                break;
            case 3:
                updateCategory();
                break;
            case 4:
                deleteCategory();
                break;
            case 5:
                searchCategory();
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice : ");
        }
        menu();
    }

    public void searchCategory() {
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Enter Category's name to search :                   |");
        System.out.print("|    ");
        String nameSearch = Config.scanner().nextLine();
        boolean checkName = false;
        System.out.println("'--------------------------------------------------------'");

        System.out.println(GREEN + "       Category list search with name : " + nameSearch);
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|"+WHITE+"       ID       |               NAME                    "+GREEN+"|");
        System.out.println("|--------------------------------------------------------|");;
        for (Category category : categoryList) {
            if (category.getCategoryName().contains(nameSearch)) {
                System.out.printf("|       %-9d|              %-25s|\n", category.getId(), category.getCategoryName());
                checkName = true;
            }
        }

        if (!checkName){
            System.out.println("|"+RED+"  Category not found !!!");

        }
        System.out.println(GREEN+"'--------------------------------------------------------'" + RESET);
        System.out.println(" ");
    }
    public void deleteCategory() {
        showListCategory();
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Enter Category's id to delete                       |");
        System.out.print("|    ");
        int idDelete = Config.getValidInteger();
        System.out.println("'--------------------------------------------------------'\n");
        if (categoryController.findIdCategory(idDelete) == null) {
            System.out.println(".--------------------------------------------------------.");
            System.out.println("|"+RED+"    ID not found !!!                                    "+RESET+"|");
            System.out.println("'--------------------------------------------------------'\n");
            return;
        }
        categoryController.deleteCategory(idDelete);
        System.out.println(".--------------------------------------------------------.");
        System.out.println(GREEN + "|    Delete successfully!                                |" + RESET);
        System.out.println("'--------------------------------------------------------'\n");
        System.out.println(" ");
    }

    public void updateCategory() {
        showListCategory();
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Enter Category's Id to edit                         |");
        System.out.print("|    ");
        int idEdit = Config.getValidInteger();
        System.out.println("'--------------------------------------------------------'\n");
        if (categoryController.findIdCategory(idEdit) == null) {
            System.out.println(".--------------------------------------------------------.");
            System.out.println(RED+"|    ID not found !!!                                    |"+RESET);
            System.out.println("'--------------------------------------------------------'\n");
            return;
        }
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Enter Category's name :                             |");
        System.out.print("|    ");
        String name;
        while (true) {
            name = Config.scanner().nextLine();
            if (name.matches("[a-zA-Z0-9]{1,10}")) {
                break;
            } else {
                System.out.println(RED+"Invalid name, try again !!!"+RESET);
                System.out.println("|    Enter Category's name :                             |");
                System.out.print("|    ");
            }
        }
        System.out.println("'--------------------------------------------------------'\n");
        categoryController.updateCategory(new Category(idEdit, name));
        System.out.println(".--------------------------------------------------------.");
        System.out.println(GREEN + "|    Edit successfully                                   |" + RESET);
        System.out.println("'--------------------------------------------------------'\n");
        System.out.println(" ");
    }

    public void createCategory() {
        //id
        int id = categoryController.getLastId();
        //name
        String name;
        while (true) {
            System.out.println("Enter the name Category: ");
            name = Config.scanner().nextLine();
            if (name.matches("[a-zA-Z0-9]{1,10}")) {
                break;
            } else {
                System.out.println(RED+"Invalid name, try again !!!"+RESET);
            }
        }
        Category category = new Category(id, name);
        categoryController.saveCategory(category);

        System.out.println(GREEN + "Create Category in list success !!!" + RESET);
        System.out.println(" ");
    }
    public void showListCategory() {
        System.out.println(GREEN+"                      CATEGORY LIST                       ");
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|"+WHITE+"       ID       |               NAME                    "+GREEN+"|");
        System.out.println("|--------------------------------------------------------|");
        for (Category category : categoryList) {
            System.out.printf("|       %-9d|              %-25s|\n", category.getId(), category.getCategoryName());
        }
        System.out.println("'--------------------------------------------------------'"+RESET);
        System.out.println(" ");
    }
}
