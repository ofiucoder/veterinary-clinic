package dev.project.veterclinic.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.project.veterclinic.models.Pet;

public interface PetRepository extends JpaRepository<Pet, Integer>{
    List<Pet> findAllByDeleted (boolean deleted);
}
