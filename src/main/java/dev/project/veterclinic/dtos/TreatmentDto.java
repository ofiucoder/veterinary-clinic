package dev.project.veterclinic.dtos;

import java.time.LocalDateTime;

import dev.project.veterclinic.models.Treatment.TreatmentType;

public record TreatmentDto(
    int id,
    LocalDateTime date,
    TreatmentType type,
    String note
) {

} 
