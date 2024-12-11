package dev.project.veterclinic.enums;

public enum PetType {
    CAT("Cat"),
    DOG("Dog");

    private final String value;

    // Constructor
    PetType(String value) {
        this.value = value;
    }

    // Getter for the value
    public String getValue() {
        return value;
    }
}
