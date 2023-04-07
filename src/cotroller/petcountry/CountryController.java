package cotroller.petcountry;

import model.petcountry.Country;
import service.country.CountryServiceIMPL;
import service.country.ICountryService;

import java.util.List;

public class CountryController {
    ICountryService countryService = new CountryServiceIMPL();

    public int getLastId(){
        return countryService.getLastId();
    }

    public List<Country> showCategoryList(){
        return countryService .findAll();
    }

    public void saveCountry(Country country){
        countryService.save(country);
    }

    public Country findIDCountry(int id){
        return countryService.findById(id);
    }

    public void deleteCountry(int id){
        countryService.remote(id);
    }

    public void updateCountry(Country country){
        countryService.save(country);
    }

}
