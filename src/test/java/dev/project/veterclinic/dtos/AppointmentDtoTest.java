package dev.project.veterclinic.dtos;

import dev.project.veterclinic.enums.AppointmentStatus;
import dev.project.veterclinic.enums.AppointmentType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AppointmentDtoTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        LocalDateTime date = LocalDateTime.of(2023, 1, 1, 10, 0);
        AppointmentType type = AppointmentType.ORDINARY;
        String reason = "Routine checkup";
        AppointmentStatus status = AppointmentStatus.PENDING;
        int petId = 1;
        String ownerDni = "12345678";

        // Act
        AppointmentDto appointmentDto = new AppointmentDto(date, type, reason, status, petId, ownerDni);

        // Assert
        assertEquals(date, appointmentDto.date());
        assertEquals(type, appointmentDto.type());
        assertEquals(reason, appointmentDto.reason());
        assertEquals(status, appointmentDto.status());
        assertEquals(petId, appointmentDto.petId());
        assertEquals(ownerDni, appointmentDto.ownerDni());
    }

    @Test
    void testEqualsAndHashCode() {
        // Arrange
        LocalDateTime date = LocalDateTime.of(2023, 1, 1, 10, 0);
        AppointmentType type = AppointmentType.ORDINARY;
        String reason = "Routine checkup";
        AppointmentStatus status = AppointmentStatus.PENDING;
        int petId = 1;
        String ownerDni = "12345678";

        // Act
        AppointmentDto appointmentDto1 = new AppointmentDto(date, type, reason, status, petId, ownerDni);
        AppointmentDto appointmentDto2 = new AppointmentDto(date, type, reason, status, petId, ownerDni);

        // Assert
        assertEquals(appointmentDto1, appointmentDto2);  // They should be equal since all fields match
        assertEquals(appointmentDto1.hashCode(), appointmentDto2.hashCode());  // hashCodes should match for equal objects
    }

    @Test
    void testToString() {
        // Arrange
        LocalDateTime date = LocalDateTime.of(2023, 1, 1, 10, 0);
        AppointmentType type = AppointmentType.ORDINARY;
        String reason = "Routine checkup";
        AppointmentStatus status = AppointmentStatus.PENDING;
        int petId = 1;
        String ownerDni = "12345678";

        // Act
        AppointmentDto appointmentDto = new AppointmentDto(date, type, reason, status, petId, ownerDni);

        // Assert
        String expectedString = "AppointmentDto[date=" + date + ", type=ORDINARY, reason=Routine checkup, status=PENDING, petId=1, ownerDni=12345678]";
        assertEquals(expectedString, appointmentDto.toString());
    }
}
