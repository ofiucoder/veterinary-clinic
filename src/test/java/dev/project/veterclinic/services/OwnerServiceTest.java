package dev.project.veterclinic.services;

import dev.project.veterclinic.dtos.OwnerDto;
import dev.project.veterclinic.dtos.petDtoResponse.PetOwnerDtoReponse;
import dev.project.veterclinic.models.Owner;
import dev.project.veterclinic.repositories.OwnerRepository;
import dev.project.veterclinic.exceptions.owner.OwnerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)  // Add this to enable Mockito extension
public class OwnerServiceTest {

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private OwnerService ownerService;

    private Owner mockOwner;
    private OwnerDto mockOwnerDto;

    @BeforeEach
    void setUp() {
        mockOwner = new Owner("John", "Doe", "12345678", "987654321");
        mockOwnerDto = new OwnerDto("John", "Doe", "12345678", "987654321");
    }

    @Test
    void testFindAll() {
        // Arrange
        when(ownerRepository.findAll()).thenReturn(List.of(mockOwner));

        // Act
        List<PetOwnerDtoReponse> owners = ownerService.findAll();

        // Assert
        assertNotNull(owners);
        assertEquals(1, owners.size());  // There should be 1 owner
        assertEquals("John", owners.get(0).firstName());  // Check if the first name matches
    }

    @Test
    void testGetById() {
        // Arrange
        int ownerId = 1;
        when(ownerRepository.findById(ownerId)).thenReturn(Optional.of(mockOwner));

        // Act
        PetOwnerDtoReponse owner = ownerService.getById(ownerId);

        // Assert
        assertNotNull(owner);
        assertEquals("John", owner.firstName());  // Check the owner's first name
    }

    @Test
    void testGetByIdThrowsException() {
        // Arrange
        int ownerId = 1;
        when(ownerRepository.findById(ownerId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(OwnerNotFoundException.class, () -> ownerService.getById(ownerId));
    }

    @Test
    void testSave() {
        // Arrange
        when(ownerRepository.save(any(Owner.class))).thenReturn(mockOwner);

        // Act
        PetOwnerDtoReponse savedOwner = ownerService.save(mockOwnerDto);

        // Assert
        assertNotNull(savedOwner);
        assertEquals("John", savedOwner.firstName());  // Ensure the saved owner's first name is "John"
    }
}
