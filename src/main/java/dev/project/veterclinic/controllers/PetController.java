package dev.project.veterclinic.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePet(@PathVariable int id){
        service.deleletById(id);
        return ResponseEntity.status(200).body("Deleted successfully");
    }

}