package dev.project.veterclinic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.project.veterclinic.models.Treatment;

public interface TreatmentRepository extends JpaRepository<Treatment, Integer>{
    @Query(value = "SELECT * FROM TREATMENTS WHERE pet_id=:petId", nativeQuery = true)
    List<Treatment> findByPetId(@Param("petId") int petId);
}