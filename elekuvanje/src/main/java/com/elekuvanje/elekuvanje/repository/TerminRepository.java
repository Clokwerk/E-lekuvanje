package com.elekuvanje.elekuvanje.repository;

import com.elekuvanje.elekuvanje.model.Termin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TerminRepository extends JpaRepository<Termin,Long> {
    List<Termin> findAll();

}
