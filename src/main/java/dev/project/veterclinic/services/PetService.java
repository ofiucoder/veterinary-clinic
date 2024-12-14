package dev.project.veterclinic.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import dev.project.veterclinic.exceptions.owner.OwnerNotFoundException;
import dev.project.veterclinic.dtos.PetDto;
import dev.project.veterclinic.dtos.PetProfileDto;
import dev.project.veterclinic.dtos.TreatmentDto;
import dev.project.veterclinic.dtos.petDtoResponse.PetBreedDtoResponse;
import dev.project.veterclinic.dtos.petDtoResponse.PetDtoResponse;
import dev.project.veterclinic.dtos.petDtoResponse.PetOwnerDtoReponse;
import dev.project.veterclinic.exceptions.pet.PetNotFoundException;
import dev.project.veterclinic.models.Breed;
import dev.project.veterclinic.models.Owner;
import dev.project.veterclinic.models.Pet;
import dev.project.veterclinic.models.Treatment;
import dev.project.veterclinic.repositories.BreedRepository;
import dev.project.veterclinic.repositories.OwnerRepository;
import dev.project.veterclinic.repositories.PetRepository;
import dev.project.veterclinic.repositories.TreatmentRepository;

@Service
public class PetService {
    private PetRepository petRepository;
    private OwnerRepository ownerRepository;
    private BreedRepository breedRepository;
    private TreatmentRepository treatmentRepository;

    public PetService(PetRepository petRepository, OwnerRepository ownerRepository, BreedRepository breedRepository, TreatmentRepository treatmentRepository){
        this.petRepository = petRepository;
        this.ownerRepository =  ownerRepository;
        this.breedRepository = breedRepository;
        this.treatmentRepository = treatmentRepository;
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

    
    public PetProfileDto getPetProfile(int id){
        Pet pet = petRepository.findById(id).orElseThrow(()-> new PetNotFoundException("Pet not found by id"));
        List<TreatmentDto> treatmentDtoList = new ArrayList<>();
        for (Treatment treatment : treatmentRepository.findByPetId(id)) {
            treatmentDtoList.add(new TreatmentDto(
                treatment.getId(),
                treatment.getDate(),
                treatment.getType(),
                treatment.getNote()
            ));
        }

        return new PetProfileDto(
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
                                    ),
                                    treatmentDtoList
                                );
    }

    public void deleletById(int id){
        Pet pet = petRepository.findById(id).orElseThrow(()-> new PetNotFoundException("Pet not found by id"));
        petRepository.delete(pet);
        
    }

    public PetDtoResponse updateById(int id, PetDto updatePet) {
        Pet pet = petRepository.findById(id).orElseThrow(()-> new PetNotFoundException("Pet not found by id"));
        Breed breed;

        if(!breedRepository.existByNameLowerCase(updatePet.breed())){
            breed = new Breed(updatePet.breed());
            breedRepository.save(breed);
        }else{
            breed = breedRepository.findByName(updatePet.breed());
        }
            pet.setName(updatePet.name());
            pet.setDateOfBirth(updatePet.dateOfBirth());
            pet.setGender(updatePet.gender());
            pet.setPetType(updatePet.petType());
            pet.setBreed(breed);

            petRepository.save(pet);
            return new PetDtoResponse(
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
           );

        
    }
        
}
