package com.abo.petclinic.bootstrap;

import com.abo.petclinic.model.Owner;
import com.abo.petclinic.model.Vet;
import com.abo.petclinic.services.OwnerService;
import com.abo.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
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
        owner1.setFirstName("Anderson");
        owner1.setLastName("Oliveira");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Danielle");
        owner2.setLastName("Cavalcante");

        ownerService.save(owner2);

        System.out.println("Loading owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Alice");
        vet1.setLastName("Oliveira");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Mariah");
        vet2.setLastName("Oliveira");

        vetService.save(vet2);

        System.out.println("Loading vets...");
    }
}
