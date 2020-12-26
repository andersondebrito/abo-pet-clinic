package com.abo.petclinic.services.springdatajpa;

import com.abo.petclinic.model.Owner;
import com.abo.petclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class OwnerSDJPAServiceTest {

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerSDJPAService service;

    Owner ownerTest;

    @BeforeEach
    void setUp() {
        ownerTest = new Owner();
        ownerTest.setId(1l);
        ownerTest.setLastName("Smith");
    }

    @Test
    void findByLastName() {

        Owner owner = new Owner();
        owner.setId(1l);
        owner.setLastName("Smith");

        Mockito.when(service.findByLastName(Mockito.any())).thenReturn(owner);

        Owner returnedOwner = ownerRepository.findByLastName("Smith");

        Assertions.assertEquals(owner.getLastName(), returnedOwner.getLastName());
        Mockito.verify(ownerRepository).findByLastName(Mockito.any());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = new HashSet<>();
        Owner owner1 = new Owner();
        owner1.setId(1l);
        owner1.setLastName("Anders");
        ownerSet.add(owner1);

        Owner owner2 = new Owner();
        owner2.setId(12l);
        owner2.setLastName("Oliver");
        ownerSet.add(owner2);

        Mockito.when(service.findAll()).thenReturn(ownerSet);

        Set<Owner> owners = service.findAll();

        Assertions.assertNotNull(owners);
        Assertions.assertEquals(2,owners.size());

    }

    @Test
    void findById() {
        Mockito.when(ownerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(ownerTest));

        Owner ownerReturned = service.findById(1l);

        Assertions.assertNotNull(ownerReturned);
        Assertions.assertEquals(ownerTest.getId(), ownerReturned.getId());
    }

    @Test
    void findByIdNotFound() {
        Mockito.when(ownerRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Owner ownerReturned = service.findById(1l);

        Assertions.assertNull(ownerReturned);
    }

    @Test
    void save() {
        Owner ownerToSave = new Owner();
        ownerToSave.setId(2l);

        Mockito.when(ownerRepository.save(Mockito.any())).thenReturn(ownerToSave);

        Owner savedOwner = service.save(ownerToSave);

        Assertions.assertNotNull(savedOwner);
        Assertions.assertEquals(ownerToSave.getId(), savedOwner.getId());
        Mockito.verify(ownerRepository).save(Mockito.any());
    }

    @Test
    void delete() {
        service.delete(ownerTest);

        Mockito.verify(ownerRepository).delete(Mockito.any());
    }

    @Test
    void deleteById() {
        service.deleteById(1l);

        Mockito.verify(ownerRepository).deleteById(Mockito.any());
    }
}