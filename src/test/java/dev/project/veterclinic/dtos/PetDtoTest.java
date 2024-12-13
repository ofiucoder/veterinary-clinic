package dev.project.veterclinic.dtos;

import dev.project.veterclinic.enums.PetType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class PetDtoTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        String name = "Bella";
        LocalDateTime dateOfBirth = LocalDateTime.of(2020, 3, 5, 10, 0);
        String gender = "Female";
        PetType petType = PetType.DOG;
        String breed = "Labrador";
        int ownerId = 1;

        // Act
        PetDto petDto = new PetDto(name, dateOfBirth, gender, petType, breed, ownerId);

        // Assert
        assertEquals(name, petDto.name());
        assertEquals(dateOfBirth, petDto.dateOfBirth());
        assertEquals(gender, petDto.gender());
        assertEquals(petType, petDto.petType());
        assertEquals(breed, petDto.breed());
        assertEquals(ownerId, petDto.ownerId());
    }

    @Test
    void testEqualsAndHashCode() {
        // Arrange
        String name = "Bella";
        LocalDateTime dateOfBirth = LocalDateTime.of(2020, 3, 5, 10, 0);
        String gender = "Female";
        PetType petType = PetType.DOG;
        String breed = "Labrador";
        int ownerId = 1;

        // Act
        PetDto petDto1 = new PetDto(name, dateOfBirth, gender, petType, breed, ownerId);
        PetDto petDto2 = new PetDto(name, dateOfBirth, gender, petType, breed, ownerId);

        // Assert
        assertEquals(petDto1, petDto2);  // They should be equal since all fields match
        assertEquals(petDto1.hashCode(), petDto2.hashCode());  // hashCodes should match for equal objects
    }

    @Test
    void testToString() {
        // Arrange
        String name = "Bella";
        LocalDateTime dateOfBirth = LocalDateTime.of(2020, 3, 5, 10, 0);
        String gender = "Female";
        PetType petType = PetType.DOG;
        String breed = "Labrador";
        int ownerId = 1;

        // Act
        PetDto petDto = new PetDto(name, dateOfBirth, gender, petType, breed, ownerId);

        // Assert
        String expectedString = "PetDto[name=Bella, dateOfBirth=2020-03-05T10:00, gender=Female, petType=DOG, breed=Labrador, ownerId=1]";
        assertEquals(expectedString, petDto.toString());
    }
}
