package dev.project.veterclinic.enums;

public enum AppointmentStatus {
    PENDING("pending"),
    PASSED("passed");

    private final String value;

    // Constructor
    AppointmentStatus(String value) {
        this.value = value;
    }

    // Getter for the value
    public String getValue() {
        return value;
    }

    // Static method to get enum by value
    public static AppointmentStatus fromValue(String value) {
        for (AppointmentStatus status : AppointmentStatus.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unexpected value: " + value);
    }
}
