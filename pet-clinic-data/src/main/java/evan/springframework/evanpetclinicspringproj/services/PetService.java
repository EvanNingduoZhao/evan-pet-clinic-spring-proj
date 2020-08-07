package evan.springframework.evanpetclinicspringproj.services;

import evan.springframework.evanpetclinicspringproj.model.Pet;

import java.util.Set;

public interface PetService {
    Pet findById(Long id);
    Pet save(Pet pet);
    Set<Pet> findAll();
}
