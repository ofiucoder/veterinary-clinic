package dev.project.veterclinic.repositories;

import dev.project.veterclinic.enums.AppointmentStatus;
import dev.project.veterclinic.enums.AppointmentType;
import dev.project.veterclinic.models.Appointment;
import dev.project.veterclinic.models.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AppointmentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AppointmentRepository appointmentRepository;

    private Owner owner;
    private Appointment appointment;

    @BeforeEach
    void setUp() {
        owner = new Owner();
        owner.setFirstName("John");
        owner.setLastName("Doe");
        owner.setDni("1234w678");
        owner.setPhoneNumber("987654321");
        entityManager.persistAndFlush(owner);

        appointment = new Appointment();
        appointment.setDate(LocalDateTime.now());
        appointment.setType(AppointmentType.ORDINARY);
        appointment.setReason("Check-up");
        appointment.setStatus(AppointmentStatus.PENDING);
        appointment.setOwner(owner);
        entityManager.persistAndFlush(appointment);
    }

    @Test
    void testFindByOwnerDniAndAppointmentId() {
        Appointment foundAppointment = appointmentRepository.findByOwnerDniAndAppointmentId(owner.getDni(), appointment.getId());
        assertNotNull(foundAppointment, "Appointment should be found");
        assertEquals(appointment.getId(), foundAppointment.getId(), "Appointment ID should match");
    }

    @Test
    void testFindByOwnerDniOrderByDateAsc() {
        List<Appointment> appointments = appointmentRepository.findByOwnerDniOrderByDateAsc(owner.getDni());
        assertNotNull(appointments, "Appointments should be found");
        assertFalse(appointments.isEmpty(), "Appointments list should not be empty");
        assertEquals(appointment.getDate(), appointments.get(0).getDate(), "First appointment date should match");
    }

    @Test
    void testFindByOwnerIdAndId() {
        Optional<Appointment> foundAppointment = appointmentRepository.findByOwnerIdAndId(owner.getId(), appointment.getId());
        assertTrue(foundAppointment.isPresent(), "Appointment should be found");
        assertEquals(appointment.getId(), foundAppointment.get().getId(), "Appointment ID should match");
    }
}
