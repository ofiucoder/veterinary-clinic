package dev.project.veterclinic.services;

import dev.project.veterclinic.models.Appointment;
import dev.project.veterclinic.models.Breed;
import dev.project.veterclinic.models.Pet;
import dev.project.veterclinic.repositories.AppointmentRepository;
import dev.project.veterclinic.repositories.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.Optional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import dev.project.veterclinic.models.Owner;
import dev.project.veterclinic.dtos.AppointmentDto;
import dev.project.veterclinic.dtos.appointmentDtoResponse.AppointDtoResponse;
import dev.project.veterclinic.enums.AppointmentStatus;
import dev.project.veterclinic.enums.AppointmentType;
import dev.project.veterclinic.enums.PetType;
import dev.project.veterclinic.exceptions.appointment.AppointmentNotFoundException;

import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith(MockitoExtension.class)  // Ensures proper initialization of mocks
public class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    private String ownerDni;
    private int appointmentId;
    private Appointment mockAppointment;
    private AppointmentDto appointmentDto;
    private List<Appointment> appointments;

    private Pet mockPet;
    private Owner mockOwner;
    private Breed mockBreed;

    @BeforeEach
    void setUp() {
        mockOwner = new Owner(1, "John", "Doe", "1234W678", "987654321");
        mockBreed = new Breed(1, "Maine Coon");
        mockPet = new Pet("Max", LocalDateTime.now(), PetType.DOG, "Male", mockBreed, mockOwner);  // Fixed constructor
        mockAppointment = new Appointment(LocalDateTime.now(), AppointmentType.ORDINARY, "Checkup", AppointmentStatus.PENDING, mockPet, mockOwner);
        appointments = List.of(mockAppointment);
        appointmentDto = new AppointmentDto(LocalDateTime.now(), AppointmentType.URGENT, "check-up", AppointmentStatus.PASSED, 4, "12345678");
    }

    @Test
    void testDeleteAppointmentByOwnerDniAndappointmentId() {
        
        when(appointmentRepository.findByOwnerDniAndAppointmentId(ownerDni, appointmentId)).thenReturn(mockAppointment);

        appointmentService.deleteAppointmentByOwnerDniAndappointmentId(ownerDni, appointmentId);

        verify(appointmentRepository, times(1)).delete(mockAppointment);
    }

    @Test
    public void testFindAll() {
        // Mock the behavior of appointmentRepository.findAll()
        
        when(appointmentRepository.findAll()).thenReturn(appointments);

        // Call the service method
        List<AppointDtoResponse> actualAppointments = appointmentService.findAll();

        // Verify the results
        assertEquals(1, actualAppointments.size());
        verify(appointmentRepository, times(1)).findAll();
    }

    @Test
    public void testSave_NewAppointment() {
    // Mock pet retrieval and appointment saving
    when(petRepository.findByOwnerDniAndPetId(appointmentDto.ownerDni(), appointmentDto.petId())).thenReturn(mockPet);
    when(appointmentRepository.save(any(Appointment.class))).thenReturn(new Appointment());

    // Call the service method
    appointmentService.save(appointmentDto);

    // Verify interactions
    verify(petRepository, times(1)).findByOwnerDniAndPetId(appointmentDto.ownerDni(), appointmentDto.petId());
    verify(appointmentRepository, times(1)).save(any(Appointment.class));
    }

    @Test
    public void testSaveAppointmentByOwnerDni_NewAppointment() {
        // Mock pet retrieval and appointment saving
        when(petRepository.findByOwnerDniAndPetId(ownerDni, appointmentDto.petId())).thenReturn(mockPet);
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(new Appointment());

        // Call the service method
        appointmentService.saveAppointmentByOwnerDni(ownerDni, appointmentDto);

        // Verify interactions
        verify(petRepository, times(1)).findByOwnerDniAndPetId(ownerDni, appointmentDto.petId());
        verify(appointmentRepository, times(1)).save(any(Appointment.class));
    }

    @Test
    public void testGetById_ExistingAppointment() {
    // Mock appointment retrieval
    when(appointmentRepository.findById(1)).thenReturn(Optional.of(mockAppointment));

    // Call the service method
    AppointDtoResponse actualAppointment = appointmentService.getById(1);

    // Verify interactions and response object
    verify(appointmentRepository, times(1)).findById(1);
    assertNotNull(actualAppointment);
    }

    @Test
    public void testGetById_AppointmentNotFound() {
    // Mock appointment retrieval (not found)
    when(appointmentRepository.findById(1)).thenReturn(Optional.empty());

    // Expect exception
    assertThrows(AppointmentNotFoundException.class, () -> appointmentService.getById(1));

    // Verify interaction
    verify(appointmentRepository, times(1)).findById(1);
    }
}
