package dev.project.veterclinic.dtos.appointmentDtoResponse;

import java.time.LocalDateTime;

import dev.project.veterclinic.dtos.petDtoResponse.PetDtoResponse;
import dev.project.veterclinic.enums.AppointmentStatus;
import dev.project.veterclinic.enums.AppointmentType;

public record AppointDtoRsponse (
    int id,
    LocalDateTime date,
    AppointmentType type,
    String reason,
    AppointmentStatus status,
    PetDtoResponse pet
){

}