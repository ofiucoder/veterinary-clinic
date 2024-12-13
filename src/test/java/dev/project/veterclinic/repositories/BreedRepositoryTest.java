package dev.project.veterclinic.repositories;

import dev.project.veterclinic.models.Breed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BreedRepositoryTest {

    @Autowired
    private BreedRepository breedRepository;

    // Prepare data for the tests
    @BeforeEach
    void setUp() {
        // Ensure names are saved as expected
        Breed breed1 = new Breed("Labrador mini");
        Breed breed2 = new Breed("Beagle mini");
        breedRepository.save(breed1);
        breedRepository.save(breed2);
    }

    @Test
    void testCountBreedsByName() {
        // Check if countBreedsByName works properly
        int count = breedRepository.countBreedsByName("Labrador mini");
        assertEquals(1, count); // There should be 1 "Labrador mini" breed

        count = breedRepository.countBreedsByName("Poodle mini");
        assertEquals(0, count); // There should be no "Poodle mini" breed
    }

    @Test
    void testExistByNameLowerCase() {
        // Check if existByNameLowerCase works properly (case insensitive)
        boolean exists = breedRepository.existByNameLowerCase("Beagle mini");
        assertTrue(exists); // "Beagle mini" should exist

        exists = breedRepository.existByNameLowerCase("Poodle mini");
        assertFalse(exists); // "Poodle mini" should not exist
    }

    @Test
    void testFindByName() {
        // Check if findByName works properly
        Breed breed = breedRepository.findByName("Labrador mini");
        assertNotNull(breed);
        assertEquals("Labrador mini", breed.getBreedName()); // Should return the "Labrador mini" breed

        breed = breedRepository.findByName("Bulldog mini");
        assertNull(breed); // Should return null since "Bulldog" does not exist
    }
}
