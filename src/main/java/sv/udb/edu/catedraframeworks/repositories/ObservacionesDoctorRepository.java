package sv.udb.edu.catedraframeworks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.udb.edu.catedraframeworks.entities.ObservacionesDoctor;
import sv.udb.edu.catedraframeworks.entities.Doctor;

public interface ObservacionesDoctorRepository extends JpaRepository<ObservacionesDoctor, Integer> {
	List<ObservacionesDoctor> findObservacionesDoctorByIdDoctorOrderByFechaRegistroDesc(Doctor doctor);
}
