package dev.project.veterclinic.dtos;

import java.time.LocalDateTime;

public record PetDto(String name, LocalDateTime dateOfBirth, String bread_id, String gender, int owner_id ) {

}
