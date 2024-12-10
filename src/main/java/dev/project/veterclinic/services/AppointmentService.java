package dev.project.veterclinic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.project.veterclinic.dtos.AppointmentDto;
import dev.project.veterclinic.exceptions.appointment.AppointmentNotFoundException;
import dev.project.veterclinic.models.Appointment;
import dev.project.veterclinic.repositories.AppointmentRepository;

@Service
public class AppointmentService {
    private final AppointmentRepository repository;

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    // Fetch all appointments
    public List<Appointment> findAll() {
        return repository.findAll();
    }

    // Save a new appointment (creating a new one)
    public Appointment save(AppointmentDto appointmentDto) {
        Appointment appointment = new Appointment(
                appointmentDto.date(),
                appointmentDto.petId(),
                appointmentDto.type(),
                appointmentDto.reason(),
                appointmentDto.status(),
                appointmentDto.ownerId());
        return repository.save(appointment);
    }

    // Get appointment by id
    public Appointment getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("There is no appointment with id: " + id));
    }

    // Find appointments by ownerId and order by date
    public List<Appointment> findAppointmentsByOwnerId(int ownerId) {
        return repository.findByOwnerIdOrderByDateAsc(ownerId);
    }

    // Find appointment by ownerId and appointmentId
    public Optional<Appointment> findAppointmentByOwnerIdAndAppId(int ownerId, int appointmentId) {
        return repository.findByOwnerIdAndId(ownerId, appointmentId);
    }

    // Update an existing appointment (modifying an existing one)
    public Appointment updateAppointment(int appointmentId, AppointmentDto appointmentDto) {
        // Fetch the existing appointment by ID
        Appointment existingAppointment = repository.findById(appointmentId)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with id: " + appointmentId));

        // Update the appointment fields
        existingAppointment.setDate(appointmentDto.date());
        existingAppointment.setPetId(appointmentDto.petId());
        existingAppointment.setType(appointmentDto.type());
        existingAppointment.setReason(appointmentDto.reason());
        existingAppointment.setStatus(appointmentDto.status());

        // Save and return the updated appointment
        return repository.save(existingAppointment);
    }
}
