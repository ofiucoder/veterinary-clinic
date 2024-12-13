package dev.project.veterclinic.exceptions.owner;

public class OwnerException extends RuntimeException {

    public OwnerException(String message) {
        super(message);
    }

    public OwnerException(String message, Throwable cause) {
        super(message, cause);
    }

}