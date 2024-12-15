package dev.project.veterclinic.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import dev.project.veterclinic.dtos.AppointmentDto;
import dev.project.veterclinic.dtos.appointmentDtoResponse.AppointDtoResponse;
import dev.project.veterclinic.exceptions.appointment.AppointmentConflictException;
import dev.project.veterclinic.services.AppointmentService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/appointments")
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    // Get all appointments
    @GetMapping("")
    public List<AppointDtoResponse> index(){
        return service.findAll();
    }

    // Create a new appointment
    @PostMapping("")
    public ResponseEntity<AppointDtoResponse> store(@RequestBody AppointmentDto entity) {

        if (entity.date() == null) {
            return ResponseEntity.badRequest().body(null);
        }
    
        // Check for existing appointment with the same owner_id and date
        if (isDuplicateAppointment(entity)) {
            throw new AppointmentConflictException("An appointment with the same date and time already exists for owner " + entity.ownerDni());
        }

        if(isInvalidAppointment(entity)){
            return ResponseEntity.badRequest().body(null);
        }

        // Proceed to save the appointment
        AppointDtoResponse appointment = service.save(entity);
    
        // Return the created appointment with status 201
        return ResponseEntity.status(201).body(appointment);
    }    

    // Get appointment by id
    @GetMapping("/{id}")
    public ResponseEntity<AppointDtoResponse> show(@PathVariable int id) {
        AppointDtoResponse appointment = service.getById(id);
        
        return ResponseEntity.ok().body(appointment);
    }

    // Helper method to validate the AppointmentDto
    private boolean isInvalidAppointment(AppointmentDto entity) {
        return entity.date() == null || 
               entity.petId() <= 0 || 
               entity.type() == null || 
               entity.reason() == null || entity.reason().trim().isEmpty() || 
               entity.status() == null || 
               entity.ownerDni() == null;
    }
    
    // Helper method to check if there is an existing appointment with the same owner_id and date
    private boolean isDuplicateAppointment(AppointmentDto entity) {
        // Query the service or repository to check if an appointment with the same owner_id and date exists
        return service.getAppointmentsByOwnerAndDate(entity.ownerDni(), entity.date()) != null;
    }
}
