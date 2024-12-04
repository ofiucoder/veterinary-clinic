package dev.project.veterclinic.exceptions.pet;


public class PetException extends RuntimeException {

    public PetException(String message){
        super(message);
    }

    public PetException(String message, Throwable cause){
        super(message, cause);
    }
}
