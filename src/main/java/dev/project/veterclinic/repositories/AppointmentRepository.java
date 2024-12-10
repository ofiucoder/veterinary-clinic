package dev.project.veterclinic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.project.veterclinic.models.Appointment;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    // Query method to find appointments by ownerId and order by date
    List<Appointment> findByOwnerIdOrderByDateAsc(int ownerId);
}