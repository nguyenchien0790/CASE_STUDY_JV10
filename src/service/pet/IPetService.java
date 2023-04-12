package service.pet;


import model.pet.Pet;
import service.IGenericService;

public interface IPetService extends IGenericService<Pet> {
    void sortPetByPrice();
}
