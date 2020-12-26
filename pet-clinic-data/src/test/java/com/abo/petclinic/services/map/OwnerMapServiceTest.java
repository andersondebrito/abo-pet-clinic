package com.abo.petclinic.services.map;

import com.abo.petclinic.model.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    final Long ownerId = 1l;
    final String lastName = "Smith";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());

        Owner owner = new Owner();
        owner.setId(ownerId);
        owner.setLastName(lastName);

        ownerMapService.save(owner);
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerMapService.findAll();

        Assertions.assertEquals(1, owners.size());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerMapService.findByLastName(lastName);

        Assertions.assertNotNull(owner);
        Assertions.assertEquals(ownerId, owner.getId());
    }

    @Test
    void findByLastNameNotFound() {
        Owner owner = ownerMapService.findByLastName("test");

        Assertions.assertNull(owner);
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);

        Assertions.assertEquals(0,ownerMapService.findAll().size());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));

        Assertions.assertEquals(0,ownerMapService.findAll().size());
    }

    @Test
    void saveExistId() {
        Long id = 2l;
        Owner owner = new Owner();
        owner.setId(id);

        Owner savedOwner = ownerMapService.save(owner);
        Assertions.assertEquals(savedOwner.getId(), owner.getId());
    }

    @Test
    void saveNoId() {
        Owner owner = new Owner();
        Owner savedOwner = ownerMapService.save(owner);

        Assertions.assertNotNull(savedOwner);
        Assertions.assertNotNull(savedOwner.getId());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);

        Assertions.assertEquals(ownerId,owner.getId());
    }

}