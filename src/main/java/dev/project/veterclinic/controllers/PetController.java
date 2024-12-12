package dev.project.veterclinic.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.project.veterclinic.dtos.PetDto;
import dev.project.veterclinic.dtos.petDtoResponse.PetDtoResponse;
import dev.project.veterclinic.models.Pet;
import dev.project.veterclinic.services.PetService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "${api-endpoint}/pets")
public class PetController {

    private PetService service;

    public PetController(PetService service){
        this.service = service;
    }

    @GetMapping("")
    public List<PetDtoResponse> index(){
        return service.findAll();
    }

    @PostMapping("")
    public ResponseEntity<PetDtoResponse> store(@RequestBody PetDto entity){
        PetDtoResponse pet = service.save(entity);
        return ResponseEntity.status(201).body(pet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePet(@PathVariable int id){
        service.deleletById(id);
        return ResponseEntity.status(200).body("Deleted successfully");
    }

    // @PutMapping("/{id}")
    // public ResponseEntity<Pet> updatePet(@PathVariable int id, @RequestBody Pet updatePet) {
    //     try {Pet pet = service.updateById(id, updatePet);
        
    //     return ResponseEntity.ok(pet);
    // } catch (RuntimeException e) {

    //     return ResponseEntity.status(404).body(null); //return 404 if is not found
    //     }
    // }
}