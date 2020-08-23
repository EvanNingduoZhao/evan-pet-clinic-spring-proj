package evan.springframework.evanpetclinicspringproj.bootstrap;

import evan.springframework.evanpetclinicspringproj.model.Owner;
import evan.springframework.evanpetclinicspringproj.model.PetType;
import evan.springframework.evanpetclinicspringproj.model.Vet;
import evan.springframework.evanpetclinicspringproj.services.OwnerService;
import evan.springframework.evanpetclinicspringproj.services.PetTypeService;
import evan.springframework.evanpetclinicspringproj.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component

// CommandLineRunner是一个springboot特殊的interface，在Spring启动以后，spring context up and running
// 之后就会首先找到所有implements CommandLine的class，之后execute它们里面的run method
public class DataLoader implements CommandLineRunner {
    //这里的ownerService和vetService虽然reference都是OwnerService和PetService这两个interface
    // 但是实际上在下面的constructor interjection里被inject进来的bean是OwnerServiceMap和
    // PetServiceMap这两个concrete class的object instance

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;


    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("Dog");
        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        // 这个被inject进来的ownerService它所属的class OwnerServiceMap是同时extends AbstractMapService
        //和implement OwnerService（OwnerService又是extends CrudService的）的
        // 所以ownerService有一个attribute是一个hashmap，和一堆对这个hashmap进行CRUD operations
        // 的class methods，这个hashmap里存的value是owner objects，key是value那个owner object的id
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");

        ownerService.save(owner2);
        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");

        vetService.save(vet2);
        System.out.println("Loaded Vets.....");



    }
}
