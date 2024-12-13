package dev.project.veterclinic.controllers;

import dev.project.veterclinic.dtos.PetDto;
import dev.project.veterclinic.dtos.petDtoResponse.PetDtoResponse;
import dev.project.veterclinic.services.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)  // Enable Mockito extension for JUnit 5
public class PetControllerTest {

    @Mock
    private PetService petService;

    @InjectMocks
    private PetController petController;  // The controller under test

    private PetDto mockPetDto;
    private PetDtoResponse mockPetDtoResponse;

    @BeforeEach
    void setUp() {
        // Initialize mock data
        mockPetDto = new PetDto("Max", null, "Male", null, "Labrador", 1);  // Sample PetDto
        mockPetDtoResponse = new PetDtoResponse(1, "Max", null, "Male", null, null, null);  // Sample PetDtoResponse
    }

    @Test
    void testIndex() {
        // Arrange
        when(petService.findAll()).thenReturn(List.of(mockPetDtoResponse));

        // Act
        var response = petController.index();

        // Assert
        assertNotNull(response);  // Ensure the response is not null
        assertEquals(1, response.size());  // Assert that there is one pet
    }

    @Test
    void testStore() {
        // Arrange
        when(petService.save(mockPetDto)).thenReturn(mockPetDtoResponse);

        // Act
        ResponseEntity<PetDtoResponse> response = petController.store(mockPetDto);

        // Assert
        assertNotNull(response.getBody(), "Response body should not be null");  // Ensure the response body is not null
        assertEquals(201, response.getStatusCode().value());  // Assert the status code is 201 (Created)

        // Check the body for the expected values, but ensure it is not null first
        PetDtoResponse body = response.getBody();
        assertNotNull(body, "The response body is null.");
        assertEquals("Max", body.name(), "The pet name should be 'Max'");
    }
}
