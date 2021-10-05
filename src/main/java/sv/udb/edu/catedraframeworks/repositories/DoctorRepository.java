package sv.udb.edu.catedraframeworks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.udb.edu.catedraframeworks.entities.Area;
import sv.udb.edu.catedraframeworks.entities.Doctor;

import java.util.List;

public interface DoctorRepository extends JpaRepository <Doctor, Integer> {

    List<Doctor> findByIdArea(Area i);


}
