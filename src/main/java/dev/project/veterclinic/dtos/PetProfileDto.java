package dev.project.veterclinic.dtos;

import java.time.LocalDateTime;
import java.util.List;

import dev.project.veterclinic.dtos.petDtoResponse.PetOwnerDtoReponse;
import dev.project.veterclinic.enums.PetType;
import dev.project.veterclinic.dtos.petDtoResponse.PetBreedDtoResponse;

public record PetProfileDto (
     int id ,
     String name , 
     LocalDateTime  dateOfBirth,
     String gender ,
     PetType petType ,
     PetBreedDtoResponse breed,
     PetOwnerDtoReponse owner,
     List<TreatmentDto> treatments
){
     
}