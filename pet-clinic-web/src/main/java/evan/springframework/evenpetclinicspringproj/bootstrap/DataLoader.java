package evan.springframework.evenpetclinicspringproj.bootstrap;

import evan.springframework.evanpetclinicspringproj.model.Owner;
import evan.springframework.evanpetclinicspringproj.model.Vet;
import evan.springframework.evanpetclinicspringproj.services.OwnerService;
import evan.springframework.evanpetclinicspringproj.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component

// CommandLineRunner是一个springboot特殊的interface，在Spring启动以后，spring context up and running
// 之后就会首先找到所有implements CommandLine的class，之后execute它们里面的run method
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;


    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstname("Michael");
        owner1.setLastname("Weston");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstname("Fiona");
        owner2.setLastname("Glenanne");

        ownerService.save(owner2);
        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstname("Sam");
        vet1.setLastname("Axe");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstname("Jessie");
        vet2.setLastname("Porter");

        vetService.save(vet2);
        System.out.println("Loaded Vets.....");



    }
}
