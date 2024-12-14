package dev.project.veterclinic.services;

import dev.project.veterclinic.dtos.PetDto;
import dev.project.veterclinic.dtos.PetProfileDto;
import dev.project.veterclinic.dtos.petDtoResponse.PetDtoResponse;
import dev.project.veterclinic.models.Breed;
import dev.project.veterclinic.models.Owner;
import dev.project.veterclinic.models.Pet;
import dev.project.veterclinic.models.Treatment;
import dev.project.veterclinic.models.Treatment.TreatmentType;
import dev.project.veterclinic.repositories.BreedRepository;
import dev.project.veterclinic.repositories.OwnerRepository;
import dev.project.veterclinic.repositories.PetRepository;
import dev.project.veterclinic.repositories.TreatmentRepository;
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

import static org.mockito.ArgumentMatchers.any;
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

    @Mock
    private TreatmentRepository treatmentRepository;
    
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
void testGetPetProfile() {
    // Arrange
    int petId = 1;
    Breed mockBreed = new Breed("Labrador");
    mockBreed.setId(101);

    Pet mockPet = new Pet(
        petId,
        "Buddy",
        LocalDateTime.of(2015, 5, 20, 0, 0),
        PetType.DOG,
        "Male",
        mockBreed,
        mockOwner
    );


    List<Treatment> mockTreatments = List.of(
        new Treatment(1, LocalDateTime.of(2023, 6, 10, 10, 0), TreatmentType.MEDICATION, "Checkup"),
        new Treatment(2, LocalDateTime.of(2023, 7, 15, 15, 30), TreatmentType.MEDICATION, "Checkup")
    );

    when(petRepository.findById(petId)).thenReturn(Optional.of(mockPet));
    when(treatmentRepository.findByPetId(petId)).thenReturn(mockTreatments);

    // Act
    PetProfileDto response = petService.getPetProfile(petId);

    // Assert
    assertNotNull(response);
    assertEquals(petId, response.id());
    assertEquals("Buddy", response.name());
    assertEquals(LocalDateTime.of(2015, 5, 20, 0, 0), response.dateOfBirth());
    assertEquals("Male", response.gender());
    assertEquals(PetType.DOG, response.petType());
    assertEquals(mockBreed.getId(), response.breed().id());
    assertEquals("Labrador", response.breed().breedName());
    assertEquals(mockOwner.getId(), response.owner().id());
    assertEquals("John", response.owner().firstName()); // Example mockOwner firstName
    assertEquals(2, response.treatments().size());
    assertEquals(TreatmentType.MEDICATION, response.treatments().get(0).type());
    assertEquals("Checkup", response.treatments().get(1).note());

    // Verify interactions
    verify(petRepository).findById(petId);
    verify(treatmentRepository).findByPetId(petId);
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
    int petId = 1;
    String updatedBreedName = "Labrador";
    Breed updatedBreed = new Breed(updatedBreedName);
    updatedBreed.setId(100);

    PetDto updatePetDto = new PetDto(
        "Buddy Updated",
        LocalDateTime.of(2015, 5, 20, 0, 0),
        "Male",
        PetType.DOG,
        updatedBreedName,
        mockOwner.getId()
    );

    Pet existingPet = new Pet(
        petId,
        "Buddy",
        LocalDateTime.of(2012, 3, 15, 0, 0),
        PetType.CAT,
        "Female",
        mockBreed, // Initial breed
        mockOwner
    );

    when(petRepository.findById(petId)).thenReturn(Optional.of(existingPet));
    when(breedRepository.existByNameLowerCase(updatedBreedName)).thenReturn(false); // Simulate new breed
    when(breedRepository.save(any(Breed.class))).thenReturn(updatedBreed);
    when(petRepository.save(any(Pet.class))).thenAnswer(invocation -> invocation.getArgument(0)); // Return updated pet

    // Act
    PetDtoResponse response = petService.updateById(petId, updatePetDto);

    // Assert
    assertNotNull(response);
    assertEquals("Buddy Updated", response.name());
    assertEquals(LocalDateTime.of(2015, 5, 20, 0, 0), response.dateOfBirth());
    assertEquals("Male", response.gender());
    assertEquals(PetType.DOG, response.petType());
    assertEquals(updatedBreedName, response.breed().breedName());
    assertEquals(mockOwner.getId(), response.owner().id());

    // Verify interactions
    verify(petRepository).findById(petId);
    verify(breedRepository).existByNameLowerCase(updatedBreedName);
    verify(breedRepository).save(any(Breed.class));
    verify(petRepository).save(any(Pet.class));
}

}
