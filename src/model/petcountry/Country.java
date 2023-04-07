package model.petcountry;

import java.io.Serializable;

public class Country implements Serializable {
    private int id;
    private String countryName;

    public Country() {
    }

    public Country(int id, String countryName) {
        this.id = id;
        this.countryName = countryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "Country { " +
                "ID= " + id +
                " - CountryName=" + countryName + " }";
    }
}
