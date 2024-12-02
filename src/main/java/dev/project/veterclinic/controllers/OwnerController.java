package dev.project.veterclinic.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${api-endpoint}/owners")
public class OwnerController {
    @GetMapping("/")
    public String index(){
        return "Hello sprint!";
    }
}
