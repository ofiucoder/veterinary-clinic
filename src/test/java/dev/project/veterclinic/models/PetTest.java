package dev.project.veterclinic.models;

import dev.project.veterclinic.enums.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class PetTest {

    private Pet pet;
    private Breed breed;
    private Owner owner;

    @BeforeEach
    void setUp() {
        // Setup data for tests
        breed = new Breed("Labrador");
        owner = new Owner("John", "Doe", "12345", "555-1234");
        pet = new Pet(1, "Max", LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0), PetType.DOG, "Male", breed, owner);
    }

    @Test
    void testGetId() {
        assertEquals(1, pet.getId());
    }

    @Test
    void testGetName() {
        assertEquals("Max", pet.getName());
    }

    @Test
    void testGetDateOfBirth() {
        assertEquals(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0), pet.getDateOfBirth());
    }

    @Test
    void testGetGender() {
        assertEquals("Male", pet.getGender());
    }

    @Test
    void testGetBreed() {
        assertEquals("Labrador", pet.getBreed().getBreedName());
    }

    @Test
    void testGetOwner() {
        assertEquals("John", pet.getOwner().getFirstName());
    }

    @Test
    void testGetPetType() {
        assertEquals(PetType.DOG, pet.getPetType());
    }

    @Test
    void testSetName() {
        pet.setName("Buddy");
        assertEquals("Buddy", pet.getName());
    }

    @Test
    void testSetDateOfBirth() {
        pet.setDateOfBirth(LocalDateTime.of(2021, 1, 1, 0, 0, 0, 0));
        assertEquals(LocalDateTime.of(2021, 1, 1, 0, 0, 0, 0), pet.getDateOfBirth());
    }

    @Test
    void testSetGender() {
        pet.setGender("Female");
        assertEquals("Female", pet.getGender());
    }

    @Test
    void testSetBreed() {
        Breed newBreed = new Breed("Bulldog");
        pet.setBreed(newBreed);
        assertEquals("Bulldog", pet.getBreed().getBreedName());
    }

    @Test
    void testSetOwner() {
        Owner newOwner = new Owner("Alice", "Smith", "67890", "555-6789");
        pet.setOwner(newOwner);
        assertEquals("Alice", pet.getOwner().getFirstName());
    }

    @Test
    void testSetPetType() {
        pet.setPetType(PetType.CAT);
        assertEquals(PetType.CAT, pet.getPetType());
    }
}
