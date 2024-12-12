package dev.project.veterclinic.controllers;

import java.util.List;
import java.util.Optional;

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
import dev.project.veterclinic.dtos.appointmentDtoResponse.AppointDtoRsponse;
import dev.project.veterclinic.models.Appointment;
import dev.project.veterclinic.models.Owner;
import dev.project.veterclinic.services.AppointmentService;
import dev.project.veterclinic.services.OwnerService;
import jakarta.validation.Valid;

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
    @GetMapping("/{ownerDni}/appointments")
    public ResponseEntity<List<AppointDtoRsponse>> showOwnerAppointments(@PathVariable int ownerDni) {
        return ResponseEntity.ok(appointmentService.findAppointmentsByOwnerId(ownerDni));
    }

    // Create a new appointment
    @PostMapping("/{ownerDni}/appointments")
    public ResponseEntity<AppointDtoRsponse> store(@PathVariable int ownerDni, @RequestBody AppointmentDto appointmentDto) {

        if (appointmentDto.date() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        if (appointmentDto.petId() <= 0) {
            return ResponseEntity.badRequest().body(null);
        }

        if (appointmentDto.type() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        if (appointmentDto.reason() == null || appointmentDto.reason().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        if (appointmentDto.status() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        
        // Proceed to save the appointment
        AppointDtoRsponse appointment = appointmentService.saveAppointmentByOwnerDni(ownerDni, appointmentDto);

        if (appointment == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(201).body(appointment);
    }
    
    // Update an appointment by ownerId and appointmentId
    @PutMapping("/{ownerDni}/appointments/{appointmentId}")
    public ResponseEntity<AppointDtoRsponse> updateAppointment(
            @PathVariable int ownerDni, 
            @PathVariable int appointmentId,
            @RequestBody AppointmentDto appointmentDto) {

        // Validate if the appointment belongs to the ownerId
        AppointDtoRsponse updatedAppointment = appointmentService.updateAppointmentByOwnerDniAndAppointmentId(ownerDni, appointmentId, appointmentDto);

        return ResponseEntity.ok(updatedAppointment);
    }

    // Get a specific appointment for a specific owner
    @GetMapping("/{ownerDni}/appointments/{appointmentId}")
    public ResponseEntity<AppointDtoRsponse> showOwnerAppointment(@PathVariable int ownerDni, @PathVariable int appointmentId) {
        AppointDtoRsponse appointment = appointmentService.findAppointmentByOwnerIdAndAppId(ownerDni, appointmentId);
        return ResponseEntity.ok(appointment);
    }

    @DeleteMapping("/{ownerDni}/appointments/{appointmentId}")
    public ResponseEntity<String> deleteOwnerAppointment(@PathVariable int ownerDni, @PathVariable int appointmentId) {
        appointmentService.deleteAppointmentByOwnerDniAndappointmentId(ownerDni, appointmentId);
        return ResponseEntity.status(200).body("Deleted successfully");
    }
}
