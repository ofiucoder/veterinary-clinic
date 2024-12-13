package dev.project.veterclinic.dtos.appointmentDtoResponse;

import dev.project.veterclinic.dtos.petDtoResponse.PetDtoResponse;
import dev.project.veterclinic.enums.AppointmentStatus;
import dev.project.veterclinic.enums.AppointmentType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AppointDtoResponseTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        int id = 1;
        LocalDateTime date = LocalDateTime.of(2023, 5, 10, 15, 30);
        AppointmentType type = AppointmentType.ORDINARY;
        String reason = "Routine checkup";
        AppointmentStatus status = AppointmentStatus.PENDING;
        PetDtoResponse pet = new PetDtoResponse(1, "Buddy", LocalDateTime.of(2020, 5, 5, 10, 0), "Male", null, null, null);

        // Act
        AppointDtoResponse appointmentDtoResponse = new AppointDtoResponse(id, date, type, reason, status, pet);

        // Assert
        assertEquals(id, appointmentDtoResponse.id());
        assertEquals(date, appointmentDtoResponse.date());
        assertEquals(type, appointmentDtoResponse.type());
        assertEquals(reason, appointmentDtoResponse.reason());
        assertEquals(status, appointmentDtoResponse.status());
        assertEquals(pet, appointmentDtoResponse.pet());
    }

    @Test
    void testEqualsAndHashCode() {
        // Arrange
        int id = 1;
        LocalDateTime date = LocalDateTime.of(2023, 5, 10, 15, 30);
        AppointmentType type = AppointmentType.ORDINARY;
        String reason = "Routine checkup";
        AppointmentStatus status = AppointmentStatus.PENDING;
        PetDtoResponse pet = new PetDtoResponse(1, "Buddy", LocalDateTime.of(2020, 5, 5, 10, 0), "Male", null, null, null);

        // Act
        AppointDtoResponse appointmentDtoResponse1 = new AppointDtoResponse(id, date, type, reason, status, pet);
        AppointDtoResponse appointmentDtoResponse2 = new AppointDtoResponse(id, date, type, reason, status, pet);

        // Assert
        assertEquals(appointmentDtoResponse1, appointmentDtoResponse2);  // They should be equal since all fields match
        assertEquals(appointmentDtoResponse1.hashCode(), appointmentDtoResponse2.hashCode());  // hashCodes should match for equal objects
    }

    @Test
    void testToString() {
        // Arrange
        int id = 1;
        LocalDateTime date = LocalDateTime.of(2023, 5, 10, 15, 30);
        AppointmentType type = AppointmentType.ORDINARY;
        String reason = "Routine checkup";
        AppointmentStatus status = AppointmentStatus.PENDING;
        PetDtoResponse pet = new PetDtoResponse(1, "Buddy", LocalDateTime.of(2020, 5, 5, 10, 0), "Male", null, null, null);

        // Act
        AppointDtoResponse appointmentDtoResponse = new AppointDtoResponse(id, date, type, reason, status, pet);

        // Assert
        String expectedString = "AppointDtoResponse[id=1, date=2023-05-10T15:30, type=ORDINARY, reason=Routine checkup, status=PENDING, pet=PetDtoResponse[id=1, name=Buddy, dateOfBirth=2020-05-05T10:00, gender=Male, petType=null, breed=null, owner=null]]";
        assertEquals(expectedString, appointmentDtoResponse.toString());
    }
}
