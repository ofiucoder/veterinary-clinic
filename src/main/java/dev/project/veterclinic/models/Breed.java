package dev.project.veterclinic.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name= "breeds") // tables always in plural
public class Breed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Primary key
    @Column (name= "Breed_id")
    private int id;
    @Column (name= "breed_name")
    private String breedName;
    @Column (name= "pet_type")
    private String petType;
    

    public Breed() {

    }


    public Breed(int id, String breedName, String petType) {
        this.id = id;
        this.breedName = breedName;
        this.petType = petType;
    }


    public int getBread_id() {
        return id;
    }


    public void setBread_id(int id) {
        this.id = id;
    }


    public String getBreedName() {
        return breedName;
    }


    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }


    public String getPetType() {
        return petType;
    }


    public void setPetType(String petType) {
        this.petType = petType;
    }

    

    
}