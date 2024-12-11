package dev.project.veterclinic.dtos;

import java.time.LocalDateTime;

import dev.project.veterclinic.enums.AppointmentStatus;
import dev.project.veterclinic.enums.AppointmentType;

public record AppointmentDto(
    LocalDateTime date,
    int petId,
    AppointmentType type,
    String reason,
    AppointmentStatus status,
    int ownerId
) {
}
