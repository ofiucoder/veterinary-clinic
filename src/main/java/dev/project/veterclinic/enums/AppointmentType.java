package dev.project.veterclinic.enums;

public enum AppointmentType {
    ORDINARY("ordinary"),
    URGENT("urgent");

    private final String value;

    // Constructor
    AppointmentType(String value) {
        this.value = value;
    }

    // Getter for the value
    public String getValue() {
        return value;
    }

    // Static method to get enum by value
    public static AppointmentType fromValue(String value) {
        for (AppointmentType type : AppointmentType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unexpected value: " + value);
    }
}

