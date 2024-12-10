package dev.project.veterclinic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.project.veterclinic.models.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

}