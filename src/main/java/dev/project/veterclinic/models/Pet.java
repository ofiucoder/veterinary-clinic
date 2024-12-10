package dev.project.veterclinic.models;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Primary Key
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false, length = 128)
    private String name;
    
    @Column(name = "date_of_birth", nullable = false )
    private LocalDateTime dateOfBirth;

    @Column(name = "bread_id")
    private String bread_id;

    //@ManyToOne
    //@JoinColumn (name = "breed_id", referencedColumnName = "Breed_id")
    //private Breed breed; // cambia de String breed_id a un objeto breed

    @Column(name = "gender")
    private String gender;

    @Column(name = "owner")
    private int owner_id;    

    protected Pet() {

    }

    public Pet(int id, String name, LocalDateTime dateOfBirth, String bread_id, String gender, int owner_id ) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.bread_id = bread_id;
        this.gender = gender;
        this.owner_id = owner_id;

    }

    public Pet(String name, LocalDateTime dateOfBirth, String bread_id, String gender, int owner_id ) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.bread_id = bread_id;
        this.gender = gender;
        this.owner_id = owner_id;

    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public LocalDateTime getDateOfBirth(){
        return dateOfBirth;
    }

    public String getBread_id(){
        return bread_id;

    }

    public String getGender(){
        return gender;

    }

    public int getOwnerId(){
        return owner_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setBread_id(String bread_id) {
        this.bread_id = bread_id;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    @Override
    public String toString() {
        return String.format("Pet[id=%d, name='%s', dateOfBirth=%d, bread_id='%s', gender='%s', owner_id='%s']",
                id, name, dateOfBirth, bread_id, gender, owner_id);
    }
}
