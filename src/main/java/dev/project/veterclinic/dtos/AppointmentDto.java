package dev.project.veterclinic.dtos;

import java.time.LocalDateTime;

import dev.project.veterclinic.enums.AppointmentStatus;
import dev.project.veterclinic.enums.AppointmentType;

public record AppointmentDto(
    LocalDateTime date,
    AppointmentType type,
    String reason,
    AppointmentStatus status,
    int petId,
    String ownerDni
) {
}
