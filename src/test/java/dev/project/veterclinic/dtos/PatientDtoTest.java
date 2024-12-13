package dev.project.veterclinic.dtos;

import dev.project.veterclinic.enums.PetType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class PatientDtoTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        String name = "Buddy";
        LocalDateTime dateOfBirth = LocalDateTime.of(2020, 5, 15, 10, 30);
        String gender = "Male";
        PetType petType = PetType.DOG;
        String breedName = "Labrador";
        String ownerFirstName = "John";
        String ownerLastName = "Doe";
        String ownerDni = "12345678A";
        String ownerPhoneNumber = "987654321";

        // Act
        PatientDto patientDto = new PatientDto(name, dateOfBirth, gender, petType, breedName, ownerFirstName, ownerLastName, ownerDni, ownerPhoneNumber);

        // Assert
        assertEquals(name, patientDto.name());
        assertEquals(dateOfBirth, patientDto.dateOfBirth());
        assertEquals(gender, patientDto.gender());
        assertEquals(petType, patientDto.petType());
        assertEquals(breedName, patientDto.breedName());
        assertEquals(ownerFirstName, patientDto.ownerFirstName());
        assertEquals(ownerLastName, patientDto.ownerLastName());
        assertEquals(ownerDni, patientDto.ownerDni());
        assertEquals(ownerPhoneNumber, patientDto.ownerPhoneNumber());
    }

    @Test
    void testEqualsAndHashCode() {
        // Arrange
        String name = "Buddy";
        LocalDateTime dateOfBirth = LocalDateTime.of(2020, 5, 15, 10, 30);
        String gender = "Male";
        PetType petType = PetType.DOG;
        String breedName = "Labrador";
        String ownerFirstName = "John";
        String ownerLastName = "Doe";
        String ownerDni = "12345678A";
        String ownerPhoneNumber = "987654321";

        // Act
        PatientDto patientDto1 = new PatientDto(name, dateOfBirth, gender, petType, breedName, ownerFirstName, ownerLastName, ownerDni, ownerPhoneNumber);
        PatientDto patientDto2 = new PatientDto(name, dateOfBirth, gender, petType, breedName, ownerFirstName, ownerLastName, ownerDni, ownerPhoneNumber);

        // Assert
        assertEquals(patientDto1, patientDto2);  // They should be equal since all fields match
        assertEquals(patientDto1.hashCode(), patientDto2.hashCode());  // hashCodes should match for equal objects
    }

    @Test
    void testToString() {
        // Arrange
        String name = "Buddy";
        LocalDateTime dateOfBirth = LocalDateTime.of(2020, 5, 15, 10, 30);
        String gender = "Male";
        PetType petType = PetType.DOG;
        String breedName = "Labrador";
        String ownerFirstName = "John";
        String ownerLastName = "Doe";
        String ownerDni = "12345678A";
        String ownerPhoneNumber = "987654321";

        // Act
        PatientDto patientDto = new PatientDto(name, dateOfBirth, gender, petType, breedName, ownerFirstName, ownerLastName, ownerDni, ownerPhoneNumber);

        // Assert
        String expectedString = "PatientDto[name=Buddy, dateOfBirth=2020-05-15T10:30, gender=Male, petType=DOG, breedName=Labrador, ownerFirstName=John, ownerLastName=Doe, ownerDni=12345678A, ownerPhoneNumber=987654321]";
        assertEquals(expectedString, patientDto.toString());
    }
}
