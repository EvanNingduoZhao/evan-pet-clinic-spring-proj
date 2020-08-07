package evan.springframework.evanpetclinicspringproj.services;

import evan.springframework.evanpetclinicspringproj.model.Vet;

import java.util.Set;

public interface VetService {

    Vet findById(Long id);
    Vet save(Vet vet);
    Set<Vet> findAll();
}