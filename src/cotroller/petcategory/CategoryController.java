package cotroller.petcategory;

import model.petcategory.Category;
import service.category.CategoryServiceIMPL;
import service.category.ICategoryService;

import java.util.List;

public class CategoryController {
    ICategoryService categoryService = new CategoryServiceIMPL();

    public int getLastId(){
        return categoryService.getLastId();
    }

    public List<Category> showCategoryList(){
        return categoryService.findAll();
    }

    public void saveCategory(Category category){
        categoryService.save(category);
    }

    public Category findIdCategory(int id){
        return categoryService.findById(id);
    }

    public void deleteCategory(int id){
        categoryService.remote(id);
    }

    public void updateCategory(Category category){
        categoryService.save(category);
    }
}
