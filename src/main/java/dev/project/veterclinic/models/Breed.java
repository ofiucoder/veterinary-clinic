package dev.project.veterclinic.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name= "breeds") // tables always in plural
public class Breed {

    // private static final CascadeType[] ALL = null;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Primary key
    @Column (name= "id")
    private int id;

    @Column (name= "breed_name")
    private String breedName;

    @OneToMany(mappedBy="breed")
    public List<Pet> pets;

    public Breed() {

    }

    public Breed(String breedName) {
        this.breedName = breedName;
    }

    public Breed(int id, String breedName) {
        this.id = id;
        this.breedName = breedName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}