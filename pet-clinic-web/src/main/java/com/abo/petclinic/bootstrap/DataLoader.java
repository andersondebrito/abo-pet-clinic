package com.abo.petclinic.bootstrap;

import com.abo.petclinic.model.Owner;
import com.abo.petclinic.model.Vet;
import com.abo.petclinic.services.OwnerService;
import com.abo.petclinic.services.VetService;
import com.abo.petclinic.services.map.OwnerServiceMap;
import com.abo.petclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader() {
        ownerService = new OwnerServiceMap();
        vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Anderson");
        owner1.setLastName("Oliveira");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Danielle");
        owner2.setLastName("Cavalcante");

        ownerService.save(owner2);

        System.out.println("Loading owners...");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Alice");
        vet1.setLastName("Oliveira");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Mariah");
        vet2.setLastName("Oliveira");

        vetService.save(vet2);

        System.out.println("Loading vets...");
    }
}
