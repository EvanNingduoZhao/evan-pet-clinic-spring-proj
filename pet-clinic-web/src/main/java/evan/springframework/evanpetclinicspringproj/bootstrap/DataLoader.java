package evan.springframework.evanpetclinicspringproj.bootstrap;

import evan.springframework.evanpetclinicspringproj.model.*;
import evan.springframework.evanpetclinicspringproj.services.OwnerService;
import evan.springframework.evanpetclinicspringproj.services.PetTypeService;
import evan.springframework.evanpetclinicspringproj.services.SpecialityService;
import evan.springframework.evanpetclinicspringproj.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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
    private final SpecialityService specialityService;


    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception {
        // 这里我们把下面具体loaddata 的code整合成一个method了
        // （选中这一段code，right click, extract method）
        // 现在还看不出这样做的作用，但是以后开始用JPA或者MySql的话，就可以先check petTypeService
        // 里有没有data，因为可能JPA或者MySQL已经load data进去了，只有里面没有data的情况下，才会执行loadData
        // 这里看petTypeService只是用来确定有没有data已经被load进去了，看别的service也一样的
        int count = petTypeService.findAll().size();
        if (count==0) {
            loadData();
        }

    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        // 下面这行的作用是persist radiology, savedRadiology和radiology的区别是savedRadiology是被
        // specialityService的save method return的Speciality object，在执行save这个method时，
        // 给savedRadiology这个object assign了ID
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        radiology.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Morewood Ave");
        owner1.setCity("Pittsburgh");
        owner1.setTelephone("1231231234");

        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");
        owner1.getPets().add(mikesPet);
        // 这个被inject进来的ownerService它所属的class OwnerServiceMap是同时extends AbstractMapService
        //和implement OwnerService（OwnerService又是extends CrudService的）的
        // 所以ownerService有一个attribute是一个hashmap，和一堆对这个hashmap进行CRUD operations
        // 的class methods，这个hashmap里存的value是owner objects，key是value那个owner object的id
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("123 Morewood Ave");
        owner2.setCity("Pittsburgh");
        owner2.setTelephone("1234123123");

        Pet fionasCat = new Pet();
        fionasCat.setName("Just Cat");
        fionasCat.setOwner(owner2);
        fionasCat.setBirthDate(LocalDate.now());
        fionasCat.setPetType(savedCatPetType);
        owner2.getPets().add(fionasCat);

        ownerService.save(owner2);
        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialities().add(savedSurgery);

        vetService.save(vet2);
        System.out.println("Loaded Vets.....");
    }
}
