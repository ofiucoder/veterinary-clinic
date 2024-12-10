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

        if (entity.date() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        if (entity.petId() <= 0) {
            return ResponseEntity.badRequest().body(null);
        }

        if (entity.type() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        if (entity.reason() == null || entity.reason().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        if (entity.status() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        if (entity.ownerId() <= 0) { 
            return ResponseEntity.badRequest().body(null);
        }

        // Proceed to save the appointment
        Appointment appointment = service.save(entity);

        if (appointment == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(201).body(appointment);
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
