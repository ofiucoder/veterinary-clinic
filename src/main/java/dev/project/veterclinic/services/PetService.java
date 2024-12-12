package dev.project.veterclinic.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import dev.project.veterclinic.exceptions.owner.OwnerNotFoundException;
import dev.project.veterclinic.dtos.PetDto;
import dev.project.veterclinic.dtos.petDtoResponse.PetBreedDtoResponse;
import dev.project.veterclinic.dtos.petDtoResponse.PetDtoResponse;
import dev.project.veterclinic.dtos.petDtoResponse.PetOwnerDtoReponse;
import dev.project.veterclinic.exceptions.pet.PetNotFoundException;
import dev.project.veterclinic.models.Breed;
import dev.project.veterclinic.models.Owner;
import dev.project.veterclinic.models.Pet;
import dev.project.veterclinic.repositories.BreedRepository;
import dev.project.veterclinic.repositories.OwnerRepository;
import dev.project.veterclinic.repositories.PetRepository;

@Service
public class PetService {
    private PetRepository petRepository;
    private OwnerRepository ownerRepository;
    private BreedRepository breedRepository;

    public PetService(PetRepository petRepository, OwnerRepository ownerRepository, BreedRepository breedRepository){
        this.petRepository = petRepository;
        this.ownerRepository =  ownerRepository;
        this.breedRepository = breedRepository;
    }

    public List<PetDtoResponse> findAll(){
        List<Pet> petList = petRepository.findAll();
        List<PetDtoResponse> petDtoList = new ArrayList<>();
        for (Pet pet : petList) {
            petDtoList.add(new PetDtoResponse(
                 pet.getId(),
                 pet.getName(),
                 pet.getDateOfBirth(),
                 pet.getGender(),
                 pet.getPetType(),
                 new  PetBreedDtoResponse(
                    pet.getBreed().getId(),
                    pet.getBreed().getBreedName()
                 ),
                 new  PetOwnerDtoReponse(
                    pet.getOwner().getId(),
                    pet.getOwner().getFirstName(),
                    pet.getOwner().getLastName(),
                    pet.getOwner().getDni(),
                    pet.getOwner().getPhoneNumber()
                 )
            ));
        }
        return petDtoList;
    }

    public PetDtoResponse save(PetDto petDto){
        Owner owner = ownerRepository.findById(petDto.ownerId()).orElseThrow(()-> new OwnerNotFoundException("Owner not found by id"));
        Breed breed;

        if(!breedRepository.existByNameLowerCase(petDto.breed())){
            breed = new Breed(petDto.breed());
            breedRepository.save(breed);
        }else{
            breed = breedRepository.findByName(petDto.breed());
        }
        
        Pet pet = new Pet(
                            petDto.name(),
                            petDto.dateOfBirth(),
                            petDto.petType(),
                            petDto.gender(), 
                            breed,
                            owner
                          );
        petRepository.save(pet);

        return new PetDtoResponse(
                 pet.getId(),
                 pet.getName(),
                 pet.getDateOfBirth(),
                 pet.getGender(),
                 pet.getPetType(),
                 new  PetBreedDtoResponse(
                    breed.getId(),
                    breed.getBreedName()
                 ),
                 new  PetOwnerDtoReponse(
                    owner.getId(),
                    owner.getFirstName(),
                    owner.getLastName(),
                    owner.getDni(),
                    owner.getPhoneNumber()
                 )
        );

    }

    public Pet getById(int id){
        Pet pet = petRepository.findById(id).orElseThrow(()-> new PetNotFoundException("Pet not found by id"));
        return pet;
    }

    public void deleletById(int id){
        Pet pet = petRepository.findById(id).orElseThrow(()-> new PetNotFoundException("Pet not found by id"));
        petRepository.delete(pet);
        
    }

    public Pet updateById (int id, Pet updatePet) {
        Optional<Pet> existingPet= petRepository.findById(id); // Optional maneja valores que pueden o no estar presentes, icluye el metodo isPresent
        if (existingPet.isPresent()) {
            Pet pet = existingPet.get();
            pet.setName(updatePet.getName());
            pet.setDateOfBirth(updatePet.getDateOfBirth());
//            pet.setBread_id(updatePet.getBread_id());
            pet.setGender(updatePet.getGender());
//            pet.setOwner_id(updatePet.getOwnerId());

            return petRepository.save(pet);

        }

        else {
            throw new RuntimeException ("Pet not found with ID" + id);
        }
    }
        
}
