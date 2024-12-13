package dev.project.veterclinic.exceptions.appointment;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppointmentExceptionTest {

    @Test
    void testAppointmentExceptionWithMessage() {
        String errorMessage = "Appointment error occurred";
        AppointmentException exception = new AppointmentException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testAppointmentExceptionWithMessageAndCause() {
        String errorMessage = "Appointment error occurred";
        Throwable cause = new RuntimeException("Cause of the error");
        AppointmentException exception = new AppointmentException(errorMessage, cause);

        assertEquals(errorMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
