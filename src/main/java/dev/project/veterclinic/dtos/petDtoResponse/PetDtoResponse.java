package dev.project.veterclinic.dtos.petDtoResponse;

import java.time.LocalDateTime;

import dev.project.veterclinic.enums.PetType;

public record PetDtoResponse (
     int id ,
     String name , 
     LocalDateTime  dateOfBirth,
     String gender ,
     PetType petType ,
     PetBreedDtoResponse breed ,
     PetOwnerDtoReponse owner
){

}