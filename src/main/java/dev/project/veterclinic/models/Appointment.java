package dev.project.veterclinic.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import dev.project.veterclinic.enums.AppointmentStatus;
import dev.project.veterclinic.enums.AppointmentType;

@Entity
@Table(name = "APPOINTMENTS")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Primary Key
    @Column(name = "id")
    private int id;
    @Column(name = "date")
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private AppointmentType type;
    @Column(name = "reason")
    private String reason;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AppointmentStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_dni", referencedColumnName = "dni")
    private Owner owner;

    public Appointment() {
    }


    public Appointment(LocalDateTime date, AppointmentType type, String reason, AppointmentStatus status,
            Pet pet, Owner owner) {
        this.date = date;
        this.type = type;
        this.reason = reason;
        this.status = status;
        this.pet = pet;
        this.owner = owner;
    }   

    public Appointment(int id, LocalDateTime date, AppointmentType type, String reason, AppointmentStatus status,
            Pet pet, Owner owner) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.reason = reason;
        this.status = status;
        this.pet = pet;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public AppointmentType getType() {
        return type;
    }

    public void setType(AppointmentType type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
