package dev.project.veterclinic.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "OWNER")
public class Owner{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Primary Key
    @Column(name = "id_country") // Primary Key
    private int id;
    private String name;

    
    public Owner() {
    }

    public Owner(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
