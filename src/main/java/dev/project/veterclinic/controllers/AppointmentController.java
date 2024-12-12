package dev.project.veterclinic.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import dev.project.veterclinic.dtos.AppointmentDto;
import dev.project.veterclinic.models.Appointment;
import dev.project.veterclinic.services.AppointmentService;

import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "${api-endpoint}/appointments")
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    // Get all appointments
    @GetMapping("")
    public List<Appointment> index(){
        return service.findAll();
    }

    // Create a new appointment
    @PostMapping("")
    public ResponseEntity<Appointment> store(@Valid @RequestBody AppointmentDto entity) {
        // Perform validation checks and return bad request if any check fails
        if (isInvalidAppointment(entity)) {
            return ResponseEntity.badRequest().body(null);
        }
    
        // Check for existing appointment with the same owner_id and date
        if (isDuplicateAppointment(entity)) {
            return ResponseEntity.badRequest().body(null);
        }
    
        // Proceed to save the appointment
        Appointment appointment = service.save(entity);
    
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
    
    // Helper method to check if there is an existing appointment with the same owner_id and date
    private boolean isDuplicateAppointment(AppointmentDto entity) {
        // Query the service or repository to check if an appointment with the same owner_id and date exists
        return service.getAppointmentsByOwnerAndDate(entity.ownerId(), entity.date()).size() > 0;
    }    

    // Get appointment by id
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> show(@PathVariable int id) {
        Appointment appointment = service.getById(id);
        if (appointment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(appointment);
    }
    
}
