package dev.project.veterclinic.dtos.petDtoResponse;

import java.time.LocalDateTime;

public record PetTreatmentDtoResponse(
    int id,
    LocalDateTime date,
    String type,
    String note,
    int pet_id
    
) {

}
