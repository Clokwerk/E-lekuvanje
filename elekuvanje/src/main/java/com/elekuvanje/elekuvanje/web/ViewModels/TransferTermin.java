package com.elekuvanje.elekuvanje.web.ViewModels;

import com.elekuvanje.elekuvanje.model.Termin;

import java.time.LocalDateTime;

public class TransferTermin {
    private Long Id;
    private String FirstName;
    private String LastName;
    private LocalDateTime dateAndTime;
    private String location;

    public TransferTermin(Long id, String firstName, String lastName, LocalDateTime dateAndTime, String location) {
        this.Id = id;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.dateAndTime = dateAndTime;
        this.location = location;
    }

}
