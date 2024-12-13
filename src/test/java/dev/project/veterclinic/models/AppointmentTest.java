package dev.project.veterclinic.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;
import dev.project.veterclinic.enums.*;

public class AppointmentTest {

    private Appointment appointment;
    private Pet pet;
    private Owner owner;
    private Breed breed;
    private PetType petType;

    @BeforeEach
    void setUp() {
        breed = new Breed("Labrador");  // Example breed
        petType = PetType.DOG;  // Example pet type
        owner = new Owner("Carlos", "López", "12345678A", "654321987");

        // Create Pet object using the constructor
        pet = new Pet("Max", LocalDateTime.of(2017, 5, 12, 0, 0), petType, "Male", breed, owner);

        // Create Appointment object using the real Pet and Owner objects
        appointment = new Appointment(LocalDateTime.now(), AppointmentType.ORDINARY, "Check-up", 
                                      AppointmentStatus.PENDING, pet, owner);
    }

    @Test
    void testDate() {
        LocalDateTime now = LocalDateTime.now();
        appointment.setDate(now);
        assertEquals(now, appointment.getDate());
    }

    @Test
    void testOwner() {
        appointment.setOwner(owner);
        assertEquals(owner, appointment.getOwner());
    }

    @Test
    void testPet() {
        appointment.setPet(pet);
        assertEquals(pet, appointment.getPet());
    }

    @Test
    void testReason() {
        appointment.setReason("Check-up");
        assertEquals("Check-up", appointment.getReason());
    }

    @Test
    void testStatus() {
        appointment.setStatus(AppointmentStatus.PENDING);
        assertEquals(AppointmentStatus.PENDING, appointment.getStatus());
    }

    @Test
    void testType() {
        appointment.setType(AppointmentType.ORDINARY);
        assertEquals(AppointmentType.ORDINARY, appointment.getType());
    }

    @Test
    void testSetDate() {
        LocalDateTime date = LocalDateTime.of(2023, 1, 1, 10, 0);
        appointment.setDate(date);
        assertEquals(date, appointment.getDate());
    }
}
