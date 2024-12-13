package dev.project.veterclinic.exceptions.appointment;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppointmentNotFoundExceptionTest {

    @Test
    void testAppointmentNotFoundExceptionWithMessage() {
        String errorMessage = "Appointment with the given ID was not found";
        AppointmentNotFoundException exception = new AppointmentNotFoundException(errorMessage);

        // Verify that the exception contains the correct message
        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testAppointmentNotFoundExceptionWithMessageAndCause() {
        String errorMessage = "Appointment with the given ID was not found";
        Throwable cause = new RuntimeException("Cause of the error");
        AppointmentNotFoundException exception = new AppointmentNotFoundException(errorMessage, cause);

        // Verify that the exception contains the correct message and cause
        assertEquals(errorMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
