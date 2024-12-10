package dev.project.veterclinic.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.project.veterclinic.dtos.OwnerDto;
import dev.project.veterclinic.models.Owner;
import dev.project.veterclinic.services.OwnerService;

@RestController
@RequestMapping(path = "${api-endpoint}/owners")
public class OwnerController {

    private OwnerService service;

    public OwnerController(OwnerService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Owner> index(){
        return service.findAll();
    }

    @PostMapping("")
    public ResponseEntity<Owner> store(@RequestBody OwnerDto entity) {
        if (entity.firstName() == "" || entity.firstName() == null) return ResponseEntity.badRequest().build();
        if (entity.lastName() == "" || entity.lastName() == null) return ResponseEntity.badRequest().build();
        if (entity.dni() == "" || entity.dni() == null) return ResponseEntity.badRequest().build();
        if (entity.phoneNumber() == "" || entity.phoneNumber() == null ) return ResponseEntity.badRequest().build();

        Owner owner = service.save(entity);

        if (owner == null) return ResponseEntity.noContent().build();

        return ResponseEntity.status(201).body(owner);
    }

    // get by id
    @GetMapping("/{id}")
    public ResponseEntity<Owner> show(@PathVariable int id) {
        
        Owner owner = service.getById(id);

        return ResponseEntity.ok().body(owner);
    }
}
