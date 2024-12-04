package dev.project.veterclinic.services;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.project.veterclinic.dtos.PetDto;
import dev.project.veterclinic.exceptions.owner.OwnerNotFoundException;
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
        
}
