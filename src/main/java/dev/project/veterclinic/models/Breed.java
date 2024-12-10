package dev.project.veterclinic.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import dev.project.veterclinic.models.Pet;

@Entity
@Table (name= "breeds") // tables always in plural
public class Breed {

    private static final CascadeType[] ALL = null;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Primary key
    @Column (name= "id")
    private int id;
    @Column (name= "breed_name")
    private String breedName;
    @Column (name= "pet_type")
    private String petType;
    

    @OneToMany(mappedBy="breed")
    public List<Pet> pets;

    public Breed() {

    }


    public Breed(int id, String breedName, String petType) {
        this.id = id;
        this.breedName = breedName;
        this.petType = petType;
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


    public String getPetType() {
        return petType;
    }


    public void setPetType(String petType) {
        this.petType = petType;
    }

    

    
}