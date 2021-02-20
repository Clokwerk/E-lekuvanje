package com.elekuvanje.elekuvanje.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity

public class Termin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Long setByDoctorId;
    private Long setForPatientId;
    private LocalDateTime dateAndTime;
    private String location;
    public Termin(){

    }

    public Termin( Long setByDoctorId, Long setForPatientId, LocalDateTime dateAndTime, String location) {

        this.setByDoctorId = setByDoctorId;
        this.setForPatientId = setForPatientId;
        this.dateAndTime = dateAndTime;
        this.location = location;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getSetByDoctorId() {
        return setByDoctorId;
    }

    public void setSetByDoctorId(Long setByDoctorId) {
        this.setByDoctorId = setByDoctorId;
    }

    public Long getSetForPatientId() {
        return setForPatientId;
    }

    public void setSetForPatientId(Long setForPatientId) {
        this.setForPatientId = setForPatientId;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
