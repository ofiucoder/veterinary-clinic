package dev.project.veterclinic.services;

import java.util.List;

import dev.project.veterclinic.models.Owner;
import dev.project.veterclinic.repositories.OwnerRepository;

public class OwnerService {
    private OwnerRepository repository;

    public OwnerService(OwnerRepository repository) {
        this.repository = repository;
    }

    public List<Owner> findAll(){

        return repository.findAll();
    }
}
