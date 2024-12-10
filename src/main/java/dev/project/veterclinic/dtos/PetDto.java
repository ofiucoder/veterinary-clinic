package dev.project.veterclinic.dtos;

import java.sql.Date;

public record PetDto(String name, Date dateOfBirth, String breed_id, String gender, int owner_id ) {

}
