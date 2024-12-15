package dev.project.veterclinic.controllers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import dev.project.veterclinic.dtos.PetDto;
import dev.project.veterclinic.dtos.PetProfileDto;
import dev.project.veterclinic.dtos.petDtoResponse.PetDtoResponse;
import dev.project.veterclinic.enums.PetType;
import dev.project.veterclinic.services.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)  // Enable Mockito extension for JUnit 5
public class PetControllerTest {

    @Mock
    private PetService petService;

    @InjectMocks
    private PetController petController;  // The controller under test

    private MockMvc mockMvc;
    private ObjectMapper mapper;

    private PetDto mockPetDto;
    private PetDtoResponse mockPetDtoResponse;
    private List<PetDtoResponse> petsDtoRespList;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mockPetDto = new PetDto("Max", null, "Male", null, "Labrador", 1);  // Sample PetDto
        mockPetDtoResponse = new PetDtoResponse(1, "Max", null, "Male", null, null, null);  // Sample PetDtoResponse
        petsDtoRespList = List.of(mockPetDtoResponse);
        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();

    }

    @Test
    void testIndex() {
        try {
            // Arrange
            when(petService.findAll()).thenReturn(petsDtoRespList);

            // Act
            MockHttpServletResponse response = mockMvc.perform(get("/api/v1/pets")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse();

            // Assert
            assertThat(response.getStatus(), is(200));
            assertThat(response.getContentAsString(), containsString("Max"));
            assertThat(response.getContentAsString(), containsString("1"));
            assertThat(response.getContentAsString(), containsString("Male"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    
    }

    @Test
    void testStore() {
        try {
            // Arrange
            when(petService.save(mockPetDto)).thenReturn(mockPetDtoResponse);

            // Act
            MockHttpServletResponse response = mockMvc.perform(post("/api/v1/pets")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(mockPetDto)))
                    .andExpect(status().isCreated())
                    .andReturn()
                    .getResponse();

            // Assert
            assertThat(response.getStatus(), is(201));
            assertThat(response.getContentAsString(), containsString("Max"));
            assertThat(response.getContentAsString(), containsString("1"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Test
    void testGetPetProfile() {
        try {
            // Arrange
            PetProfileDto mockPetProfile = new PetProfileDto(1, "Max", LocalDateTime.now(), "Male", PetType.DOG, null,  null, null);

            when(petService.getPetProfile(1)).thenReturn(mockPetProfile);

            // Act
            MockHttpServletResponse response = mockMvc.perform(get("/api/v1/pets/1")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse();

            // Assert
            assertThat(response.getStatus(), is(200));
            assertThat(response.getContentAsString(), containsString("Max"));
            assertThat(response.getContentAsString(), containsString("Male"));
            assertThat(response.getContentAsString(), containsString("1"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Test
    void testDeletePet() {
        try {
            // Arrange
            doNothing().when(petService).deleletById(1);

            // Act
            MockHttpServletResponse response = mockMvc.perform(delete("/api/v1/pets/1")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse();

            // Assert
            assertThat(response.getStatus(), is(200));
            assertThat(response.getContentAsString(), containsString("Deleted successfully"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Test
    void testUpdatePet() {
        try {
            // Arrange
            PetDto updatePetDto = new PetDto("Max", null, "Male", null, "Labrador", 1);
            PetDtoResponse updatedPetResponse = new PetDtoResponse(1, "Max", null, "Male", null, null, null);
            when(petService.updateById(1, updatePetDto)).thenReturn(updatedPetResponse);

            // Act
            MockHttpServletResponse response = mockMvc.perform(put("/api/v1/pets/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(updatePetDto)))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse();

            // Assert
            assertThat(response.getStatus(), is(200 ));
            assertThat(response.getContentAsString(), containsString("Max"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
