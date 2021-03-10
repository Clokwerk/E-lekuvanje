package elekuvanje_mutualauth.demo.service;



import elekuvanje_mutualauth.demo.model.Termin;
import elekuvanje_mutualauth.demo.model.User;

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
