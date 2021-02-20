package com.elekuvanje.elekuvanje.service;

import com.elekuvanje.elekuvanje.model.Termin;

import java.time.LocalDateTime;
import java.util.List;

public interface TerminService {
    List<Termin> listAll();
    void deleteById(Long Id);
    void createTermin(Long doctorId, Long patientId, LocalDateTime dateTime,String location);

}
