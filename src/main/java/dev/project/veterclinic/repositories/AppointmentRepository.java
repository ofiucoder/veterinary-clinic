package dev.project.veterclinic.repositories;

import dev.project.veterclinic.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query(value = "SELECT * FROM appointments WHERE owner_dni=:ownerDni order by date Asc", nativeQuery = true)
    List<Appointment> findByOwnerDniOrderByDateAsc(String ownerDni);

    // Method to find appointment by ownerId and appointmentId
    Optional<Appointment> findByOwnerIdAndId(int ownerId, int appointmentId);

    @Query(value = "SELECT * FROM appointments WHERE owner_dni=:ownerDni AND ID=:appointmentId  LIMIT 1", nativeQuery = true)
    Appointment findByOwnerDniAndAppointmentId(@Param("ownerDni") String ownerDni, @Param("appointmentId") int appointmentId);    
    
    @Query(value = "SELECT * FROM appointments WHERE owner_dni=:ownerDni AND date=:date", nativeQuery = true)
    Appointment findByOwnerDniAndDate(@Param("ownerDni") String ownerDni, @Param("date") LocalDateTime date);
}
