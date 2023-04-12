package model.pet;

import model.petcategory.Category;
import model.petcountry.Country;

import java.io.Serializable;

public class Pet implements Serializable {
    private int id;
    private String petName;
    private String color;
    private Category category;
    private double price;
    private int amount;
    private Country country;



    public Pet(int id, String petName, String color, Category category, double price, int amount, Country country) {
        this.id = id;
        this.petName = petName;
        this.color = color;
        this.category = category;
        this.price = price;
        this.amount = amount;
        this.country = country;
    }

    public Pet(int newAmount) {
        this.amount = newAmount;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return String.format("      %-8d|     %-28s|         %-15s|       %-15s|     %-14.2f|    %-10d|         %-16s|\n",
                getId(),getPetName(), getColor(), getCategory().getCategoryName(), getPrice(),getAmount(),getCountry().getCountryName());
    }
}
