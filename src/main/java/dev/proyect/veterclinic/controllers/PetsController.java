package dev.proyect.veterclinic.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${api-endpoint}/pets")
public class PetsController {
    @GetMapping("/")
    public String index(){
        return "Hello Pets!";
    }
}