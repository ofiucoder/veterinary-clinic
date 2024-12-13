package dev.project.veterclinic.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.project.veterclinic.models.Breed;

public interface BreedRepository extends JpaRepository<Breed, Integer> {
    
    // Custom native SQL query
    @Query(value = "SELECT COUNT(*) FROM breeds WHERE LOWER(breed_name) = LOWER(:name)", nativeQuery = true)
    int countBreedsByName(@Param("name") String name);

    @Query(value = "SELECT * FROM breeds WHERE LOWER(breed_name) = LOWER(:name) LIMIT 1", nativeQuery = true)
    Breed findByName(@Param("name") String name);

    default boolean existByNameLowerCase(String name) {
        return countBreedsByName(name) > 0;
    }

}
