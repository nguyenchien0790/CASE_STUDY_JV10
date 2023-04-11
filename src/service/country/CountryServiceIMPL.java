package service.country;

import config.Config;
import model.petcountry.Country;

import java.util.ArrayList;
import java.util.List;

public class CountryServiceIMPL implements ICountryService{
    public static String PATH_COUNTRY = "src/data/country.txt";
    public static Config<List<Country>> config = new Config<>();
    public static List<Country> countryList = config.read(PATH_COUNTRY);

    static {
        if(countryList == null){
            countryList = new ArrayList<>();
        }
    }
    @Override
    public List<Country> findAll() {
        return countryList;
    }

    @Override
    public void save(Country country) {
        Country id = findById(country.getId());
        if (id == null){
            countryList.add(country);
        }else {
            Country editCountry = findById(country.getId());
            editCountry.setCountryName(country.getCountryName());
        }
        updateData();


    }

    @Override
    public void remote(int id) {
        countryList.remove(findById(id));
        updateData();

    }

    @Override
    public Country findById(int id) {
        for (Country country : countryList) {
            if (country.getId() == id){
                return country;
            }
        }
        return null;
    }

    @Override
    public int getLastId() {
        return countryList.isEmpty() ? 1 : countryList.get(countryList.size()-1).getId()+1;
    }

    @Override
    public void updateData() {
        config.write(PATH_COUNTRY,countryList);
    }

    @Override
    public Country findByName(String name) {
        for (Country country : countryList) {
            if (country.getCountryName().equalsIgnoreCase(name)){
                return country;
            }
        }
        return null;
    }
}
