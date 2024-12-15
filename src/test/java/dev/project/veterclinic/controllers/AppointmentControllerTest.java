package dev.project.veterclinic.controllers;

import dev.project.veterclinic.dtos.AppointmentDto;
import dev.project.veterclinic.dtos.appointmentDtoResponse.AppointDtoResponse;
import dev.project.veterclinic.dtos.petDtoResponse.PetDtoResponse;
import dev.project.veterclinic.enums.AppointmentStatus;
import dev.project.veterclinic.enums.AppointmentType;
import dev.project.veterclinic.enums.PetType;
import dev.project.veterclinic.models.Appointment;
import dev.project.veterclinic.services.AppointmentService;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)  // To enable Mockito annotations
public class AppointmentControllerTest {

    @Mock
    private AppointmentService appointmentService;

    @InjectMocks
    private AppointmentController appointmentController;

    private MockMvc mockMvc;
    private Appointment appointment;
    private AppointmentDto appointmentDto;
    private AppointDtoResponse appointDtoRespon;
    private List<AppointDtoResponse> appointments;

    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        appointDtoRespon = new AppointDtoResponse(
                    1,
                    LocalDateTime.now(),
                    AppointmentType.ORDINARY,
                    "check-up",
                    AppointmentStatus.PENDING,
                    new PetDtoResponse(1, "Max", LocalDateTime.now(), "Male", PetType.DOG, null, null)
                );
        appointments = List.of(appointDtoRespon);
        appointmentDto = new AppointmentDto(LocalDateTime.now(), AppointmentType.URGENT, "check-up", AppointmentStatus.PASSED, 4, "12345678");
        appointment = new Appointment(LocalDateTime.now(), AppointmentType.URGENT, "check-up", AppointmentStatus.PASSED, null, null);
        mockMvc = MockMvcBuilders.standaloneSetup(appointmentController).build();
    }

    @Test
    void testIndex() {
        try {
            when(appointmentService.findAll()).thenReturn(appointments);

            // Act
            MockHttpServletResponse response = mockMvc.perform(get("/api/v1/appointments")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse();

            // Assert
            assertThat(response.getStatus(), is(200));
            assertThat(response.getContentAsString(), containsString("check-up"));
            assertThat(response.getContentAsString(), containsString("1"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Test
    void testShow() {
        try {
            when(appointmentService.save(appointmentDto)).thenReturn(appointDtoRespon);

            // Act
            MockHttpServletResponse response = mockMvc.perform(post("/api/v1/appointments") .contentType(MediaType.APPLICATION_JSON) // Set Content-Type header 
            .accept(MediaType.APPLICATION_JSON) .content(mapper.writeValueAsString(appointmentDto))) .andExpect(status().isCreated()) .andReturn() .getResponse();

            // Assert
            assertThat(response.getStatus(), is(201));
            assertThat(response.getContentAsString(), containsString("check-up"));
            assertThat(response.getContentAsString(), containsString("1"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Test
    void testShowById() {
        try {
            when(appointmentService.getById(1)).thenReturn(appointDtoRespon);

            // Act
            MockHttpServletResponse response = mockMvc.perform(get("/api/v1/appointments/1")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse();

            // Assert
            assertThat(response.getStatus(), is(200));
            assertThat(response.getContentAsString(), containsString("check-up"));
            assertThat(response.getContentAsString(), containsString("1"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Test
    void testCreateInvalidAppointment() {
        try {
            AppointmentDto invalidAppointmentDto = new AppointmentDto(null, AppointmentType.URGENT, "check-up", AppointmentStatus.PASSED, 4, "12345678");

            // Act
            MockHttpServletResponse response = mockMvc.perform(post("/api/v1/appointments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(invalidAppointmentDto)))
                    .andExpect(status().isBadRequest())
                    .andReturn()
                    .getResponse();

            // Assert
            assertThat(response.getStatus(), is(400));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Test
    void testCreateDuplicateAppointment() {
        try {
            when(appointmentService.getAppointmentsByOwnerAndDate(appointmentDto.ownerDni(), appointmentDto.date()))
            .thenReturn(appointment);

            // Act
            MockHttpServletResponse response = mockMvc.perform(post("/api/v1/appointments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(appointmentDto)))
                    .andExpect(status().isConflict())
                    .andReturn()
                    .getResponse();

            // Assert
            assertThat(response.getStatus(), is(409));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
