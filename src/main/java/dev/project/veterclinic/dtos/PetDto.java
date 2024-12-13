package dev.project.veterclinic.dtos;

import java.time.LocalDateTime;

import dev.project.veterclinic.enums.PetType;

public record PetDto(
    String name, 
    LocalDateTime dateOfBirth,
    String gender,
    PetType petType,
    String breed,
    int ownerId
) {

}
