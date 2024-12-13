package dev.project.veterclinic.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OwnerDtoTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        String firstName = "John";
        String lastName = "Doe";
        String dni = "12345678A";
        String phoneNumber = "987654321";

        // Act
        OwnerDto ownerDto = new OwnerDto(firstName, lastName, dni, phoneNumber);

        // Assert
        assertEquals(firstName, ownerDto.firstName());
        assertEquals(lastName, ownerDto.lastName());
        assertEquals(dni, ownerDto.dni());
        assertEquals(phoneNumber, ownerDto.phoneNumber());
    }

    @Test
    void testEqualsAndHashCode() {
        // Arrange
        String firstName = "John";
        String lastName = "Doe";
        String dni = "12345678A";
        String phoneNumber = "987654321";

        // Act
        OwnerDto ownerDto1 = new OwnerDto(firstName, lastName, dni, phoneNumber);
        OwnerDto ownerDto2 = new OwnerDto(firstName, lastName, dni, phoneNumber);

        // Assert
        assertEquals(ownerDto1, ownerDto2);  // They should be equal since all fields match
        assertEquals(ownerDto1.hashCode(), ownerDto2.hashCode());  // hashCodes should match for equal objects
    }

    @Test
    void testToString() {
        // Arrange
        String firstName = "John";
        String lastName = "Doe";
        String dni = "12345678A";
        String phoneNumber = "987654321";

        // Act
        OwnerDto ownerDto = new OwnerDto(firstName, lastName, dni, phoneNumber);

        // Assert
        String expectedString = "OwnerDto[firstName=John, lastName=Doe, dni=12345678A, phoneNumber=987654321]";
        assertEquals(expectedString, ownerDto.toString());
    }
}
