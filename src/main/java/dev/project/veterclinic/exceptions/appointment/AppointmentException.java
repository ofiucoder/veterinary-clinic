package dev.project.veterclinic.exceptions.appointment;

public class AppointmentException extends RuntimeException{

    public AppointmentException(String message) {
        super(message);
    }

    public AppointmentException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
