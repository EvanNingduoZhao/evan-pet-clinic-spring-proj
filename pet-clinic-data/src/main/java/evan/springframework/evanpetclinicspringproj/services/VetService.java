package evan.springframework.evanpetclinicspringproj.services;

import evan.springframework.evanpetclinicspringproj.model.Owner;
import evan.springframework.evanpetclinicspringproj.model.Vet;

import java.util.Set;

public class VetService {

    Vet findById(Long id);
    Vet save(Owner vet);
    Set<Vet> findAll();
}
