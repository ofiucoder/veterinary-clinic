package dev.project.veterclinic.dtos;

import java.time.LocalDateTime;

import dev.project.veterclinic.enums.PetType;

public record PatientDto(
    String name,
    LocalDateTime dateOfBirth,
    String gender,
    PetType petType,
    String breedName,
    String ownerFirstName,
    String ownerLastName,
    String ownerDni,
    String ownerPhoneNumber
) {
}


   