package dev.project.veterclinic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.project.veterclinic.models.Pet;

public interface PetRepository extends JpaRepository<Pet, Integer>{
    
}
