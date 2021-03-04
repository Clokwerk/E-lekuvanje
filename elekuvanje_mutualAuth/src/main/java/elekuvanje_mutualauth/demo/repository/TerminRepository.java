package elekuvanje_mutualauth.demo.repository;


import elekuvanje_mutualauth.demo.model.Termin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TerminRepository extends JpaRepository<Termin,Long> {
    List<Termin> findAll();

}
