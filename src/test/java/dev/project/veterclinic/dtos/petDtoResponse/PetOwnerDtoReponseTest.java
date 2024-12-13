package dev.project.veterclinic.dtos.petDtoResponse;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PetOwnerDtoReponseTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        int id = 1;
        String firstName = "John";
        String lastName = "Doe";
        String dni = "12345678A";
        String phoneNumber = "987654321";

        // Act
        PetOwnerDtoReponse petOwnerDtoReponse = new PetOwnerDtoReponse(id, firstName, lastName, dni, phoneNumber);

        // Assert
        assertEquals(id, petOwnerDtoReponse.id());
        assertEquals(firstName, petOwnerDtoReponse.firstName());
        assertEquals(lastName, petOwnerDtoReponse.lastName());
        assertEquals(dni, petOwnerDtoReponse.dni());
        assertEquals(phoneNumber, petOwnerDtoReponse.phoneNumber());
    }

    @Test
    void testEqualsAndHashCode() {
        // Arrange
        int id = 1;
        String firstName = "John";
        String lastName = "Doe";
        String dni = "12345678A";
        String phoneNumber = "987654321";

        // Act
        PetOwnerDtoReponse petOwnerDtoReponse1 = new PetOwnerDtoReponse(id, firstName, lastName, dni, phoneNumber);
        PetOwnerDtoReponse petOwnerDtoReponse2 = new PetOwnerDtoReponse(id, firstName, lastName, dni, phoneNumber);

        // Assert
        assertEquals(petOwnerDtoReponse1, petOwnerDtoReponse2);  // They should be equal since all fields match
        assertEquals(petOwnerDtoReponse1.hashCode(), petOwnerDtoReponse2.hashCode());  // hashCodes should match for equal objects
    }

    @Test
    void testToString() {
        // Arrange
        int id = 1;
        String firstName = "John";
        String lastName = "Doe";
        String dni = "12345678A";
        String phoneNumber = "987654321";

        // Act
        PetOwnerDtoReponse petOwnerDtoReponse = new PetOwnerDtoReponse(id, firstName, lastName, dni, phoneNumber);

        // Assert
        String expectedString = "PetOwnerDtoReponse[id=1, firstName=John, lastName=Doe, dni=12345678A, phoneNumber=987654321]";
        assertEquals(expectedString, petOwnerDtoReponse.toString());
    }
}
