package dev.project.veterclinic.repositories;

import dev.project.veterclinic.enums.PetType;
import dev.project.veterclinic.models.Owner;
import dev.project.veterclinic.models.Pet;
import dev.project.veterclinic.models.Treatment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TreatmentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TreatmentRepository treatmentRepository;

    private Owner owner;
    private Pet pet;
    private Treatment treatment;

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

        treatment = new Treatment();
        treatment.setPet(pet);
        treatment.setNote("Vaccination");
        treatment.setDate(LocalDateTime.now());
        entityManager.persistAndFlush(treatment);
    }

    @Test
    void testFindByPetId() {
        List<Treatment> treatments = treatmentRepository.findByPetId(pet.getId());
        assertNotNull(treatments, "Treatments should be found");
        assertFalse(treatments.isEmpty(), "Treatments list should not be empty");
        assertEquals(treatment.getNote(), treatments.get(0).getNote(), "Treatment description should match");
    }
}
