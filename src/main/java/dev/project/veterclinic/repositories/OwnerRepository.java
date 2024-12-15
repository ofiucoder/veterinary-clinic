package dev.project.veterclinic.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.project.veterclinic.models.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {
    @Query(value = "SELECT COUNT(*) FROM owners WHERE dni=:ownerDni", nativeQuery = true)
    int findByDni(@Param("ownerDni") String ownerDni);

    default boolean existByDni(String ownerDni) {
        return findByDni(ownerDni) > 0;
    }
}
