package com.elekuvanje.elekuvanje.service;

import com.elekuvanje.elekuvanje.model.Termin;
import com.elekuvanje.elekuvanje.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TerminService {
    List<Termin> listAll();
    void deleteById(Long Id);
    void createTermin(User doctor, User patient, LocalDateTime dateTime, String location);
    List<Termin> findBySetByDoctorId(Long doctorId);
    List<Termin> findBySetForPatientId(Long patientId);

}
