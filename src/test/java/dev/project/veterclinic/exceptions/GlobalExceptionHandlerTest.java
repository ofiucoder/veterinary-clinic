package dev.project.veterclinic.exceptions;

import dev.project.veterclinic.exceptions.appointment.AppointmentConflictException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    void testHandleAppointmentConflict() {
        // Trigger an exception manually
        ResponseEntity<String> responseEntity = globalExceptionHandler.handleAppointmentConflict(new AppointmentConflictException("Conflict with appointment"));

        // Assert that the response status is CONFLICT and the body contains the error message
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        assertEquals("Conflict with appointment", responseEntity.getBody());
    }

    @Test
    void testHandleGeneralException() {
        // Trigger a general exception manually
        ResponseEntity<String> responseEntity = globalExceptionHandler.handleGeneralException(new Exception("General error"));

        // Assert that the response status is INTERNAL_SERVER_ERROR and the body contains the error message
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("An unexpected error occurred: General error", responseEntity.getBody());
    }
}
