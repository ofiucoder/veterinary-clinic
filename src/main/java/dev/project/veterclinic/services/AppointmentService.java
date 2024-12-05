package dev.project.veterclinic.services;
import java.util.List;

import org.springframework.stereotype.Service;
import dev.project.veterclinic.dtos.AppointmentDto;
import dev.project.veterclinic.exceptions.appointment.AppointmentNotFoundException;
import dev.project.veterclinic.models.Appointment;
import dev.project.veterclinic.repositories.AppointmentRepository;

@Service
public class AppointmentService {
    private AppointmentRepository repository;

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    public List<Appointment> findAll(){
        return repository.findAll();
    }

    public Appointment save(AppointmentDto appointmentDto) {
        Appointment appointment = new Appointment(appointmentDto.firstName(),  appointmentDto.lastName(),  appointmentDto.dni(),  appointmentDto.phoneNumber());
        repository.save(appointment);
        return appointment;
    }

    public Appointment getById(int id) {
        Appointment country = repository.findById(id).orElseThrow(()-> new AppointmentNotFoundException("There is no appointment with id: " + id));
        return country;
    }
}

