package dev.project.veterclinic.services;

import dev.project.veterclinic.dtos.PetDto;
import dev.project.veterclinic.dtos.petDtoResponse.PetDtoResponse;
import dev.project.veterclinic.models.Breed;
import dev.project.veterclinic.models.Owner;
import dev.project.veterclinic.models.Pet;
import dev.project.veterclinic.repositories.BreedRepository;
import dev.project.veterclinic.repositories.OwnerRepository;
import dev.project.veterclinic.repositories.PetRepository;
import dev.project.veterclinic.enums.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)  // To enable Mockito annotations
public class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private BreedRepository breedRepository;

    @InjectMocks
    private PetService petService;

    private Pet mockPet;
    private PetDto mockPetDto;
    private Owner mockOwner;
    private Breed mockBreed;

    @BeforeEach
    void setUp() {
        mockOwner = new Owner("John", "Doe", "12345678", "987654321");
        mockBreed = new Breed("Labrador");
        mockPet = new Pet("Max", LocalDateTime.now(), PetType.DOG, "Male", mockBreed, mockOwner);  // Fixed constructor
        mockPetDto = new PetDto("Max", LocalDateTime.now(), "Male", PetType.DOG, "Labrador", 12345);  // Fixed constructor
    }

    @Test
    void testSave() {
        // Arrange
        when(ownerRepository.findById(mockPetDto.ownerId())).thenReturn(Optional.of(mockOwner));
        when(breedRepository.existByNameLowerCase(mockPetDto.breed())).thenReturn(true);
        when(breedRepository.findByName(mockPetDto.breed())).thenReturn(mockBreed);
        when(petRepository.save(any(Pet.class))).thenReturn(mockPet);

        // Act
        PetDtoResponse response = petService.save(mockPetDto);

        // Assert
        assertNotNull(response);
        assertEquals(mockPet.getName(), response.name());
        assertEquals(mockPet.getPetType(), response.petType());
        verify(petRepository, times(1)).save(any(Pet.class));  // Verifying that save was called
    }

    @Test
    void testFindAll() {
        // Arrange
        when(petRepository.findAll()).thenReturn(List.of(mockPet));

        // Act
        var response = petService.findAll();

        // Assert
        assertNotNull(response);
        assertEquals(1, response.size());
    }

    @Test
    void testGetById() {
        // Arrange
        when(petRepository.findById(mockPet.getId())).thenReturn(Optional.of(mockPet));

        // Act
        Pet response = petService.getById(mockPet.getId());

        // Assert
        assertNotNull(response);
        assertEquals(mockPet.getId(), response.getId());
    }

    @Test
    void testDeleteById() {
        // Arrange
        when(petRepository.findById(mockPet.getId())).thenReturn(Optional.of(mockPet));
        doNothing().when(petRepository).delete(mockPet);

        // Act
        petService.deleletById(mockPet.getId());

        // Assert
        verify(petRepository, times(1)).delete(mockPet);
    }

    @Test
    void testUpdateById() {
        // Arrange
        Pet updatedPet = new Pet("Max Updated", LocalDateTime.now(), PetType.DOG, "Male", mockBreed, mockOwner);
        when(petRepository.findById(mockPet.getId())).thenReturn(Optional.of(mockPet));
        when(petRepository.save(any(Pet.class))).thenReturn(updatedPet);

        // Act
        Pet response = petService.updateById(mockPet.getId(), updatedPet);

        // Assert
        assertNotNull(response);
        assertEquals(updatedPet.getName(), response.getName());
    }
}
