package dev.project.veterclinic.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.Arrays;

import dev.project.veterclinic.enums.PetType;

public class BreedTest {

    private Breed breed;
    private Pet pet;
    private Owner owner;

    @BeforeEach
    void setUp() {
        owner = new Owner("Carlos", "López", "12345678A", "654321987");
        pet = new Pet("Max", LocalDateTime.of(2017, 5, 12, 0, 0), PetType.DOG, "Male", new Breed("Labrador"), owner);
        breed = new Breed("Labrador");
    }

    @Test
    void testBreedName() {
        breed.setBreedName("Chihua-hua");
        assertEquals("Chihua-hua", breed.getBreedName());
    }

    @Test
    void testPets() {
        breed.setPets(Arrays.asList(pet));
        assertEquals(1, breed.getPets().size());
    }
}
