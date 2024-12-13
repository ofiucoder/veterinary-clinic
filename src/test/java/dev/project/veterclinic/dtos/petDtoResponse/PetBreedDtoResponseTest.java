package dev.project.veterclinic.dtos.petDtoResponse;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PetBreedDtoResponseTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        int id = 1;
        String breedName = "Labrador";

        // Act
        PetBreedDtoResponse petBreedDtoResponse = new PetBreedDtoResponse(id, breedName);

        // Assert
        assertEquals(id, petBreedDtoResponse.id());
        assertEquals(breedName, petBreedDtoResponse.breedName());
    }

    @Test
    void testEqualsAndHashCode() {
        // Arrange
        int id = 1;
        String breedName = "Labrador";

        // Act
        PetBreedDtoResponse petBreedDtoResponse1 = new PetBreedDtoResponse(id, breedName);
        PetBreedDtoResponse petBreedDtoResponse2 = new PetBreedDtoResponse(id, breedName);

        // Assert
        assertEquals(petBreedDtoResponse1, petBreedDtoResponse2);  // They should be equal since all fields match
        assertEquals(petBreedDtoResponse1.hashCode(), petBreedDtoResponse2.hashCode());  // hashCodes should match for equal objects
    }

    @Test
    void testToString() {
        // Arrange
        int id = 1;
        String breedName = "Labrador";

        // Act
        PetBreedDtoResponse petBreedDtoResponse = new PetBreedDtoResponse(id, breedName);

        // Assert
        String expectedString = "PetBreedDtoResponse[id=1, breedName=Labrador]";
        assertEquals(expectedString, petBreedDtoResponse.toString());
    }
}
