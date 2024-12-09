package dev.project.veterclinic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.project.veterclinic.dtos.PetDto;
import dev.project.veterclinic.exceptions.pet.PetNotFoundException;
import dev.project.veterclinic.models.Pet;
import dev.project.veterclinic.repositories.PetRepository;

@Service
public class PetService {
    private PetRepository repository;

    public PetService(PetRepository repository){
        this.repository = repository;
    }

    public List<Pet> findAll(){
        return repository.findAllByDeleted (false);
    }

    public Pet save(PetDto petDto){
        Pet pet = new Pet(petDto.name(), petDto.dateOfBirth(), petDto.bread_id(), petDto.gender(), petDto.owner_id() );
        repository.save(pet);
        return pet;

    }

    public Pet getById(int id){
        Pet pet = repository.findById(id).orElseThrow(()-> new PetNotFoundException("Pet not found by id"));
        return pet;
    }

    public void deleletById(int id){
        Pet pet = repository.findById(id).orElseThrow(()-> new PetNotFoundException("Pet not found by id"));
        pet.setDeleted(true);
        repository.save(pet);
    }

    public Pet updateById (int id, Pet updatePet) {
        Optional<Pet> existingPet= repository.findById(id); // Optional maneja valores que pueden o no estar presentes, icluye el metodo isPresent
        if (existingPet.isPresent()) {
            Pet pet = existingPet.get();
            pet.setName(updatePet.getName());
            pet.setDateOfBirth(updatePet.getDateOfBirth());
            pet.setBread_id(updatePet.getBread_id());
            pet.setGender(updatePet.getGender());
            pet.setOwner_id(updatePet.getOwnerId());

            return repository.save(pet);

        }

        else {
            throw new RuntimeException ("Pet not found with ID" + id);
        }
    }
        
}
