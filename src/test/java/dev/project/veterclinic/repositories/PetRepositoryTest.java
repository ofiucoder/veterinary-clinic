package dev.project.veterclinic.repositories;

import dev.project.veterclinic.enums.PetType;
import dev.project.veterclinic.models.Owner;
import dev.project.veterclinic.models.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

@DataJpaTest
public class PetRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PetRepository petRepository;

    private Owner owner;
    private Pet pet;

    @BeforeEach
    void setUp() {
        owner = new Owner();
        owner.setFirstName("John");
        owner.setLastName("Doe");
        owner.setDni("1234W678");
        owner.setPhoneNumber("987654321");
        entityManager.persistAndFlush(owner);

        pet = new Pet();
        pet.setName("Max");
        pet.setGender("Male");
        pet.setPetType(PetType.DOG);
        pet.setOwner(owner);
        pet.setDateOfBirth(LocalDateTime.now());
        entityManager.persistAndFlush(pet);
    }

    @Test
    void testFindByOwnerDniAndPetId() {
        Pet foundPet = petRepository.findByOwnerDniAndPetId(owner.getDni(), pet.getId());
        assertNotNull(foundPet, "Pet should be found");
        assertEquals(pet.getId(), foundPet.getId(), "Pet ID should match");
        assertEquals(pet.getName(), foundPet.getName(), "Pet name should match");
    }

}
