package evan.springframework.evanpetclinicspringproj.services;

import evan.springframework.evanpetclinicspringproj.model.Owner;



public interface OwnerService extends CrudService<Owner, Long>{
    Owner findByLastName(String lastname);

}
