package com.elekuvanje.elekuvanje.service.impl;

import com.elekuvanje.elekuvanje.model.Termin;
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
    public void createTermin(Long doctorId, Long patientId, LocalDateTime dateTime,String location) {
        this.terminRepository.save(new Termin(doctorId,patientId,dateTime,location));
    }

    @Override
    public List<Termin> findBySetByDoctorId(Long doctorId) {
        return this.terminRepository.findAll().stream().filter(x->x.getSetByDoctorId()==doctorId).collect(Collectors.toList());
    }
}
