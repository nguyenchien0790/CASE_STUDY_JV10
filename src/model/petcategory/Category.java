package model.petcategory;

import java.io.Serializable;

public class Category implements Serializable {
    private int id;
    private String categoryName;

    public Category() {
    }

    public Category(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return String.format("%5d. %-20s",id ,categoryName);



//        return "Category { " +
//                "ID = " + id +
//                " - CategoryName = " + categoryName + " }";
    }
}
