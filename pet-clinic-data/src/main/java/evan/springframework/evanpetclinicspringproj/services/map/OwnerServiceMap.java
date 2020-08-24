package evan.springframework.evanpetclinicspringproj.services.map;

import evan.springframework.evanpetclinicspringproj.model.Owner;
import evan.springframework.evanpetclinicspringproj.model.Pet;
import evan.springframework.evanpetclinicspringproj.services.OwnerService;
import evan.springframework.evanpetclinicspringproj.services.PetService;
import evan.springframework.evanpetclinicspringproj.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {
    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        if(object != null){
            if (object.getPets()!=null){
                //下面的code是对于owner的每一个pet，都保证这个pet有petType，且petType有id，且这个pet自己也有id
                object.getPets().forEach(pet ->{
                    if(pet.getPetType()!=null){
                        if(pet.getPetType().getId()==null){
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                    }else{
                        throw new RuntimeException("Pet Type is required");
                    }
                    if (pet.getId()==null){
                        Pet savePet = petService.save(pet);
                        pet.setId(savePet.getId());
                    }
                });
            }
            return super.save(object);
        }else{
            return null;
        }
    }

    @Override
    public Owner findByLastName(String lastname) {
        return null;
    }
}
