package dev.project.veterclinic.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.project.veterclinic.dtos.PatientDto;
import dev.project.veterclinic.models.Breed;
import dev.project.veterclinic.models.Owner;
import dev.project.veterclinic.models.Pet;
import dev.project.veterclinic.repositories.BreedRepository;
import dev.project.veterclinic.repositories.OwnerRepository;
import dev.project.veterclinic.repositories.PetRepository;

@Service
public class PatientService {

    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;
    private final BreedRepository breedRepository;

    public PatientService(PetRepository petRepository, OwnerRepository ownerRepository, BreedRepository breedRepository) {
        this.petRepository = petRepository;
        this.ownerRepository = ownerRepository;
        this.breedRepository = breedRepository;
    }

    public List<PatientDto> listAll() {
        return petRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public PatientDto getById(int id) {
        return petRepository.findById(id)
                .map(this::mapToDto)
                .orElse(null);
    }

    public PatientDto create(PatientDto patientDto) {
        Pet pet = mapToEntity(patientDto);
        Pet savedPet = petRepository.save(pet);
        return mapToDto(savedPet);
    }

    // public PatientDto update(int id, PatientDto patientDto) {
    //     return petRepository.findById(id)
    //             .map(existingPet -> {
    //                 Pet updatedPet = mapToEntity(patientDto);
    //                 updatedPet.setId(id);
    //                 return mapToDto(petRepository.save(updatedPet));
    //             })
    //             .orElse(null);
    // }

    public boolean delete(int id) {
        if (petRepository.existsById(id)) {
            petRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private PatientDto mapToDto(Pet pet) {
        return new PatientDto(
            pet.getName(),
            pet.getDateOfBirth(),
            pet.getGender(),
            pet.getPetType(),
            pet.getBreed().getBreedName(),
            pet.getOwner().getFirstName(),
            pet.getOwner().getLastName(),
            pet.getOwner().getDni(),
            pet.getOwner().getPhoneNumber()
        );
    }

    private Pet mapToEntity(PatientDto patientDto) {
        Pet pet = new Pet();
        pet.setName(patientDto.name());
        pet.setDateOfBirth(patientDto.dateOfBirth());
        pet.setGender(patientDto.gender());
        pet.setPetType(patientDto.petType());

        Breed breed = breedRepository.findAll().stream()
            .filter(b -> b.getBreedName().equals(patientDto.breedName()))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Breed not found"));
        pet.setBreed(breed);

        Owner owner = ownerRepository.findAll().stream()
            .filter(o -> o.getDni().equals(patientDto.ownerDni()))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Owner not found"));
        pet.setOwner(owner);

        return pet;
    }
}
