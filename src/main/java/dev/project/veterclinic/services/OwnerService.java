package dev.project.veterclinic.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import dev.project.veterclinic.dtos.OwnerDto;
import dev.project.veterclinic.dtos.petDtoResponse.PetOwnerDtoReponse;
import dev.project.veterclinic.exceptions.owner.OwnerNotFoundException;
import dev.project.veterclinic.models.Owner;
import dev.project.veterclinic.repositories.OwnerRepository;

@Service
public class OwnerService {
    private OwnerRepository repository;

    public OwnerService(OwnerRepository repository) {
        this.repository = repository;
    }

    public List<PetOwnerDtoReponse> findAll(){
        List<PetOwnerDtoReponse> OwnerDtoList = new ArrayList<>();
        for (Owner owner : repository.findAll()) {
            OwnerDtoList.add(
                                    new  PetOwnerDtoReponse(
                                    owner.getId(),
                                    owner.getFirstName(),
                                    owner.getLastName(),
                                    owner.getDni(),
                                    owner.getPhoneNumber()
                                )
                            );
        }
        return OwnerDtoList;
    }

    public PetOwnerDtoReponse save(OwnerDto ownerDto) {
        if(repository.existByDni(ownerDto.dni())){
            throw  new OwnerNotFoundException("Owner already exist");
        }
        Owner owner = new Owner(ownerDto.firstName(),  ownerDto.lastName(),  ownerDto.dni(),  ownerDto.phoneNumber());
        repository.save(owner);
        return new  PetOwnerDtoReponse(
            owner.getId(),
            owner.getFirstName(),
            owner.getLastName(),
            owner.getDni(),
            owner.getPhoneNumber()
        );
    }

    public PetOwnerDtoReponse getById(int id) {
        Owner owner = repository.findById(id).orElseThrow(()-> new OwnerNotFoundException("Owner not found by id"));
        return new  PetOwnerDtoReponse(
                owner.getId(),
                owner.getFirstName(),
                owner.getLastName(),
                owner.getDni(),
                owner.getPhoneNumber()
            );
    }
}
