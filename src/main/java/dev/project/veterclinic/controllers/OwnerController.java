package dev.project.veterclinic.controllers;

import java.util.List;
import java.util.Optional;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.project.veterclinic.dtos.AppointmentDto;
import dev.project.veterclinic.dtos.OwnerDto;
import dev.project.veterclinic.exceptions.appointment.AppointmentConflictException;
import dev.project.veterclinic.models.Appointment;
import dev.project.veterclinic.models.Owner;
import dev.project.veterclinic.services.AppointmentService;
import dev.project.veterclinic.services.OwnerService;

@RestController
@RequestMapping(path = "${api-endpoint}/owners")
public class OwnerController {

    private final OwnerService ownerService;
    private final AppointmentService appointmentService;

    public OwnerController(OwnerService ownerService, AppointmentService appointmentService) {
        this.ownerService = ownerService;
        this.appointmentService = appointmentService;
    }

    @GetMapping("")
    public List<Owner> index() {
        return ownerService.findAll();
    }

    @PostMapping("")
    public ResponseEntity<Owner> store(@RequestBody OwnerDto entity) {
        if (entity.firstName() == "" || entity.firstName() == null)
            return ResponseEntity.badRequest().build();
        if (entity.lastName() == "" || entity.lastName() == null)
            return ResponseEntity.badRequest().build();
        if (entity.dni() == "" || entity.dni() == null)
            return ResponseEntity.badRequest().build();
        if (entity.phoneNumber() == "" || entity.phoneNumber() == null)
            return ResponseEntity.badRequest().build();

        // Save the owner and get the created owner object
        Owner owner = ownerService.save(entity);

        if (owner == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.status(201).body(owner);
    }

    // get by id
    @GetMapping("/{id}")
    public ResponseEntity<Owner> show(@PathVariable int id) {
        Owner owner = ownerService.getById(id);
        return ResponseEntity.ok().body(owner);
    }

    // Get all appointments by ownerId, ordered by date
    @GetMapping("/{ownerId}/appointments")
    public ResponseEntity<List<Appointment>> showOwnerAppointments(@PathVariable int ownerId) {
        List<Appointment> appointments = appointmentService.findAppointmentsByOwnerId(ownerId);

        if (appointments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(appointments);
    }

    // Modify the existing store method to create an appointment for the owner
@PostMapping("/{ownerId}/appointments")
public ResponseEntity<Appointment> storeAppointment(@PathVariable int ownerId, @Valid @RequestBody AppointmentDto appointmentDto) {
    // Set the ownerId in the appointmentDto before saving
    appointmentDto = new AppointmentDto(
            appointmentDto.date(),
            appointmentDto.petId(),
            appointmentDto.type(),
            appointmentDto.reason(),
            appointmentDto.status(),
            ownerId); // Set the ownerId from the path

    // Perform validation checks and return bad request if any check fails
    if (isInvalidAppointment(appointmentDto)) {
        return ResponseEntity.badRequest().body(null);
    }

    // Check for existing appointment with the same owner_id and date
    if (isDuplicateAppointment(appointmentDto)) {
        // Throw a custom exception for duplicate appointment
        throw new AppointmentConflictException("An appointment with the same date and time already exists for owner " + ownerId);
    }

    // Proceed to save the appointment
    Appointment appointment = appointmentService.save(appointmentDto);

    // Return no content if the appointment could not be saved
    if (appointment == null) {
        return ResponseEntity.noContent().build();
    }

    // Return the created appointment with status 201
    return ResponseEntity.status(201).body(appointment);
}


    // Helper method to validate the AppointmentDto
    private boolean isInvalidAppointment(AppointmentDto entity) {
        return entity.date() == null ||
                entity.petId() <= 0 ||
                entity.type() == null ||
                entity.reason() == null || entity.reason().trim().isEmpty() ||
                entity.status() == null ||
                entity.ownerId() <= 0;
    }

    // Helper method to check if there is an existing appointment with the same
    // owner_id and date
    private boolean isDuplicateAppointment(AppointmentDto entity) {
        return appointmentService.getAppointmentsByOwnerAndDate(entity.ownerId(), entity.date()).size() > 0;
    }

    // Update an appointment by ownerId and appointmentId
    @PutMapping("/{ownerId}/appointments/{appointmentId}")
    public ResponseEntity<Appointment> updateAppointment(
            @PathVariable int ownerId,
            @PathVariable int appointmentId,
            @RequestBody AppointmentDto appointmentDto) {

        // Validate if the appointment belongs to the ownerId
        Appointment updatedAppointment = appointmentService.updateAppointment(appointmentId, appointmentDto);

        return ResponseEntity.ok(updatedAppointment);
    }

    // Get a specific appointment for a specific owner
    @GetMapping("/{ownerId}/appointments/{appointmentId}")
    public ResponseEntity<Appointment> showOwnerAppointment(@PathVariable int ownerId,
            @PathVariable int appointmentId) {
        Optional<Appointment> appointment = appointmentService.findAppointmentByOwnerIdAndAppId(ownerId, appointmentId);

        if (appointment.isEmpty()) {
            return ResponseEntity.notFound().build(); // Return 404 if the appointment is not found
        }

        return ResponseEntity.ok(appointment.get()); // Return the appointment if found
    }
}
