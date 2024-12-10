package dev.project.veterclinic.repositories;

import dev.project.veterclinic.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    // Method to find appointments by ownerId and order them by date
    List<Appointment> findByOwnerIdOrderByDateAsc(int ownerId);

    // Method to find appointment by ownerId and appointmentId
    Optional<Appointment> findByOwnerIdAndId(int ownerId, int appointmentId);
}
