package cotroller.pet;

import model.pet.Pet;
import service.category.CategoryServiceIMPL;
import service.category.ICategoryService;
import service.country.CountryServiceIMPL;
import service.country.ICountryService;
import service.pet.IPetService;
import service.pet.PetServiceIMPL;

import java.util.List;

public class PetController {
    IPetService petService = new PetServiceIMPL();
    ICategoryService categoryService = new CategoryServiceIMPL();
    ICountryService countryService = new CountryServiceIMPL();

    public int getLastId(){
        return petService.getLastId();
    }

    public List<Pet> showPetList(){
        return petService.findAll();
    }

    public void savePet(Pet pet){
        petService.save(pet);
    }

    public Pet findIdPet(int id){
        return petService.findById(id);
    }

    public void deletePet(int id){
        petService.remote(id);
    }

    public void updatePet(Pet pet){
        petService.save(pet);
    }
    public void sortByPrice(){
        petService.sortPetByPrice();
    }
    public void editPetCart(int id, Pet newProduct2) {
        Pet product2 = petService.findById(id);
        product2.setAmount(newProduct2.getAmount());
        petService.save(product2);
    }


}
