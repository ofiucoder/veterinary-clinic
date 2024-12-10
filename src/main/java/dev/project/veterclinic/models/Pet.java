package dev.project.veterclinic.models;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Primary Key
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDateTime dateOfBirth;

    @Column(name = "gender")
    private String gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "breed_id")
    private Breed breed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public Pet(int id, String name, LocalDateTime dateOfBirth, String gender, int owner_id) {

        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;

    }

    public Pet(String name, LocalDateTime dateOfBirth, String gender, int owner_id) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;

    }

    @Override
    public String toString() {
        return String.format("Pet[id=%d, name='%s', dateOfBirth=%d,  gender='%s']",
                id, name, dateOfBirth, gender);
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setName(String name2) {
        this.name = name2;

    }

    public void setDateOfBirth(LocalDateTime dateOfBirth2) {
        this.dateOfBirth = dateOfBirth2;

    }
}
