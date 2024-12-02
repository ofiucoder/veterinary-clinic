package dev.project.veterclinic.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import dev.project.veterclinic.models.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {
    
}
