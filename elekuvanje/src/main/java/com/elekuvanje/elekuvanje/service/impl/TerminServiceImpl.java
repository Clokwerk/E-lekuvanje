package com.elekuvanje.elekuvanje.service.impl;

import com.elekuvanje.elekuvanje.model.Termin;
import com.elekuvanje.elekuvanje.model.User;
import com.elekuvanje.elekuvanje.repository.TerminRepository;
import com.elekuvanje.elekuvanje.service.TerminService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TerminServiceImpl implements TerminService {
    private TerminRepository terminRepository;
    public TerminServiceImpl(TerminRepository terminRepository){
        this.terminRepository=terminRepository;
    }
    @Override
    public List<Termin> listAll() {
       return this.terminRepository.findAll();
    }

    @Override
    public void deleteById(Long Id) {
        this.terminRepository.deleteById(Id);
    }

    @Override
    public void createTermin(User doctor, User patient, LocalDateTime dateTime, String location) {
        this.terminRepository.save(new Termin(doctor,patient,dateTime,location));
    }

    @Override
    public List<Termin> findBySetByDoctorId(Long doctorId) {
        return this.terminRepository.findAll().stream().filter(x->x.getDoctor().getId()==doctorId).collect(Collectors.toList());
    }

    @Override
    public List<Termin> findBySetForPatientId(Long patientId) {
        return this.terminRepository.findAll().stream().filter(x->x.getPatient().getId()==patientId).collect(Collectors.toList());

    }

}
