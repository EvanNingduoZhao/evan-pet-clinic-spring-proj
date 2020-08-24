package evan.springframework.evanpetclinicspringproj.services.map;

import evan.springframework.evanpetclinicspringproj.model.Speciality;
import evan.springframework.evanpetclinicspringproj.model.Vet;
import evan.springframework.evanpetclinicspringproj.services.SpecialityService;
import evan.springframework.evanpetclinicspringproj.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetServiceMap extends AbstractMapService<Vet,Long> implements VetService {

    private final SpecialityService specialityService;

    public VetServiceMap(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet object) {
        //在save一个vet之前，先看看这个vet的Specialities的hashset是不是空的
        if (object.getSpecialities().size()>0){
            //如果不是空的，那么对于Specialities这个hashset里的每一个Speciality
            object.getSpecialities().forEach(speciality -> {
                //如果这个Speciality Object没有id的话
                if(speciality.getId()==null){
                    //就用过specialityService里的save method给它assign一个id
                    Speciality savedSpeciality = specialityService.save(speciality);
                    // 下面这行就是defensive coding了，没有也可以，因为
                    // specialityService的save是一定会给这个object assign一个id的
                    speciality.setId(savedSpeciality.getId());
                }
            });
        }
        return super.save(object);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
