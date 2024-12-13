package dev.project.veterclinic.services;

import java.util.List;

import org.springframework.stereotype.Service;
import dev.project.veterclinic.dtos.OwnerDto;
import dev.project.veterclinic.exceptions.owner.OwnerNotFoundException;
import dev.project.veterclinic.models.Owner;
import dev.project.veterclinic.repositories.OwnerRepository;

@Service
public class OwnerService {
    private OwnerRepository repository;

    public OwnerService(OwnerRepository repository) {
        this.repository = repository;
    }

    public List<Owner> findAll(){
        return repository.findAll();
    }

    public Owner save(OwnerDto ownerDto) {
        Owner owner = new Owner(ownerDto.firstName(),  ownerDto.lastName(),  ownerDto.dni(),  ownerDto.phoneNumber());
        repository.save(owner);
        return owner;
    }

    public Owner getById(int id) {
        Owner owner = repository.findById(id).orElseThrow(()-> new OwnerNotFoundException("Owner not found by id"));
        return owner;
    }
}
