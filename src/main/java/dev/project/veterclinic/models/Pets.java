package dev.project.veterclinic.models;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Table(name="Pets")
public class Pets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Primary Key
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 128)
    private String name;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth", nullable = false )
    private Date dateOfBirth;

    @Column(name = "bread_id")
    private String bread_id;

    @Column(name = "gender")
    private String gender;

    @Column(name = "owner")
    private int owner_id;    

    protected Pets() {

    }

    public Pets(Long id, String name, Date dateOfBirth, String bread_id, String gender, int owner_id ) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.bread_id = bread_id;
        this.gender = gender;
        this.owner_id = owner_id;

    }

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public Date getDateOfBirth(){
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

 /*    public List<Appointment> getAppointments() {
        return appointments;
    }

    public List<Treatments> getTreatments() {
        return treatments;
    }
*/
    @Override
    public String toString() {
        return String.format("Pet[id=%d, name='%s', dateOfBirth=%d, bread_id='%s', gender='%s', owner_id='%s']",
                id, name, dateOfBirth, bread_id, gender, owner_id);
    }


}
