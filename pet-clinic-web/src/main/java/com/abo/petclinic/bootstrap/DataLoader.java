package com.abo.petclinic.bootstrap;

import com.abo.petclinic.model.Owner;
import com.abo.petclinic.model.Pet;
import com.abo.petclinic.model.PetType;
import com.abo.petclinic.model.Vet;
import com.abo.petclinic.services.OwnerService;
import com.abo.petclinic.services.PetTypeService;
import com.abo.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

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
        PetType savedDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Anderson");
        owner1.setLastName("Oliveira");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("123456789");

        Pet andersonPet = new Pet();
        andersonPet.setPetType(savedDogType);
        andersonPet.setOwner(owner1);
        andersonPet.setBirthDate(LocalDate.now());
        andersonPet.setName("Rex");
        owner1.getPets().add(andersonPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Danielle");
        owner2.setLastName("Cavalcante");
        owner2.setAddress("123 Brickerel");
        owner2.setCity("Miami");
        owner2.setTelephone("123456789");

        Pet daniPet = new Pet();
        daniPet.setPetType(savedCatType);
        daniPet.setOwner(owner2);
        daniPet.setBirthDate(LocalDate.now());
        daniPet.setName("Xer");
        owner2.getPets().add(daniPet);

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
