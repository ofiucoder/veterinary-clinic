package dev.project.veterclinic.dtos;

import java.sql.Date;

public record PetDto(Long id, String name, Date dateOfBirth, String bread_id, String gender, int owner_id ) {

}
