package dev.project.veterclinic.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppointmentController {

    @GetMapping("")
    public String index(){
        return "Hello sprint!";
    }
}
