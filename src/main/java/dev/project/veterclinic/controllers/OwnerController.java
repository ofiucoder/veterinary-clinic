package dev.project.veterclinic.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.project.veterclinic.dtos.AppointmentDto;
import dev.project.veterclinic.dtos.OwnerDto;
import dev.project.veterclinic.dtos.appointmentDtoResponse.AppointDtoResponse;
import dev.project.veterclinic.dtos.petDtoResponse.PetOwnerDtoReponse;
import dev.project.veterclinic.exceptions.appointment.AppointmentConflictException;
import dev.project.veterclinic.services.AppointmentService;
import dev.project.veterclinic.services.OwnerService;

@RestController
@RequestMapping(path = "/api/v1/owners")
public class OwnerController {

    private final OwnerService ownerService;
    private final AppointmentService appointmentService;

    public OwnerController(OwnerService ownerService, AppointmentService appointmentService) {
        this.ownerService = ownerService;
        this.appointmentService = appointmentService;
    }

    @GetMapping("")
    public List<PetOwnerDtoReponse> index() {
        return ownerService.findAll();
    }

    @PostMapping("")
    public ResponseEntity<PetOwnerDtoReponse> store(@RequestBody OwnerDto entity) {
        if (entity.firstName() == "" || entity.firstName() == null)
            return ResponseEntity.badRequest().build();
        if (entity.lastName() == "" || entity.lastName() == null)
            return ResponseEntity.badRequest().build();
        if (entity.dni() == "" || entity.dni() == null)
            return ResponseEntity.badRequest().build();
        if (entity.phoneNumber() == "" || entity.phoneNumber() == null)
            return ResponseEntity.badRequest().build();

        // Save the owner and get the created owner object
        PetOwnerDtoReponse owner = ownerService.save(entity);

        if (owner == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.status(201).body(owner);
    }

    // get by id
    @GetMapping("/{id}")
    public ResponseEntity<PetOwnerDtoReponse> show(@PathVariable int id) {
        PetOwnerDtoReponse owner = ownerService.getById(id);
        return ResponseEntity.ok().body(owner);
    }
    
    // Get all appointments by ownerId, ordered by date
    @GetMapping("/{ownerDni}/appointments")
    public ResponseEntity<List<AppointDtoResponse>> showOwnerAppointments(@PathVariable String ownerDni) {
        return ResponseEntity.ok(appointmentService.findAppointmentsByOwnerId(ownerDni));
    }

    // Create a new appointment
    @PostMapping("/{ownerDni}/appointments")
    public ResponseEntity<AppointDtoResponse> store(@PathVariable String ownerDni, @RequestBody AppointmentDto appointmentDto) {
        // Set the ownerId in the appointmentDto before saving
        appointmentDto = new AppointmentDto(
            appointmentDto.date(),
            appointmentDto.type(),
            appointmentDto.reason(),
            appointmentDto.status(),
            appointmentDto.petId(),
            ownerDni
        ); // Set the ownerId from the path

        // Perform validation checks and return bad request if any check fails
        if (isInvalidAppointment(appointmentDto)) {
            return ResponseEntity.badRequest().body(null);
        }

        // Check for existing appointment with the same owner_id and date
        if (isDuplicateAppointment(appointmentDto)) {
            // Throw a custom exception for duplicate appointment
            throw new AppointmentConflictException("An appointment with the same date and time already exists for owner " + ownerDni);
        }
        
        // Proceed to save the appointment
        AppointDtoResponse appointment = appointmentService.saveAppointmentByOwnerDni(ownerDni, appointmentDto);

        if (appointment == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(201).body(appointment);
    }

    // Update an appointment by ownerId and appointmentId
    @PutMapping("/{ownerDni}/appointments/{appointmentId}")
    public ResponseEntity<AppointDtoResponse> updateAppointment(
            @PathVariable String ownerDni, 
            @PathVariable int appointmentId,
            @RequestBody AppointmentDto appointmentDto) {

        // Validate if the appointment belongs to the ownerId
        AppointDtoResponse updatedAppointment = appointmentService.updateAppointmentByOwnerDniAndAppointmentId(ownerDni, appointmentId, appointmentDto);

        return ResponseEntity.ok(updatedAppointment);
    }

    // Get a specific appointment for a specific owner
    @GetMapping("/{ownerDni}/appointments/{appointmentId}")
    public ResponseEntity<AppointDtoResponse> showOwnerAppointment(@PathVariable String ownerDni, @PathVariable int appointmentId) {
        AppointDtoResponse appointment = appointmentService.findAppointmentByOwnerIdAndAppId(ownerDni, appointmentId);
        return ResponseEntity.ok(appointment);
    }

    @DeleteMapping("/{ownerDni}/appointments/{appointmentId}")
    public ResponseEntity<String> deleteOwnerAppointment(@PathVariable String ownerDni, @PathVariable int appointmentId) {
        appointmentService.deleteAppointmentByOwnerDniAndappointmentId(ownerDni, appointmentId);
        return ResponseEntity.status(200).body("Deleted successfully");
    }

    // Helper method to validate the AppointmentDto
    private boolean isInvalidAppointment(AppointmentDto entity) {
        return entity.date() == null ||
                entity.petId() <= 0 ||
                entity.type() == null ||
                entity.reason() == null || entity.reason().trim().isEmpty() ||
                entity.status() == null;
    }

    // Helper method to check if there is an existing appointment with the same
    // owner_id and date
    private boolean isDuplicateAppointment(AppointmentDto entity) {
        return appointmentService.getAppointmentsByOwnerAndDate(entity.ownerDni(), entity.date()) != null;
    }
}
