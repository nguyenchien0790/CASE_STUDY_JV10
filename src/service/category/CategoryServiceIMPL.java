package service.category;

import config.Config;
import model.pet.Pet;
import model.petcategory.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryServiceIMPL implements ICategoryService{
    public static String PATH_CATEGORY = "src/data/category.txt";
    public static Config<List<Category>> config = new Config<>();
    public static List<Category> categoryList = config.read(PATH_CATEGORY);

    static {
        if (categoryList == null) {
            categoryList = new ArrayList<>();
        }
    }

    @Override
    public List<Category> findAll() {
        return categoryList;
    }

    @Override
    public void save(Category category) {
        Category id = findById(category.getId());
        if (id == null){
            categoryList.add(category);
        }else {
            Category editCategory = findById(category.getId());
            editCategory.setCategoryName(category.getCategoryName());
        }
        updateData();
    }

    @Override
    public void remote(int id) {
        categoryList.remove(findById(id));
        updateData();
    }

    @Override
    public Category findById(int id) {
        for (Category category : categoryList) {
            if (category.getId() == id){
                return category;
            }
        }
        return null;
    }

    @Override
    public int getLastId() {
        return categoryList.isEmpty() ? 1 : categoryList.get(categoryList.size()-1).getId()+1;
    }

    @Override
    public void updateData() {
        config.write(PATH_CATEGORY,categoryList);
    }

    @Override
    public Category findByName(String name) {
        for (Category category : categoryList) {
            if (category.getCategoryName().equalsIgnoreCase(name)){
                return category;
            }
        }
        return null;
    }
}
