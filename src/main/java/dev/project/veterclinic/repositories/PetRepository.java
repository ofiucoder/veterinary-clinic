package dev.project.veterclinic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.project.veterclinic.models.Pet;

public interface PetRepository extends JpaRepository<Pet, Integer>{
    @Query(value = 
                """
                    SELECT pets.ID, BREED_ID, OWNER_ID, DATE_OF_BIRTH, NAME, GENDER, PET_TYPE FROM pets 
                    inner join owners on owners.id = pets.owner_id 
                    WHERE owners.dni=:ownerDni 
                    AND pets.id=:petId LIMIT 1
                """
            , nativeQuery = true)
    Pet findByOwnerDniAndPetId(@Param("ownerDni") String ownerDni, @Param("petId") int petId);    
}
