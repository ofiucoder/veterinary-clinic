package dev.project.veterclinic.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.project.veterclinic.dtos.PetDto;
import dev.project.veterclinic.models.Pet;
import dev.project.veterclinic.services.PetService;

@RestController
@RequestMapping(path = "${api-endpoint}/pets")
public class PetController {

    private PetService service;

    public PetController(PetService service){
        this.service = service;
    }

    @GetMapping("")
    public List<Pet> index(){
        return service.findAll();
    }

    
}