package dev.project.veterclinic.exceptions.appointment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Appointment not found")
public class AppointmentNotFoundException extends AppointmentException {
    public AppointmentNotFoundException(String message) {
        super(message);
    }

    public AppointmentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
