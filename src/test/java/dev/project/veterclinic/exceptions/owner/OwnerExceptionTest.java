package dev.project.veterclinic.exceptions.owner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OwnerExceptionTest {

    @Test
    void testOwnerExceptionWithMessage() {
        String errorMessage = "Owner not found";
        OwnerException exception = new OwnerException(errorMessage);

        // Verify that the exception contains the correct message
        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testOwnerExceptionWithMessageAndCause() {
        String errorMessage = "Owner not found";
        Throwable cause = new RuntimeException("Cause of the error");
        OwnerException exception = new OwnerException(errorMessage, cause);

        // Verify that the exception contains the correct message and cause
        assertEquals(errorMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
