package service.pet;


import config.Config;
import model.pet.Pet;

import java.util.ArrayList;
import java.util.List;

public class PetServiceIMPL implements IPetService {
    public static  String PATH_PET = "src/data/pet.txt";
    public static Config<List<Pet>> config = new Config<>();
    public static List<Pet> petList = config.read(PATH_PET);

    static {
        if (petList == null) {
            petList = new ArrayList<>();
        }
    }
    @Override
    public List<Pet> findAll() {
        return petList;
    }

    @Override
    public void save(Pet pet) {
        Pet id = findById(pet.getId());
        if (id == null){
            petList.add(pet);
        }else {
            Pet editPet = findById(pet.getId());
            editPet.setPetName(pet.getPetName());
            editPet.setColor(pet.getColor());
            editPet.setCategory(pet.getCategory());
            editPet.setPrice(pet.getPrice());
            editPet.setAmount(pet.getAmount());
            editPet.setCountry(pet.getCountry());
        }
        updateData();

    }

    @Override
    public void remote(int id) {
        petList.remove(findById(id));
        updateData();
    }

    @Override
    public Pet findById(int id) {
        for (Pet pet: petList) {
            if (pet.getId() == id){
                return pet;
            }
        }
        return null;
    }

    @Override
    public int getLastId() {
        return petList.isEmpty() ? 1 : petList.get(petList.size() - 1).getId() +1;

    }

    @Override
    public void updateData() {
        config.write(PATH_PET, petList);
    }

    @Override
    public Pet findByName(String name) {
        for (Pet pet: petList) {
            if (pet.getPetName().equals(name)){
                return pet;
            }
        }
        return null;
    }
}
