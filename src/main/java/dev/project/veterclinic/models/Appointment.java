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
    @Column(name = "pet_id")
    private int petId;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private AppointmentType type;
    @Column(name = "reason")
    private String reason;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AppointmentStatus status;
    @Column(name = "owner_id")
    private int ownerId;

    public Appointment() {
    }

    public Appointment(int id,
            LocalDateTime date,
            int petId,
            AppointmentType type,
            String reason,
            AppointmentStatus status,
            int ownerId) {
        this.id = id;
        this.date = date;
        this.petId = petId;
        this.type = type;
        this.reason = reason;
        this.status = status;
        this.ownerId = ownerId;
    }

    public Appointment(
            LocalDateTime date,
            int petId,
            AppointmentType type,
            String reason,
            AppointmentStatus status,
            int ownerId) {

        this.date = date;
        this.petId = petId;
        this.type = type;
        this.reason = reason;
        this.status = status;
        this.ownerId = ownerId;
    }

    // Getters and setters
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

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
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

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
