package dev.project.veterclinic.dtos;

import dev.project.veterclinic.models.AppointmentStatus;
import dev.project.veterclinic.models.AppointmentType;

import java.time.LocalDateTime;

public record AppointmentDto(
    LocalDateTime date,
    int petId,
    AppointmentType type,
    String reason,
    AppointmentStatus status,
    int ownerId
) {
}
