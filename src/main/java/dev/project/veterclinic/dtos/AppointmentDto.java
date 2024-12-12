package dev.project.veterclinic.dtos;

import java.time.LocalDateTime;

import dev.project.veterclinic.dtos.petDtoResponse.PetDtoResponse;
import dev.project.veterclinic.dtos.petDtoResponse.PetOwnerDtoReponse;
import dev.project.veterclinic.enums.AppointmentStatus;
import dev.project.veterclinic.enums.AppointmentType;

public record AppointmentDto(
    LocalDateTime date,
    AppointmentType type,
    String reason,
    AppointmentStatus status,
    PetDtoResponse pet,
    PetOwnerDtoReponse owner
) {
}
