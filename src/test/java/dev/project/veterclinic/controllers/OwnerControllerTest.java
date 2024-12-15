package dev.project.veterclinic.controllers;

import dev.project.veterclinic.dtos.AppointmentDto;
import dev.project.veterclinic.dtos.OwnerDto;
import dev.project.veterclinic.dtos.appointmentDtoResponse.AppointDtoResponse;
import dev.project.veterclinic.dtos.petDtoResponse.PetOwnerDtoReponse;
import dev.project.veterclinic.enums.AppointmentStatus;
import dev.project.veterclinic.enums.AppointmentType;
import dev.project.veterclinic.services.AppointmentService;
import dev.project.veterclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
public class OwnerControllerTest {

    @Mock
    private OwnerService ownerService;
    @Mock
    private AppointmentService appointmentService;

    @InjectMocks
    private OwnerController ownerController;

    private List<PetOwnerDtoReponse> mockOwnerList;

    private MockMvc mockMvc;
    private ObjectMapper mapper;

    
    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mockOwnerList = List.of(
                new PetOwnerDtoReponse(1, "John", "Doe", "12345678", "987654321"),
                new PetOwnerDtoReponse(2, "Jane", "Smith", "87654321", "123456789"));
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @Test
    void testIndex() {
        try {
            // Arrange: Mock the service call
            when(ownerService.findAll()).thenReturn(mockOwnerList);

            MockHttpServletResponse responseM = mockMvc.perform(get("/api/v1/owners")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse();

            assertThat(responseM.getStatus(), is(200));
            assertThat(responseM.getContentAsString(), containsString("John"));
            assertThat(responseM.getContentAsString(), containsString("Smith"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e); // Opcional: relanzar como una excepción no comprobada
        }
    }

     @Test
    void testStore() {
        try {
            OwnerDto ownerDto = new OwnerDto("John", "Doe", "12345678", "987654321");
            PetOwnerDtoReponse ownerResponse = new PetOwnerDtoReponse(1, "John", "Doe", "12345678", "987654321");

            when(ownerService.save(any(OwnerDto.class))).thenReturn(ownerResponse);

            MockHttpServletResponse response = mockMvc.perform(post("/api/v1/owners")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(ownerDto)))
                    .andExpect(status().isCreated())
                    .andReturn()
                    .getResponse();

            assertThat(response.getStatus(), is(201));
            assertThat(response.getContentAsString(), containsString("John"));
            assertThat(response.getContentAsString(), containsString("Doe"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }  

    @Test
    void testShowById() {
        try {
            PetOwnerDtoReponse ownerResponse = new PetOwnerDtoReponse(1, "John", "Doe", "12345678", "987654321");

            when(ownerService.getById(1)).thenReturn(ownerResponse);

            MockHttpServletResponse response = mockMvc.perform(get("/api/v1/owners/1")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse();

            assertThat(response.getStatus(), is(200));
            assertThat(response.getContentAsString(), containsString("John"));
            assertThat(response.getContentAsString(), containsString("Doe"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Test
    void testShowOwnerAppointments() {
        try {
            List<AppointDtoResponse> appointments = List.of(
                    new AppointDtoResponse(1, LocalDateTime.now(), AppointmentType.ORDINARY, "check-up", AppointmentStatus.PENDING, null)
            );

            when(appointmentService.findAppointmentsByOwnerId("12345678")).thenReturn(appointments);

            MockHttpServletResponse response = mockMvc.perform(get("/api/v1/owners/12345678/appointments")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse();

            assertThat(response.getStatus(), is(200));
            assertThat(response.getContentAsString(), containsString("check-up"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Test
    void testStoreAppointment() {
        try {
            AppointmentDto appointmentDto = new AppointmentDto(LocalDateTime.now(), AppointmentType.ORDINARY, "check-up", AppointmentStatus.PENDING, 1, "12345678");
            AppointDtoResponse appointmentResponse = new AppointDtoResponse(1, LocalDateTime.now(), AppointmentType.ORDINARY, "check-up", AppointmentStatus.PENDING, null);

            when(appointmentService.saveAppointmentByOwnerDni(eq("12345678"), any(AppointmentDto.class))).thenReturn(appointmentResponse);

            MockHttpServletResponse response = mockMvc.perform(post("/api/v1/owners/12345678/appointments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(appointmentDto)))
                    .andExpect(status().isCreated())
                    .andReturn()
                    .getResponse();

            assertThat(response.getStatus(), is(201));
            assertThat(response.getContentAsString(), containsString("check-up"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Test
    void testUpdateAppointment() {
        try {
            AppointmentDto appointmentDto = new AppointmentDto(LocalDateTime.now(), AppointmentType.ORDINARY, "check-up", AppointmentStatus.PENDING, 1, "12345678");
            AppointDtoResponse appointmentResponse = new AppointDtoResponse(1, LocalDateTime.now(), AppointmentType.ORDINARY, "check-up", AppointmentStatus.PENDING, null);

            when(appointmentService.updateAppointmentByOwnerDniAndAppointmentId(eq("12345678"), eq(1), any(AppointmentDto.class))).thenReturn(appointmentResponse);

            MockHttpServletResponse response = mockMvc.perform(put("/api/v1/owners/12345678/appointments/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(appointmentDto)))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse();

            assertThat(response.getStatus(), is(200));
            assertThat(response.getContentAsString(), containsString("check-up"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Test
    void testDeleteAppointment() {
        try {
            doNothing().when(appointmentService).deleteAppointmentByOwnerDniAndappointmentId("12345678", 1);

            MockHttpServletResponse response = mockMvc.perform(delete("/api/v1/owners/12345678/appointments/1")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse();

            assertThat(response.getStatus(), is(200));
            assertThat(response.getContentAsString(), containsString("Deleted successfully"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}